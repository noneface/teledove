package com.teledove.model;

public class UserState {
	private String username;
	private String userState;
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUserState() {
		return userState;
	}

	public void setUserState(String userState) {
		this.userState = userState;
	}

	public UserState(String username, String userState) {
		// TODO Auto-generated constructor stub
		this.username = username;
		this.userState = userState;
	}
}
