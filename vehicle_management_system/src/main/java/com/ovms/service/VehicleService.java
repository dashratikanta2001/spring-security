package com.ovms.service;

import com.ovms.dto.VehicleDto;
import com.ovms.response.CustomeResponse;

public interface VehicleService {

	CustomeResponse<VehicleDto> addNewVehicle(VehicleDto vehicleDto);

}
