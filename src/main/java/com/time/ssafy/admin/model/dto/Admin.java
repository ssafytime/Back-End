package com.time.ssafy.admin.model.dto;

public class Admin {

	private int adminNo;
	private String userId;
	public int getAdminNo() {
		return adminNo;
	}
	public void setAdminNo(int adminNo) {
		this.adminNo = adminNo;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
		
	@Override
	public String toString() {
		return "Admin [adminNo=" + adminNo + ", userId=" + userId + "]";
	}

}
