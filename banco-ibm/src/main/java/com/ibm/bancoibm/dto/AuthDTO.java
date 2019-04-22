package com.ibm.bancoibm.dto;

public class AuthDTO {
	
	private String user;
	private String password;
	
	public AuthDTO(String user, String password) {
		super();
		this.user = user;
		this.password = password;
	}
	
	public AuthDTO() {
		super();
	}
	
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "AuthDTO [user=" + user + ", password=" + password + "]";
	}
}