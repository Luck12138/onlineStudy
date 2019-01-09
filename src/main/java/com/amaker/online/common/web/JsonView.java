package com.amaker.online.common.web;

import com.alibaba.fastjson.JSONObject;

import java.util.Date;

public class JsonView {

	//错误代码 0-成功
	private Integer errcode;

	// 消息
	private String message;

	// 数据
	private Object data;

	/**
	 * 构造函数
	 */
	public JsonView(){}
	public JsonView(Integer errcode){this.errcode=errcode;}
	public JsonView(Integer errcode,String message){
		this.errcode=errcode;
		this.message=message;
	}
	public JsonView(Integer errcode, String message, Object date){
		this.errcode=errcode;
		this.message=message;
		this.data=date;
	}


	public static String getJSONString(Object data){
		JsonView view=new JsonView(0,"success",data);
		return JSONObject.toJSONString(view);
	}

	public static String getJSONString(Integer errcode){
		JsonView view=new JsonView(errcode);
		return JSONObject.toJSONString(view);
	}

	public static String getJSONString(Integer errcode,String message){
		JsonView view=new JsonView(errcode,message);
		return JSONObject.toJSONString(view);
	}

	public static String getJSONString(Integer errcode,String message,Object data){
		JsonView view=new JsonView(errcode,message,data);
		return JSONObject.toJSONString(view);
	}


	public Integer getErrcode() {
		return errcode;
	}

	public void setErrcode(Integer errcode) {
		this.errcode = errcode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
}
