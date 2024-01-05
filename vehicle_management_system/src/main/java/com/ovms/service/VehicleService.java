package com.ovms.service;

import java.util.List;

import com.ovms.dto.VehicleDto;
import com.ovms.response.CustomeResponse;

public interface VehicleService {

	CustomeResponse<VehicleDto> addNewVehicle(VehicleDto vehicleDto);

	CustomeResponse<List<VehicleDto>> findByShowroomId(Integer id);

}
