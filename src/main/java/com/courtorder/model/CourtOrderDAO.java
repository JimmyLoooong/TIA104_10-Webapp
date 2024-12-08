package com.courtorder.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class CourtOrderDAO implements CourtOrderDAO_interface{

    // 一個應用程式中,針對一個資料庫 ,共用一個DataSource即可
    private static DataSource ds = null;
    static {
        try {
            Context ctx = new InitialContext();
            ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB4");
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

    private static final String INSERT_STMT =
        "INSERT INTO court_order_id (member_id, stadium_id, reservation_status, created_at, total_amount, cancel_reason, comment_text, rating) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

    private static final String GET_ALL_STMT =
        "SELECT stadium_order_id, member_id, stadium_id, reservation_status, created_at, total_amount, cancel_reason, comment_text, rating FROM court_order_id ORDER BY stadium_order_id";

    private static final String GET_ONE_STMT =
        "SELECT stadium_order_id, member_id, stadium_id, reservation_status, created_at, total_amount, cancel_reason, comment_text, rating FROM court_order_id WHERE stadium_order_id = ?";

    private static final String DELETE =
        "DELETE FROM court_order_id WHERE stadium_order_id = ?";

    private static final String UPDATE =
        "UPDATE court_order_id SET member_id = ?, stadium_id = ?, reservation_status = ?, created_at = ?, total_amount = ?, cancel_reason = ?, comment_text = ?, rating = ? WHERE stadium_order_id = ?";

    @Override
    public void insert(CourtOrderVO courtOrderVO) {
        Connection con = null;
        PreparedStatement pstmt = null;

        try {
            con = ds.getConnection();
            pstmt = con.prepareStatement(INSERT_STMT);

            // 處理一般欄位
            pstmt.setInt(1, courtOrderVO.getMemberId());
            pstmt.setInt(2, courtOrderVO.getStadiumId());
            pstmt.setBoolean(3, courtOrderVO.getReservationStatus());
            pstmt.setDate(4, courtOrderVO.getCreatedAt());
            pstmt.setInt(5, courtOrderVO.getTotalAmount());
            pstmt.setString(6, courtOrderVO.getCancelReason());
            pstmt.setString(7, courtOrderVO.getCommentText());

            // 特別處理 rating 欄位
            if (courtOrderVO.getRating() == null) {
                pstmt.setNull(8, java.sql.Types.INTEGER);
            } else {
                pstmt.setInt(8, courtOrderVO.getRating());
            }

            pstmt.executeUpdate();

        } catch (SQLException se) {
            throw new RuntimeException("A database error occured. " + se.getMessage());
        } finally {
            if (pstmt != null) {
                try { pstmt.close(); } catch (SQLException se) { se.printStackTrace(System.err); }
            }
            if (con != null) {
                try { con.close(); } catch (Exception e) { e.printStackTrace(System.err); }
            }
        }
    }

    @Override
    public void update(CourtOrderVO courtOrderVO) {
        Connection con = null;
        PreparedStatement pstmt = null;

        try {
            con = ds.getConnection();
            pstmt = con.prepareStatement(UPDATE);
            
            pstmt.setInt(1, courtOrderVO.getMemberId());
            pstmt.setInt(2, courtOrderVO.getStadiumId());
            pstmt.setBoolean(3, courtOrderVO.getReservationStatus());
            pstmt.setDate(4, courtOrderVO.getCreatedAt());
            pstmt.setInt(5, courtOrderVO.getTotalAmount());
            pstmt.setString(6, courtOrderVO.getCancelReason());
            pstmt.setString(7, courtOrderVO.getCommentText());

            // 特別處理 rating 欄位
            if (courtOrderVO.getRating() == null) {
                pstmt.setNull(8, java.sql.Types.INTEGER);
            } else {
                pstmt.setInt(8, courtOrderVO.getRating());
            }

            pstmt.setInt(9, courtOrderVO.getStadiumOrderId());

            pstmt.executeUpdate();

        } catch (SQLException se) {
            throw new RuntimeException("A database error occured. " + se.getMessage());
        } finally {
            if (pstmt != null) {
                try { pstmt.close(); } catch (SQLException se) { se.printStackTrace(System.err); }
            }
            if (con != null) {
                try { con.close(); } catch (Exception e) { e.printStackTrace(System.err); }
            }
        }
    }

    @Override
    public void delete(Integer stadiumOrderId) {
        Connection con = null;
        PreparedStatement pstmt = null;

        try {
            con = ds.getConnection();
            pstmt = con.prepareStatement(DELETE);

            pstmt.setInt(1, stadiumOrderId);

            pstmt.executeUpdate();

        } catch (SQLException se) {
            throw new RuntimeException("A database error occured. " + se.getMessage());
        } finally {
            if (pstmt != null) {
                try { pstmt.close(); } catch (SQLException se) { se.printStackTrace(System.err); }
            }
            if (con != null) {
                try { con.close(); } catch (Exception e) { e.printStackTrace(System.err); }
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
            con = ds.getConnection();
            pstmt = con.prepareStatement(GET_ONE_STMT);

            pstmt.setInt(1, stadiumOrderId);

            rs = pstmt.executeQuery();

            if (rs.next()) {
                courtOrderVO = new CourtOrderVO();
                courtOrderVO.setStadiumOrderId(rs.getInt("stadium_order_id"));
                courtOrderVO.setMemberId(rs.getInt("member_id"));
                courtOrderVO.setStadiumId(rs.getInt("stadium_id"));
                courtOrderVO.setReservationStatus(rs.getBoolean("reservation_status"));
                courtOrderVO.setCreatedAt(rs.getDate("created_at"));
                courtOrderVO.setTotalAmount(rs.getInt("total_amount"));
                courtOrderVO.setCancelReason(rs.getString("cancel_reason"));
                courtOrderVO.setCommentText(rs.getString("comment_text"));
                
                // 正確處理 rating 欄位
                int rating = rs.getInt("rating");
                if (rs.wasNull()) {
                    courtOrderVO.setRating(null);
                } else {
                    courtOrderVO.setRating(rating);
                }
            }

        } catch (SQLException se) {
            throw new RuntimeException("A database error occured. " + se.getMessage());
        } finally {
            if (rs != null) {
                try { rs.close(); } catch (SQLException se) { se.printStackTrace(System.err); }
            }
            if (pstmt != null) {
                try { pstmt.close(); } catch (SQLException se) { se.printStackTrace(System.err); }
            }
            if (con != null) {
                try { con.close(); } catch (Exception e) { e.printStackTrace(System.err); }
            }
        }
        return courtOrderVO;
    }

    @Override
    public List<CourtOrderVO> getAll() {
        List<CourtOrderVO> list = new ArrayList<>();
        CourtOrderVO courtOrderVO = null;

        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            con = ds.getConnection();
            pstmt = con.prepareStatement(GET_ALL_STMT);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                courtOrderVO = new CourtOrderVO();
                courtOrderVO.setStadiumOrderId(rs.getInt("stadium_order_id"));
                courtOrderVO.setMemberId(rs.getInt("member_id"));
                courtOrderVO.setStadiumId(rs.getInt("stadium_id"));
                courtOrderVO.setReservationStatus(rs.getBoolean("reservation_status"));
                courtOrderVO.setCreatedAt(rs.getDate("created_at"));
                courtOrderVO.setTotalAmount(rs.getInt("total_amount"));
                courtOrderVO.setCancelReason(rs.getString("cancel_reason"));
                courtOrderVO.setCommentText(rs.getString("comment_text"));
                
                // 正確處理 rating 欄位
                int rating = rs.getInt("rating");
                if (rs.wasNull()) {
                    courtOrderVO.setRating(null);
                } else {
                    courtOrderVO.setRating(rating);
                }
                
                list.add(courtOrderVO);
            }

        } catch (SQLException se) {
            throw new RuntimeException("A database error occured. " + se.getMessage());
        } finally {
            if (rs != null) {
                try { rs.close(); } catch (SQLException se) { se.printStackTrace(System.err); }
            }
            if (pstmt != null) {
                try { pstmt.close(); } catch (SQLException se) { se.printStackTrace(System.err); }
            }
            if (con != null) {
                try { con.close(); } catch (Exception e) { e.printStackTrace(System.err); }
            }
        }
        return list;
    }
}