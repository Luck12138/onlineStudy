package com.amaker.online.model;

public class UserCourseSection extends BaseEntity{

	private static final long serialVersionUID = 5447461555053008202L;


	private Long userId;
	

	private Long courseId;
	

	private Long sectionId;
	

	private Integer status;
	

	private Integer rate;

	public Long getUserId(){
		return userId;
	}
	public void setUserId(Long userId){
		this.userId = userId;
	}

	public Long getCourseId(){
		return courseId;
	}
	public void setCourseId(Long courseId){
		this.courseId = courseId;
	}

	public Long getSectionId(){
		return sectionId;
	}
	public void setSectionId(Long sectionId){
		this.sectionId = sectionId;
	}

	public Integer getStatus(){
		return status;
	}
	public void setStatus(Integer status){
		this.status = status;
	}
	public Integer getRate() {
		return rate;
	}
	public void setRate(Integer rate) {
		this.rate = rate;
	}

}

