package com.amaker.online.model;

public class UserCollections extends BaseEntity{

	private static final long serialVersionUID = -3909997252117758595L;


	private Long userId;


	private Integer classify;
	

	private Long objectId;


	private String tips;
	

	private String name;

	public Long getUserId(){
		return userId;
	}
	public void setUserId(Long userId){
		this.userId = userId;
	}

	public Integer getClassify(){
		return classify;
	}
	public void setClassify(Integer classify){
		this.classify = classify;
	}

	public String getTips(){
		return tips;
	}
	public void setTips(String tips){
		this.tips = tips;
	}
	public Long getObjectId() {
		return objectId;
	}
	public void setObjectId(Long objectId) {
		this.objectId = objectId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}

