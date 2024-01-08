package com.ovms.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ovms.dao.CridentialMasterDao;
import com.ovms.dao.CustomerDao;
import com.ovms.entity.CridentialMaster;
import com.ovms.entity.Customer;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
//	private CustomerDao customerDao;
	private CridentialMasterDao cridentialMasterDao;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		
//		Customer customer = customerDao.findByEmail(username);
		
		CridentialMaster cridentialMaster = cridentialMasterDao.getByUsername(username);
		
//		String password ="$2a$10$2YM59INLBap1vjbjEEZnwuS9t7lYCvK7XlVCX1eFtCCF5ESgvVZxe";
		
		if (cridentialMaster !=null) {
			
			List<GrantedAuthority> authorities = new ArrayList<>();
			
			authorities.add(new SimpleGrantedAuthority(cridentialMaster.getCustomer().getRole().getRole()));
			return new User(cridentialMaster.getUsername(), cridentialMaster.getPassword(), authorities);
		}
		
		throw new UsernameNotFoundException("Username not found.");
	}

}
