package com.practice.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@EnableWebMvc
@ComponentScan("com.practice")
public class MyAppConfig implements WebMvcConfigurer{

	@Bean
	InternalResourceViewResolver viewResolver()
	{
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		
		viewResolver.setPrefix("/WEB-INF/views/");
		viewResolver.setSuffix(".jsp");
		
		return viewResolver;
	}
	
	@Bean
	public DataSource dataSource()
	{
		DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();

		driverManagerDataSource.setUrl("jdbc:mysql://localhost:3306/justTesting");
		driverManagerDataSource.setUsername("root");
		driverManagerDataSource.setPassword("Admin@123");
		driverManagerDataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
		
		return driverManagerDataSource;
	}
	
	
	@Bean
	public JdbcTemplate jdbcTemplate()
	{
		JdbcTemplate jdbcTemplate = new JdbcTemplate();
		jdbcTemplate.setDataSource(dataSource());
		return jdbcTemplate;
	}
	
//	@Bean
//	public JdbcUserDetailsManager jdbcUserDetailsManager()
//	{
//		return new JdbcUserDetailsManager(dataSource());
//	}
	
	
	
	
	
	
	
	
//	
//	@Bean
//	PasswordEncoder getPasswordEncoder() {
//		// TODO Auto-generated method stub
////		return NoOpPasswordEncoder.getInstance();
//		return new BCryptPasswordEncoder();
//	}
	
	@Bean
	PasswordEncoder getPasswordEncoder() {
		// TODO Auto-generated method stub
//		return NoOpPasswordEncoder.getInstance();
		return NoOpPasswordEncoder.getInstance();
	}
	
	@Bean
	public JdbcUserDetailsManager jdbcUserDetailsManager()
	{
		JdbcUserDetailsManager userDetailsManager = new JdbcUserDetailsManager(dataSource());
		
		userDetailsManager.setUsersByUsernameQuery("select username, password, enabled from customers where username=?");
		userDetailsManager.setAuthoritiesByUsernameQuery("select username,roles from customers where username=?");
		userDetailsManager.setChangePasswordSql("update customers set password = ? where username = ?");
		userDetailsManager.setDeleteUserSql("delete from customers where username = ?");
		userDetailsManager.setDeleteUserAuthoritiesSql("delete from customers where username = ?");
		return userDetailsManager;
	}
	
	
	
}
