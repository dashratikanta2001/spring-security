package com.ovms.authorize;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.ovms.dao.CustomerDao;
import com.ovms.entity.Customer;
import com.ovms.enums.RoleType;
import com.ovms.exception.UnauthorizeException;
import com.ovms.response.CustomeResponse;
import com.ovms.util.JwtUtil;

@Component
public class Authorization {

	@Autowired
	private JwtUtil jwtUtil;
	
	@Autowired
	private CustomerDao customerDao;
	
	
	public CustomeResponse<?> authorizeToAddCustomer(String token)
	{
		String usernameFromToken = jwtUtil.getUsernameFromToken(token);
		
		Customer customer = customerDao.findByEmail(usernameFromToken);
		
		if (customer !=null) {
			
						
			if (customer.getRole().getRole().equals(RoleType.ROLE_RECEPTIONIST.toString()) || customer.getRole().getRole().equals(RoleType.ROLE_MANAGER.toString())) {
				return new CustomeResponse<>(null, HttpStatus.OK.value(), "Valid user");
			}
		}
		
//		return new CustomeResponse<>(null, HttpStatus.UNAUTHORIZED.value(), "Unauthorized");
		
		throw new UnauthorizeException("Unauthorized to add new user");
	}
	
	
}
