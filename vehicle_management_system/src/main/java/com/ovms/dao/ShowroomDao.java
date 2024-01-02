package com.ovms.dao;

import java.util.List;

import com.ovms.entity.Showroom;

public interface ShowroomDao {

	Showroom save(Showroom showroom);
	
	List<Showroom> findAll();
	
	List<Showroom> findByType(String type);
	
}
