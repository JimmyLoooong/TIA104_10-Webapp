package com.courtorder.model;

import java.util.Date;
import java.util.List;

public class CourtOrderService {
	
	private CourtOrderDAO_interface dao;

	public CourtOrderService() {
		dao = new CourtOrderDAO();
		
	}
	
//    private Integer stadiumOrderId;    // stadium_order_id
//    private Integer memberId;          // member_id
//    private Integer stadiumId;         // stadium_id
//    private Boolean reservationStatus; // reservation_status
//    private Date createdAt;           // created_at
//    private Integer totalAmount;      // total_amount
//    private String cancelReason;      // cancel_reason

	public CourtOrderVO addCourtOrder(Integer memberId, Integer stadiumId, Boolean reservationStatus,
			java.sql.Date createdAt, Integer totalAmount, String cancelReason, String commentText, Integer rating) {

		CourtOrderVO courtOrderVO = new CourtOrderVO();

		courtOrderVO.setMemberId(memberId);
		courtOrderVO.setStadiumId(stadiumId);
		courtOrderVO.setReservationStatus(reservationStatus);
		courtOrderVO.setCreatedAt(createdAt);
		courtOrderVO.setTotalAmount(totalAmount);
		courtOrderVO.setCancelReason(cancelReason);
	    courtOrderVO.setCommentText(commentText);
	    courtOrderVO.setRating(rating);
		dao.insert(courtOrderVO);
		

		return courtOrderVO;
	}

	public CourtOrderVO updateCourtOrder(Integer stadiumOrderId, Integer memberId, Integer stadiumId, Boolean reservationStatus,
			java.sql.Date createdAt, Integer totalAmount, String cancelReason,  String commentText, Integer rating) {

		CourtOrderVO courtOrderVO = new CourtOrderVO();

		courtOrderVO.setStadiumOrderId(stadiumOrderId);
		courtOrderVO.setMemberId(memberId);
		courtOrderVO.setStadiumId(stadiumId);
		courtOrderVO.setReservationStatus(reservationStatus);
		courtOrderVO.setCreatedAt(createdAt);
		courtOrderVO.setTotalAmount(totalAmount);
		courtOrderVO.setCancelReason(cancelReason);
	    courtOrderVO.setCommentText(commentText);
	    courtOrderVO.setRating(rating);
		
		
		dao.update(courtOrderVO);

		return courtOrderVO;
	}

	public void deleteCourtOrder(Integer stadiumOrderId) {
		dao.delete(stadiumOrderId);
	}

	public CourtOrderVO getOneCourtOrder(Integer stadiumOrderId) {
		return dao.findByPrimaryKey(stadiumOrderId);
	}

	public List<CourtOrderVO> getAll() {
		return dao.getAll();
	}

}
