package com.baizhi.entity;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;
import java.util.Date;

public class Log implements Serializable {
	private  String  id;
	private  String userName;
	private  String type;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	private  Date createDate;
	private  String message;

	@Override
	public String toString() {
		return "Log{" +
				"id='" + id + '\'' +
				", userName='" + userName + '\'' +
				", type='" + type + '\'' +
				", createDate=" + createDate +
				", message='" + message + '\'' +
				'}';
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Log(String id, String userName, String type, Date createDate, String message) {

		this.id = id;
		this.userName = userName;
		this.type = type;
		this.createDate = createDate;
		this.message = message;
	}

	public Log() {

	}
}
