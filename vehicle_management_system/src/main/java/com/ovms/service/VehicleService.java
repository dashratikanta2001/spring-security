package com.ovms.service;

import java.util.List;

import com.ovms.dto.VehicleDto;
import com.ovms.response.CustomeResponse;

public interface VehicleService {

	CustomeResponse<VehicleDto> addNewVehicle(VehicleDto vehicleDto);

	CustomeResponse<List<VehicleDto>> findByShowroomId(Integer id, Boolean registered);

	CustomeResponse<VehicleDto> findByVehicleNumber(String vehicleNumber);

	CustomeResponse<VehicleDto> registerVehicleToUser(Integer v_id, Integer u_id);

}
