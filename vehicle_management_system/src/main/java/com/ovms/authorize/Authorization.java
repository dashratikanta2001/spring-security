package com.ovms.authorize;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.ovms.dao.CustomerDao;
import com.ovms.dao.RoleDao;
import com.ovms.entity.Customer;
import com.ovms.entity.Role;
import com.ovms.enums.RoleType;
import com.ovms.exception.CustomUnauthorizeException;
import com.ovms.response.CustomeResponse;
import com.ovms.util.JwtUtil;

@Component
public class Authorization {

	@Autowired
	private JwtUtil jwtUtil;

	@Autowired
	private CustomerDao customerDao;

	@Autowired
	private RoleDao roleDao;

	public CustomeResponse<?> authorizeToAddOrUpdateCustomer(String token) {
//		String usernameFromToken = jwtUtil.getUsernameFromToken(token);

		String role = jwtUtil.getRoleFromToken(token);

//		Customer customer = customerDao.findByEmail(usernameFromToken);

		if (role.equals(RoleType.ROLE_RECEPTIONIST.toString()) || role.equals(RoleType.ROLE_MANAGER.toString())) {
			return new CustomeResponse<>(null, HttpStatus.OK.value(), "Valid user");
		}
		throw new CustomUnauthorizeException("Unauthorized to add new user");
	}
	
	
	
	
	public CustomeResponse<?> authorizeToViewDetails(String token) {
//		String usernameFromToken = jwtUtil.getUsernameFromToken(token);
		
		String role = jwtUtil.getRoleFromToken(token);
		
//		Customer customer = customerDao.findByEmail(usernameFromToken);
		
		if (!role.equals(RoleType.ROLE_USER.toString())) {
			return new CustomeResponse<>(null, HttpStatus.OK.value(), "Valid user");
		}
		throw new CustomUnauthorizeException("Unauthorized to view details");
	}

	
	
	
	
	public CustomeResponse<?> authorizeToAddRole(String token, Integer customerId, Integer roleId) {

		String usernameFromToken = jwtUtil.getUsernameFromToken(token);

		Customer customer = customerDao.findByEmail(usernameFromToken);

		if (customer == null) {
			throw new UsernameNotFoundException("Invalid Token");
		}
		if (customer.getId() == customerId) {
			return new CustomeResponse<>(null, HttpStatus.UNAUTHORIZED.value(), "You can't update your role");
		}

		Role role = roleDao.findRoleById(roleId);
		if (role == null) {
			throw new UsernameNotFoundException("Invalid role");
		}

		if (role.getRole().equals(RoleType.ROLE_SUPERADMIN.toString())) {

			return new CustomeResponse<>(null, HttpStatus.UNAUTHORIZED.value(), "SUPERADMIN can not be added.");

		} else if (customer.getRole().getRole().equals(RoleType.ROLE_SUPERADMIN.toString())) {

			if (role.getRole().equals(RoleType.ROLE_SUPERADMIN.toString())) {

				return new CustomeResponse<>(null, HttpStatus.UNAUTHORIZED.value(),
						"Multiple SUPERADMIN can't be added.");
			}
			return new CustomeResponse<>(null, HttpStatus.OK.value(), "Authorized.");
		} else if (customer.getRole().getRole().equals(RoleType.ROLE_ADMIN.toString())) {

			if (role.getRole().equals(RoleType.ROLE_SUPERADMIN.toString())) {
				return new CustomeResponse<>(null, HttpStatus.UNAUTHORIZED.value(), "You can't add SUPERADMIN");
			}

			return new CustomeResponse<>(null, HttpStatus.OK.value(), "Authorized.");

		} else if (customer.getRole().getRole().equals(RoleType.ROLE_MANAGER.toString())) {

			if (role.getRole().equals(RoleType.ROLE_ADMIN.toString())) {
				return new CustomeResponse<>(null, HttpStatus.UNAUTHORIZED.value(), "You can't add ADMIN");
			}

			return new CustomeResponse<>(null, HttpStatus.OK.value(), "Authorized.");
		}

		return new CustomeResponse<>(null, HttpStatus.UNAUTHORIZED.value(), "Unauthorized.");
	}




	public CustomeResponse<?> authorizeToAddBrand(String token) {
		// TODO Auto-generated method stub
//		String usernameFromToken = jwtUtil.getUsernameFromToken(token);

		String role = jwtUtil.getRoleFromToken(token);

//		Customer customer = customerDao.findByEmail(usernameFromToken);

		if (role.equals(RoleType.ROLE_SUPERADMIN.toString()) || role.equals(RoleType.ROLE_ADMIN.toString())) {
			return new CustomeResponse<>(null, HttpStatus.OK.value(), "Valid user");
		}
		throw new CustomUnauthorizeException("Unauthorized.");
	}




	public CustomeResponse<?> authorizeToAddVehicle(String token) {
		// TODO Auto-generated method stub
//		String usernameFromToken = jwtUtil.getUsernameFromToken(token);

		String role = jwtUtil.getRoleFromToken(token);

//		Customer customer = customerDao.findByEmail(usernameFromToken);

		if (role.equals(RoleType.ROLE_ADMIN.toString()) || role.equals(RoleType.ROLE_MANAGER.toString())) {
			return new CustomeResponse<>(null, HttpStatus.OK.value(), "Valid user");
		}
		throw new CustomUnauthorizeException("Unauthorized.");
	}

}
