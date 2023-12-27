package com.practice.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class MySecurityConfig extends WebSecurityConfigurerAdapter{

	
	//I want to create some details for an user
	//username, password, roles
	//ratikanta, rati123, admin
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private DataSource dataSource;
	
	//This is for the database store.
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// TODO Auto-generated method stub
		//Load the user from database.
		//username, password, role
		auth.jdbcAuthentication().dataSource(dataSource).passwordEncoder(passwordEncoder);
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
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// TODO Auto-generated method stub
		
		http
		.authorizeRequests()
		.antMatchers("/hello").authenticated()
		.antMatchers("/bye").authenticated()
		.antMatchers("/helloWorld").permitAll()
		.antMatchers("/signup").permitAll()
		.and()
		.formLogin()
		.loginPage("/myCustomLogin")
		.and()
		.httpBasic()
		.and().logout();
	}
	
	
	
}