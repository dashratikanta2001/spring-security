package com.ovms.util;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;

import com.ovms.dto.CustomerDto;
import com.ovms.entity.Customer;

public class ObjectUtil {

	
	
	  private static  ModelMapper modelMapper ;

	    public static CustomerDto convertToDto(Customer customer) {
	        return modelMapper.map(customer, CustomerDto.class);
	    }

	    public static List<CustomerDto> convertToDtoList(List<Customer> customers) {
	        return modelMapper.map(customers, new TypeToken<List<CustomerDto>>() {}.getType());
	    }

	    public static Optional<CustomerDto> convertToDtoOptional(Optional<Customer> customerOptional) {
	        return customerOptional.map(customer -> modelMapper.map(customer, CustomerDto.class));
	    }
	
}
