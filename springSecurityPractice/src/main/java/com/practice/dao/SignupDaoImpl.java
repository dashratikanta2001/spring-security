package com.practice.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.practice.dto.SignupDto;

@Repository
public class SignupDaoImpl implements SignupDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public void saveUser(SignupDto signupDto) {
		// TODO Auto-generated method stub

		String sql = "insert into users values(?,?,?)";
		String sql2 = "insert into authorities values(?,?)";

//		jdbcTemplate.update(sql, signupDto.getUsername(), signupDto.getPassword(),1);
//		jdbcTemplate.update(sql2, signupDto.getUsername(), "Trainer");

		jdbcTemplate.update(sql, ps -> {
			// TODO Auto-generated method stub

			ps.setString(1, signupDto.getUsername());
			ps.setString(2, signupDto.getPassword());
			ps.setInt(3, 1);

		}); 
		
		jdbcTemplate.update(sql2, ps->{
			ps.setString(1, signupDto.getUsername());
			ps.setString(2, "Coder");
		});
	}

}
