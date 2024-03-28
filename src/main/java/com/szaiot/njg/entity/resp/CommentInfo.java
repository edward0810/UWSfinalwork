package com.szaiot.njg.entity.resp;

public class CommentInfo {
	
	private int userId;          // 用户编号
	private int carId;           // 汽车编号
	private String comment;      // 评论
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getCarId() {
		return carId;
	}
	public void setCarId(int carId) {
		this.carId = carId;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
}
