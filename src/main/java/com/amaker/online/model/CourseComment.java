package com.amaker.online.model;

/**
 * 课程评论&QA
 */
public class CourseComment extends BaseEntity{

	private static final long serialVersionUID = 789165716801545108L;
	

	private String username;


	private String toUsername;


	private Long courseId;


	private Long sectionId;


	private String sectionTitle;


	private String content;


	private Long refId;


	private String refContent;


	private Integer type;
	
	

	private String header;
	

	private String courseName;
	
	public String getUsername(){
		return username;
	}
	public void setUsername(String username){
		this.username = username;
	}

	public String getToUsername(){
		return toUsername;
	}
	public void setToUsername(String toUsername){
		this.toUsername = toUsername;
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

	public String getSectionTitle(){
		return sectionTitle;
	}
	public void setSectionTitle(String sectionTitle){
		this.sectionTitle = sectionTitle;
	}

	public String getContent(){
		return content;
	}
	public void setContent(String content){
		this.content = content;
	}

	public Long getRefId(){
		return refId;
	}
	public void setRefId(Long refId){
		this.refId = refId;
	}

	public String getRefContent(){
		return refContent;
	}
	public void setRefContent(String refContent){
		this.refContent = refContent;
	}

	public Integer getType(){
		return type;
	}
	public void setType(Integer type){
		this.type = type;
	}
	public String getHeader() {
		return header;
	}
	public void setHeader(String header) {
		this.header = header;
	}
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	
}

