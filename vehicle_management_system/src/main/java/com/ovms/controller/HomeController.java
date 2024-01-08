package com.ovms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ovms.dao.CridentialMasterDao;
import com.ovms.dao.CustomerDao;
import com.ovms.dto.CustomerDto;
import com.ovms.entity.CridentialMaster;
import com.ovms.entity.Customer;
import com.ovms.enums.RoleType;
import com.ovms.service.CustomerService;
import com.ovms.service.RoleService;

import springfox.documentation.annotations.ApiIgnore;

@ApiIgnore
@RestController
@RequestMapping("/")
@PropertySource("classpath:application.properties")
public class HomeController {

	@Autowired
	private RoleService roleService;

	@Autowired
	private CustomerDao customerDao;
	
	@Autowired
	private CridentialMasterDao cridentialMasterDao;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private Environment env;

	@GetMapping("/home")
	public ResponseEntity<?> test() {
		return new ResponseEntity<>("Welcome", HttpStatus.OK);
	}

	@GetMapping
	public ResponseEntity<?> doAuthorizationSetup() {
		if (roleService.findRole(RoleType.ROLE_SUPERADMIN) == null) {
			roleService.addnewRole(RoleType.ROLE_SUPERADMIN);
			roleService.addnewRole(RoleType.ROLE_ADMIN);
			roleService.addnewRole(RoleType.ROLE_MANAGER);
			roleService.addnewRole(RoleType.ROLE_RECEPTIONIST);
			roleService.addnewRole(RoleType.ROLE_USER);

			Customer superAdmin = new Customer();

			superAdmin.setEmail(env.getRequiredProperty("superAdmin.username"));
			superAdmin.setName(env.getRequiredProperty("superAdmin.name"));
			superAdmin.setRole(roleService.findRole(RoleType.ROLE_SUPERADMIN));

			superAdmin = customerDao.save(superAdmin);
			
			
			CridentialMaster cridentialMaster = new CridentialMaster(superAdmin.getEmail(), passwordEncoder.encode(env.getRequiredProperty("superAdmin.password")), superAdmin);
			cridentialMasterDao.addCridential(cridentialMaster);

			return new ResponseEntity<>("Setup completed.", HttpStatus.OK);
		}

		return new ResponseEntity<>("Welcome", HttpStatus.OK);
	}
}
