package com.cn.vo;

public class RetObj {
	private Integer code = 0;
	private String msg;
	private Object data;

	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public RetObj() {

	}
	
}
