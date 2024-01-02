package com.ovms.service;

import java.util.List;

import com.ovms.dto.ShowroomDto;
import com.ovms.response.CustomeResponse;

public interface ShowroomService {

	CustomeResponse<?> save(ShowroomDto showroomDto);
	
//	ShowroomDto update(Integer id, ShowroomDto showroomDto);
	
	CustomeResponse<?> findAll ();
	
	CustomeResponse<?> findByVehicleType(String type);
}
