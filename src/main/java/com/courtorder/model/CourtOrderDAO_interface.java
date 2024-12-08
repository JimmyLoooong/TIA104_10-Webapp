package com.courtorder.model;

import java.util.*;

public interface CourtOrderDAO_interface {
	
    public void insert(CourtOrderVO courtOrderVO);
    public void update(CourtOrderVO courtOrderVO);
    public void delete(Integer stadiumOrderId);
    public CourtOrderVO findByPrimaryKey(Integer stadiumOrderId);
    public List<CourtOrderVO> getAll();

    //萬用複合查詢(傳入參數型態Map)(回傳 List)
//  public List<EmpVO> getAll(Map<String, String[]> map); 
    
} 