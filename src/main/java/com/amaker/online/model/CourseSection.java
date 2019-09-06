package com.amaker.online.model;

/**
 * 课程章节
 */
public class CourseSection extends BaseEntity{

	private static final long serialVersionUID = -7261405404725335316L;

	private long courseId;


	private Long parentId;


	private String name;


	private Integer sort;


	private String time;


	private Integer onsale;
	

	private String videoUrl;

	public long getCourseId() {
		return courseId;
	}

	public void setCourseId(long courseId) {
		this.courseId = courseId;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public String getName(){
		return name;
	}
	public void setName(String name){
		this.name = name;
	}

	public Integer getSort(){
		return sort;
	}
	public void setSort(Integer sort){
		this.sort = sort;
	}

	public String getTime(){
		return time;
	}
	public void setTime(String time){
		this.time = time;
	}

	public Integer getOnsale(){
		return onsale;
	}
	public void setOnsale(Integer onsale){
		this.onsale = onsale;
	}
	public String getVideoUrl() {
		return videoUrl;
	}
	public void setVideoUrl(String videoUrl) {
		this.videoUrl = videoUrl;
	}
	
}

