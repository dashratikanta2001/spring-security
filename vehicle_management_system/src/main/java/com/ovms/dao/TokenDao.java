package com.ovms.dao;

import com.ovms.entity.Token;

public interface TokenDao {

	void addToken(String useraname, String token);
	
	void deleteToken(String username);
	
	Token getTokenByusername(String username);
	
	Boolean tokenAvailable(String username, String token);
	
}
