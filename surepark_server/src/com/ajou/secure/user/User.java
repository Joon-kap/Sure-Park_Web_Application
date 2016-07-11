package com.ajou.secure.user;

public class User {
	private int userSeq;
	private String userEmail;
	private String userPassword;
	private String userName;
	private String userRrnNo; 
	private String userPmtNo;
	private String userStatus;
	private String userRegDate;
	
	public User(){}

	public int getUserSeq() {
		return userSeq;
	}

	public void setUserSeq(int userSeq) {
		this.userSeq = userSeq;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserRrnNo() {
		return userRrnNo;
	}

	public void setUserRrnNo(String userRrnNo) {
		this.userRrnNo = userRrnNo;
	}

	public String getUserPmtNo() {
		return userPmtNo;
	}

	public void setUserPmtNo(String userPmtNo) {
		this.userPmtNo = userPmtNo;
	}

	public String getUserStatus() {
		return userStatus;
	}

	public void setUserStatus(String userStatus) {
		this.userStatus = userStatus;
	}

	public String getUserRegDate() {
		return userRegDate;
	}

	public void setUserRegDate(String userRegDate) {
		this.userRegDate = userRegDate;
	}
	
	
}
