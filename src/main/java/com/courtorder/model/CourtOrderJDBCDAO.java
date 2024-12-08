package com.courtorder.model;

import java.sql.*;
import java.sql.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;


public class CourtOrderJDBCDAO implements CourtOrderDAO_interface{
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/dbtiag01?serverTimezone=Asia/Taipei";
	String userid = "root";
	String passwd = "123456";
	
	

    
	private static final String INSERT_STMT =
		    "INSERT INTO court_order_id (member_id, stadium_id, reservation_status, created_at, total_amount, cancel_reason, comment_text, rating) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

		private static final String GET_ALL_STMT =
		    "SELECT stadium_order_id, member_id, stadium_id, reservation_status, created_at, total_amount, cancel_reason, comment_text, rating FROM court_order_id order by stadium_order_id";

		private static final String GET_ONE_STMT =
		    "SELECT stadium_order_id, member_id, stadium_id, reservation_status, created_at, total_amount, cancel_reason, comment_text, rating FROM court_order_id where stadium_order_id = ?";
		
		private static final String DELETE =
		"DELETE FROM court_order_id where stadium_order_id = ?";

		private static final String UPDATE =
		    "UPDATE court_order_id set member_id=?, stadium_id=?, reservation_status=?, created_at=?, total_amount=?, cancel_reason=?, comment_text=?, rating=? where stadium_order_id = ?";
	

		@Override
		public void insert(CourtOrderVO courtOrderVO) {

			Connection con = null;
			PreparedStatement pstmt = null;

			try {

				Class.forName(driver);
				con = DriverManager.getConnection(url, userid, passwd);
				pstmt = con.prepareStatement(INSERT_STMT);

				pstmt.setInt(1, courtOrderVO.getMemberId());
				pstmt.setInt(2, courtOrderVO.getStadiumId());
				pstmt.setBoolean(3, courtOrderVO.getReservationStatus());
				pstmt.setDate(4, courtOrderVO.getCreatedAt());
//////				pstmt.setTimestamp(4, new Timetamp(courtOrderVO.getCreatedAt().getTime()));
				pstmt.setInt(5, courtOrderVO.getTotalAmount());
				pstmt.setString(6, courtOrderVO.getCancelReason());

				pstmt.executeUpdate();

				// Handle any driver errors
			} catch (ClassNotFoundException e) {
				throw new RuntimeException("Couldn't load database driver. "
						+ e.getMessage());
				// Handle any SQL errors
			} catch (SQLException se) {
				throw new RuntimeException("A database error occured. "
						+ se.getMessage());
				// Clean up JDBC resources
			} finally {
				if (pstmt != null) {
					try {
						pstmt.close();
					} catch (SQLException se) {
						se.printStackTrace(System.err);
					}
				}
				if (con != null) {
					try {
						con.close();
					} catch (Exception e) {
						e.printStackTrace(System.err);
					}
				}
			}

		}

		@Override
		public void update(CourtOrderVO courtOrderVO) {

			Connection con = null;
			PreparedStatement pstmt = null;

			try {

				Class.forName(driver);
				con = DriverManager.getConnection(url, userid, passwd);
				pstmt = con.prepareStatement(UPDATE);
				
				pstmt.setInt(1, courtOrderVO.getMemberId());
				pstmt.setInt(2, courtOrderVO.getStadiumId());
				pstmt.setBoolean(3, courtOrderVO.getReservationStatus());
				pstmt.setDate(4, courtOrderVO.getCreatedAt());
////////				pstmt.setTimestamp(4, new Timestamp(courtOrderVO.getCreatedAt().getTime()));
				pstmt.setInt(5, courtOrderVO.getTotalAmount());
				pstmt.setString(6, courtOrderVO.getCancelReason());
				pstmt.setInt(7, courtOrderVO.getStadiumOrderId());

				pstmt.executeUpdate();

				// Handle any driver errors
			} catch (ClassNotFoundException e) {
				throw new RuntimeException("Couldn't load database driver. "
						+ e.getMessage());
				// Handle any SQL errors
			} catch (SQLException se) {
				throw new RuntimeException("A database error occured. "
						+ se.getMessage());
				// Clean up JDBC resources
			} finally {
				if (pstmt != null) {
					try {
						pstmt.close();
					} catch (SQLException se) {
						se.printStackTrace(System.err);
					}
				}
				if (con != null) {
					try {
						con.close();
					} catch (Exception e) {
						e.printStackTrace(System.err);
					}
				}
			}

		}

		@Override
		public void delete(Integer stadiumOrderId) {

			Connection con = null;
			PreparedStatement pstmt = null;

			try {

				Class.forName(driver);
				con = DriverManager.getConnection(url, userid, passwd);
				pstmt = con.prepareStatement(DELETE);

				pstmt.setInt(1, stadiumOrderId);

				pstmt.executeUpdate();

				// Handle any driver errors
			} catch (ClassNotFoundException e) {
				throw new RuntimeException("Couldn't load database driver. "
						+ e.getMessage());
				// Handle any SQL errors
			} catch (SQLException se) {
				throw new RuntimeException("A database error occured. "
						+ se.getMessage());
				// Clean up JDBC resources
			} finally {
				if (pstmt != null) {
					try {
						pstmt.close();
					} catch (SQLException se) {
						se.printStackTrace(System.err);
					}
				}
				if (con != null) {
					try {
						con.close();
					} catch (Exception e) {
						e.printStackTrace(System.err);
					}
				}
			}

		}

		@Override
		public CourtOrderVO findByPrimaryKey(Integer stadiumOrderId) {

			CourtOrderVO courtOrderVO = null;
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			try {

				Class.forName(driver);
				con = DriverManager.getConnection(url, userid, passwd);
				pstmt = con.prepareStatement(GET_ONE_STMT);

				pstmt.setInt(1, stadiumOrderId);

				rs = pstmt.executeQuery();

				while (rs.next()) {
					// empVo 也稱為 Domain objects
					courtOrderVO = new CourtOrderVO();
					courtOrderVO.setStadiumOrderId(rs.getInt("stadium_order_id"));
					courtOrderVO.setMemberId(rs.getInt("member_id"));
					courtOrderVO.setStadiumId(rs.getInt("stadium_id"));
					courtOrderVO.setReservationStatus(rs.getBoolean("reservation_status"));
					courtOrderVO.setCreatedAt(rs.getDate("created_at"));
					courtOrderVO.setTotalAmount(rs.getInt("total_amount"));
					courtOrderVO.setCancelReason(rs.getString("cancel_reason"));
				}

				// Handle any driver errors
			} catch (ClassNotFoundException e) {
				throw new RuntimeException("Couldn't load database driver. "
						+ e.getMessage());
				// Handle any SQL errors
			} catch (SQLException se) {
				throw new RuntimeException("A database error occured. "
						+ se.getMessage());
				// Clean up JDBC resources
			} finally {
				if (rs != null) {
					try {
						rs.close();
					} catch (SQLException se) {
						se.printStackTrace(System.err);
					}
				}
				if (pstmt != null) {
					try {
						pstmt.close();
					} catch (SQLException se) {
						se.printStackTrace(System.err);
					}
				}
				if (con != null) {
					try {
						con.close();
					} catch (Exception e) {
						e.printStackTrace(System.err);
					}
				}
			}
			return courtOrderVO;
		}

		@Override
		public List<CourtOrderVO> getAll() {
			List<CourtOrderVO> list = new ArrayList<CourtOrderVO>();
			CourtOrderVO courtOrderVO = null;

			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			try {

				Class.forName(driver);
				con = DriverManager.getConnection(url, userid, passwd);
				pstmt = con.prepareStatement(GET_ALL_STMT);
				rs = pstmt.executeQuery();

				while (rs.next()) {
					// empVO 也稱為 Domain objects
					courtOrderVO = new CourtOrderVO();
					courtOrderVO.setStadiumOrderId(rs.getInt("stadium_order_id"));
					courtOrderVO.setMemberId(rs.getInt("member_id"));
					courtOrderVO.setStadiumId(rs.getInt("stadium_id"));
					courtOrderVO.setReservationStatus(rs.getBoolean("reservation_status"));
					courtOrderVO.setCreatedAt(rs.getDate("created_at"));
					courtOrderVO.setTotalAmount(rs.getInt("total_amount"));
					courtOrderVO.setCancelReason(rs.getString("cancel_reason"));
					list.add(courtOrderVO);
				}

				// Handle any driver errors
			} catch (ClassNotFoundException e) {
				throw new RuntimeException("Couldn't load database driver. "
						+ e.getMessage());
				// Handle any SQL errors
			} catch (SQLException se) {
				throw new RuntimeException("A database error occured. "
						+ se.getMessage());
				// Clean up JDBC resources
			} finally {
				if (rs != null) {
					try {
						rs.close();
					} catch (SQLException se) {
						se.printStackTrace(System.err);
					}
				}
				if (pstmt != null) {
					try {
						pstmt.close();
					} catch (SQLException se) {
						se.printStackTrace(System.err);
					}
				}
				if (con != null) {
					try {
						con.close();
					} catch (Exception e) {
						e.printStackTrace(System.err);
					}
				}
			}
			return list;
		}

		public static void main(String[] args) {

			CourtOrderJDBCDAO dao = new CourtOrderJDBCDAO();

			// 新增
//			CourtOrderVO courtOrderVO2 = new CourtOrderVO();
//			courtOrderVO2.setMemberId(12);
//			courtOrderVO2.setStadiumId(2);
//			courtOrderVO2.setReservationStatus(true);
//			courtOrderVO2.setCreatedAt(java.sql.Date.valueOf("2005-01-01"));
//			courtOrderVO2.setTotalAmount(60000);
//			courtOrderVO2.setCancelReason("有事無法前往");
//			dao.insert(courtOrderVO2);

			// 修改
//			CourtOrderVO courtOrderVO2 = new CourtOrderVO();
//			courtOrderVO2.setStadiumOrderId(2);
//			courtOrderVO2.setMemberId(12);
//			courtOrderVO2.setStadiumId(2);
//			courtOrderVO2.setReservationStatus(true);
//			courtOrderVO2.setCreatedAt(java.sql.Date.valueOf("2005-01-01"));
//			courtOrderVO2.setTotalAmount(60000);
//			courtOrderVO2.setCancelReason("有事無法前往");
//			dao.update(courtOrderVO2);


			// 刪除
			dao.delete(12);

//			 查詢
//			CourtOrderVO courtOrderVO2 = dao.findByPrimaryKey(2);
//			System.out.print(courtOrderVO2.getStadiumOrderId() + ",");
//			System.out.print(courtOrderVO2.getMemberId() + ",");
//			System.out.print(courtOrderVO2.getStadiumId() + ",");
//			System.out.print(courtOrderVO2.getReservationStatus() + ",");
//			System.out.println(courtOrderVO2.getCreatedAt());
//			System.out.print(courtOrderVO2.getTotalAmount() + ",");
//			System.out.print(courtOrderVO2.getCancelReason() + ",");
//			System.out.println("---------------------");

			// 查詢
			List<CourtOrderVO> list = dao.getAll();
			for (CourtOrderVO aCourtOrder : list) {
				System.out.print(aCourtOrder.getStadiumOrderId() + ",");
				System.out.print(aCourtOrder.getMemberId() + ",");
				System.out.print(aCourtOrder.getStadiumId() + ",");
				System.out.print(aCourtOrder.getReservationStatus() + ",");
				System.out.print(aCourtOrder.getCreatedAt() + ",");
				System.out.print(aCourtOrder.getTotalAmount() + ",");
				System.out.print(aCourtOrder.getCancelReason());
				System.out.println();
			}
		}
}
