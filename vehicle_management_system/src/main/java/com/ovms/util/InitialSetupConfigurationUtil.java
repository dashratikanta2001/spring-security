package com.ovms.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.ovms.dao.CridentialMasterDao;
import com.ovms.dao.CustomerDao;
import com.ovms.entity.CridentialMaster;
import com.ovms.entity.Customer;
import com.ovms.enums.RoleType;
import com.ovms.service.RoleService;

@Component
public class InitialSetupConfigurationUtil implements ApplicationListener<ApplicationEvent> {

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
	
	private static boolean isExecuted;

	@Override
	public void onApplicationEvent(ApplicationEvent event) {
		// TODO Auto-generated method stub
		if (!isExecuted) { 
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

				if (cridentialMasterDao.getByUsername(env.getRequiredProperty("superAdmin.username")) == null) {
					superAdmin = customerDao.save(superAdmin);

					CridentialMaster cridentialMaster = new CridentialMaster(superAdmin.getEmail(),
							passwordEncoder.encode(env.getRequiredProperty("superAdmin.password")), superAdmin);
					cridentialMasterDao.addCridential(cridentialMaster);

				}

			}
			isExecuted = true;
		}
	}

}
