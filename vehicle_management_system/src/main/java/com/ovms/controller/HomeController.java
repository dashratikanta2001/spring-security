package com.ovms.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;

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

	@GetMapping
	public ResponseEntity<?> doAuthorizationSetup() {
		
		HashMap<String,String> map = new LinkedHashMap<>();
		
		map.put("Name", "Vehicle Management System");
		map.put("swagger-ui - enabled", "true");
		map.put("swagger-ui - url", "/swagger-ui.html");
		map.put("TimeStamp", new Date().toLocaleString());
		map.put("status", "200");
		
		return new ResponseEntity<>(map, HttpStatus.OK);
	}
}
