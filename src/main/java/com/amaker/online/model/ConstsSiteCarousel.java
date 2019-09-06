package com.amaker.online.model;

public class ConstsSiteCarousel extends BaseEntity{

	private static final long serialVersionUID = -68528406716367757L;


	private String name;


	private String picture;


	private String url;


	private Integer weight;
	

	private Integer enable;
	
	
	public String getName(){
		return name;
	}
	public void setName(String name){
		this.name = name;
	}

	public String getPicture(){
		return picture;
	}
	public void setPicture(String picture){
		this.picture = picture;
	}

	public String getUrl(){
		return url;
	}
	public void setUrl(String url){
		this.url = url;
	}

	public Integer getWeight(){
		return weight;
	}
	public void setWeight(Integer weight){
		this.weight = weight;
	}
	public Integer getEnable() {
		return enable;
	}
	public void setEnable(Integer enable) {
		this.enable = enable;
	}

}

