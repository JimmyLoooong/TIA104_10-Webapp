package com.courtorder.controller;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

import com.courtorder.model.*;

public class CourtOrderServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		
		if ("getOne_For_Display_Court_Order".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String str = req.getParameter("stadiumOrderId");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入場館訂單編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/courtorder/select_page_court_order.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				Integer stadiumOrderId = null;
				try {
					stadiumOrderId = Integer.valueOf(str);
				} catch (Exception e) {
					errorMsgs.add("場館訂單編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/courtorder/select_page_court_order.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				CourtOrderService courtOrderSvc = new CourtOrderService();
				CourtOrderVO courtOrderVO = courtOrderSvc.getOneCourtOrder(stadiumOrderId);
				if (courtOrderVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/courtorder/select_page_court_order.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("courtOrderVO", courtOrderVO); // 資料庫取出的empVO物件,存入req
				String url = "/back-end/courtorder/listOneCourtOrder.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);
		        // 確認是否進入了頁面
		        System.out.println("Debug: 查詢成功");
			    
		}  
		
		
		if ("getOne_For_Update".equals(action)) { // 來自listAllEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
				/***************************1.接收請求參數****************************************/
				Integer stadiumOrderId = Integer.valueOf(req.getParameter("stadiumOrderId"));
				
				/***************************2.開始查詢資料****************************************/
				CourtOrderService courtOrderSvc = new CourtOrderService();
				CourtOrderVO courtOrderVO = courtOrderSvc.getOneCourtOrder(stadiumOrderId);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("courtOrderVO", courtOrderVO);         // 資料庫取出的empVO物件,存入req
				String url = "/back-end/courtorder/update_court_order_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				successView.forward(req, res);

		}
		



		if ("update".equals(action)) { 
		    System.out.println("Debug: 開始修改");
		    List<String> errorMsgs = new LinkedList<String>();
		    req.setAttribute("errorMsgs", errorMsgs);

		    try {
		        /***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
		        Integer stadiumOrderId = Integer.valueOf(req.getParameter("stadiumOrderId"));
		        Integer memberId = Integer.valueOf(req.getParameter("memberId"));
		        Integer stadiumId = Integer.valueOf(req.getParameter("stadiumId"));
		        Boolean reservationStatus = Boolean.valueOf(req.getParameter("reservationStatus"));
		        
		        java.sql.Date createdAt = null;
		        try {
		            createdAt = java.sql.Date.valueOf(req.getParameter("createdAt").trim());
		        } catch (IllegalArgumentException e) {
		            createdAt = new java.sql.Date(System.currentTimeMillis());
		            errorMsgs.add("請輸入日期!");
		        }
		        
		        Integer totalAmount = Integer.valueOf(req.getParameter("totalAmount"));
		        String cancelReason = req.getParameter("cancelReason");
		        
		        // 處理評論
		        String commentText = req.getParameter("commentText");
		        if (commentText == null) {
		            commentText = ""; 
		        }
		        
		        // 處理評分 - 簡化版本
		        Integer rating = null;
		        String ratingStr = req.getParameter("rating");
		        if (ratingStr != null && !ratingStr.trim().isEmpty()) {
		            rating = Integer.valueOf(ratingStr);
		        }
		        // 因為是下拉選單，不需要驗證範圍
		        
		        CourtOrderVO courtOrderVO = new CourtOrderVO();
		        courtOrderVO.setStadiumOrderId(stadiumOrderId);
		        courtOrderVO.setMemberId(memberId);
		        courtOrderVO.setStadiumId(stadiumId);
		        courtOrderVO.setReservationStatus(reservationStatus);
		        courtOrderVO.setCreatedAt(createdAt);
		        courtOrderVO.setTotalAmount(totalAmount);
		        courtOrderVO.setCancelReason(cancelReason);
		        courtOrderVO.setCommentText(commentText);
		        courtOrderVO.setRating(rating);  // 可以是 null

		        // Send the use back to the form, if there were errors
		        if (!errorMsgs.isEmpty()) {
		            req.setAttribute("courtOrderVO", courtOrderVO); 
		            RequestDispatcher failureView = req
		                    .getRequestDispatcher("/back-end/courtorder/update_court_order_input.jsp");
		            failureView.forward(req, res);
		            return;
		        }
		        
		        /***************************2.開始修改資料*****************************************/
		        CourtOrderService courtOrderSvc = new CourtOrderService();
		        courtOrderVO = courtOrderSvc.updateCourtOrder(
		            stadiumOrderId, 
		            memberId, 
		            stadiumId, 
		            reservationStatus, 
		            createdAt, 
		            totalAmount, 
		            cancelReason,
		            commentText,
		            rating  // 可以是 null
		        );
		        
		        /***************************3.修改完成,準備轉交(Send the Success view)*************/
		        req.setAttribute("courtOrderVO", courtOrderVO);
		        String url = "/back-end/courtorder/listOneCourtOrder.jsp";
		        RequestDispatcher successView = req.getRequestDispatcher(url);
		        successView.forward(req, res);

		    } catch (Exception e) {
		        errorMsgs.add("修改資料失敗:" + e.getMessage());
		        RequestDispatcher failureView = req
		                .getRequestDispatcher("/back-end/courtorder/update_court_order_input.jsp");
		        failureView.forward(req, res);
		    }
		} 


		if ("insert".equals(action)) { // 來自addCourtOrder.jsp的請求
		    
		    List<String> errorMsgs = new LinkedList<String>();
		    req.setAttribute("errorMsgs", errorMsgs);

		    try {
		        /*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
		        // 會員編號驗證
		        String memberIdStr = req.getParameter("memberId");
		        Integer memberId = null;
		        if (memberIdStr == null || memberIdStr.trim().length() == 0) {
		            errorMsgs.add("會員編號: 請勿空白");
		        } else {
		            try {
		                memberId = Integer.valueOf(memberIdStr);
		            } catch (NumberFormatException e) {
		                errorMsgs.add("會員編號請填數字");
		            }
		        }

		        // 場館編號驗證
		        String stadiumIdStr = req.getParameter("stadiumId");
		        Integer stadiumId = null;
		        if (stadiumIdStr == null || stadiumIdStr.trim().length() == 0) {
		            errorMsgs.add("場館編號: 請勿空白");
		        } else {
		            try {
		                stadiumId = Integer.valueOf(stadiumIdStr);
		            } catch (NumberFormatException e) {
		                errorMsgs.add("場館編號請填數字");
		            }
		        }

		        // 預約狀態驗證
		        Boolean reservationStatus = Boolean.valueOf(req.getParameter("reservationStatus"));

		        // 訂單成立日期驗證
		        java.sql.Date createdAt = null;
		        try {
		            createdAt = java.sql.Date.valueOf(req.getParameter("createdAt").trim());
		        } catch (IllegalArgumentException e) {
		            createdAt = new java.sql.Date(System.currentTimeMillis());
		            errorMsgs.add("請輸入日期!");
		        }

		        // 訂單總金額驗證
		        String totalAmountStr = req.getParameter("totalAmount");
		        Integer totalAmount = null;
		        if (totalAmountStr == null || totalAmountStr.trim().length() == 0) {
		            errorMsgs.add("訂單總金額: 請勿空白");
		        } else {
		            try {
		                totalAmount = Integer.valueOf(totalAmountStr);
		                if (totalAmount < 0) {
		                    errorMsgs.add("訂單總金額不能小於0");
		                }
		            } catch (NumberFormatException e) {
		                errorMsgs.add("訂單總金額請填數字");
		            }
		        }

		        // 取消原因驗證 (可為空)
		        String cancelReason = req.getParameter("cancelReason");
		        if (cancelReason == null) {
		            cancelReason = ""; // 若為null則設為空字串
		        }
		        
                // 新增評論和評分的處理
		        String commentText = req.getParameter("commentText");
		        if (commentText == null) {
		            commentText = ""; 
		        }
                
		        // 處理評分 - 當選擇"未評等"時，rating會是null
		        Integer rating = null;
		        String ratingStr = req.getParameter("rating");
		        // 只有當選擇了實際的星等（非"未評等"）時才設定rating值
		        if (ratingStr != null && !ratingStr.isEmpty()) {
		            rating = Integer.valueOf(ratingStr);
		        }

		        CourtOrderVO courtOrderVO = new CourtOrderVO();
		        courtOrderVO.setMemberId(memberId);
		        courtOrderVO.setStadiumId(stadiumId);
		        courtOrderVO.setReservationStatus(reservationStatus);
		        courtOrderVO.setCreatedAt(createdAt);
		        courtOrderVO.setTotalAmount(totalAmount);
		        courtOrderVO.setCancelReason(cancelReason);
		        courtOrderVO.setCommentText(commentText);
		        courtOrderVO.setRating(rating); // 若選擇"未評等"，這裡會是null


		        // 若有錯誤，將courtOrderVO物件存入req，並轉交回新增頁面
		        if (!errorMsgs.isEmpty()) {
		            req.setAttribute("courtOrderVO", courtOrderVO);
		            RequestDispatcher failureView = req
		                    .getRequestDispatcher("/back-end/courtorder/addCourtOrder.jsp");
		            failureView.forward(req, res);
		            return;
		        }

		        /*************************** 2.開始新增資料 ***************************************/
		        CourtOrderService courtOrderSvc = new CourtOrderService();
		        courtOrderVO = courtOrderSvc.addCourtOrder(memberId, stadiumId, reservationStatus, 
		                                                 createdAt, totalAmount, cancelReason, commentText, rating);

		        /*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
		        String url = "/back-end/courtorder/listAllCourtOrder.jsp";
		        RequestDispatcher successView = req.getRequestDispatcher(url);
		        successView.forward(req, res);

		    } catch (Exception e) {
		        errorMsgs.add(e.getMessage());
		        RequestDispatcher failureView = req
		                .getRequestDispatcher("/back-end/courtorder/addCourtOrder.jsp");
		        failureView.forward(req, res);
		    }
		}

		
		if ("delete".equals(action)) { // 來自listAllEmp.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
	
				/***************************1.接收請求參數***************************************/
				Integer stadiumOrderId = Integer.valueOf(req.getParameter("stadiumOrderId"));
				
				/***************************2.開始刪除資料***************************************/
				CourtOrderService courtOrderSvc = new CourtOrderService();
				courtOrderSvc.deleteCourtOrder(stadiumOrderId);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				String url = "/back-end/courtorder/listAllCourtOrder.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
			

		}


		
	}
}
