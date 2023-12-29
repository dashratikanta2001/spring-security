package com.practice.config;

import java.util.ArrayList;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import com.practice.service.CustomerUserDetailsServiceImpl;

@EnableWebSecurity
public class MySecurityConfig extends WebSecurityConfigurerAdapter{

	
	//I want to create some details for an user
	//username, password, roles
	//ratikanta, rati123, admin
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private DataSource dataSource;
	
	@Autowired
	private CustomerUserDetailsServiceImpl userDetailsServiceImpl;
	
	
	//This is for the database store.
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// TODO Auto-generated method stub
		//Load the user from database.
		//username, password, role
//		auth
//		.jdbcAuthentication()
//		.dataSource(dataSource)
//		.usersByUsernameQuery("select username, password, enabled from customers where username=?")
//		.authoritiesByUsernameQuery("select username,roles from customers where username=?")
//		.passwordEncoder(passwordEncoder);
		
		auth.userDetailsService(userDetailsServiceImpl).passwordEncoder(passwordEncoder);
	}
	
	
	
	
	/*
	 //This is for the inmemory store
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// TODO Auto-generated method stub
		
		auth
		.inMemoryAuthentication()
		.withUser("ratikanta")
		.password("$2a$10$JSGyMMl0By0ncnPwhpNPj.Z1VhrmsqymLTSmzpbP3kztTcTx6Vcv6")//rati
		.roles("admin")
		.and()
		.withUser("kartik")
		.password("$2a$10$YBJ11NDkxURxLtr88/vKUuFR2YcY0W/QHUuggYV68RcEHixrSXbsy")//kartik123
		.roles("user");
		
//		System.out.println("My password encoded value "+passwordEncoder.encode("kartik123"));
		
		
	}
	 */
	
	/*
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// TODO Auto-generated method stub
		//Inmemory auth
//		ArrayList<GrantedAuthority> roles = new ArrayList<>();
//		
//		SimpleGrantedAuthority role1 = new SimpleGrantedAuthority("ADMIN");
//		SimpleGrantedAuthority role2 = new SimpleGrantedAuthority("USER");
//		
//		roles.add(role1);
//		roles.add(role2);
//		User rati = new User("rati@gmail.com", "rati123", roles);
		
		
		InMemoryUserDetailsManager userDetailsManager = new InMemoryUserDetailsManager();
		
		
		UserDetails ratiUser = User.withUsername("rati").password("rati123").roles("ADMIN","USER").build();
		UserDetails kartikUser = User.withUsername("kartik").password("kartik123").roles("USER").build();
		
		userDetailsManager.createUser(ratiUser);
		userDetailsManager.createUser(kartikUser);
		
		auth.userDetailsService(userDetailsManager);
		
	}
	*/
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// TODO Auto-generated method stub
		
		http
		.authorizeRequests()
		.antMatchers("/coder").hasAuthority("Coder")
		.antMatchers("/trainer").hasAuthority("Trainer")
		.antMatchers("/signup","/process-signup").permitAll()
		.anyRequest()
		.authenticated()
		.and()
		.formLogin()
		.loginPage("/myCustomLogin").permitAll()
		.and()
		.httpBasic()
		.and().logout().permitAll()
		.and().exceptionHandling().accessDeniedPage("/accessDenied");
	}
	
	
	
}
