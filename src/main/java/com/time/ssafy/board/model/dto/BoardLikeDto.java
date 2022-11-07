package com.time.ssafy.board.model.dto;

public class BoardLikeDto {
	private int likeNo;
	private String userId;
	private int articleNo;
	private String registerTime;
	
	public int getLikeNo() {
		return likeNo;
	}
	public void setLikeNo(int likeNo) {
		this.likeNo = likeNo;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public int getArticleNo() {
		return articleNo;
	}
	public void setArticleNo(int articleNo) {
		this.articleNo = articleNo;
	}
	public String getRegisterTime() {
		return registerTime;
	}
	public void setRegisterTime(String registerTime) {
		this.registerTime = registerTime;
	}
	
	@Override
	public String toString() {
		return "BoardLikeDto [likeNo=" + likeNo + ", userId=" + userId + ", articleNo=" + articleNo + ", registerTime="
				+ registerTime + "]";
	}
}
