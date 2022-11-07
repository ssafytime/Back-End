package com.time.ssafy.board.model.dto;

public class BoardRoomDto {
	private int roomNo;
	private String userId;
	private String roomName;
	private String registerTime;
	
	public int getRoomNo() {
		return roomNo;
	}
	public void setRoomNo(int roomNo) {
		this.roomNo = roomNo;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getRoomName() {
		return roomName;
	}
	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}
	public String getRegisterTime() {
		return registerTime;
	}
	public void setRegisterTime(String registerTime) {
		this.registerTime = registerTime;
	}
	
	@Override
	public String toString() {
		return "BoardRoomDto [roomNo=" + roomNo + ", userId=" + userId + ", roomName=" + roomName + ", registerTime="
				+ registerTime + "]";
	}
	
}
