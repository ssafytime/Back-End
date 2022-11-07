package com.time.ssafy.board.model.dto;

public class BoardBlameDto {
	private int blameNo;
	private int articleNo;
	private String userId;
	private String registerTime;
	
	public int getBlameNo() {
		return blameNo;
	}
	public void setBlameNo(int blameNo) {
		this.blameNo = blameNo;
	}
	public int getArticleNo() {
		return articleNo;
	}
	public void setArticleNo(int articleNo) {
		this.articleNo = articleNo;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getRegisterTime() {
		return registerTime;
	}
	public void setRegisterTime(String registerTime) {
		this.registerTime = registerTime;
	}
	
	@Override
	public String toString() {
		return "BoardBlameDto [blameNo=" + blameNo + ", articleNo=" + articleNo + ", userId=" + userId
				+ ", registerTime=" + registerTime + "]";
	}
	
}
