package com.amaker.online.model;

import java.util.Date;
import java.util.Set;



/**
 * 系统用户
 */
public class AuthUser extends BaseEntity {

	private static final long serialVersionUID = 94044276250229411L;

	/**
	 *登录用户名
	 **/
	private String realname;

	/**
	 *真实姓名
	 **/
	private String username;

	/**
	 *密码
	 **/
	private String password;

	/**
	 *性别
	 **/
	private Integer gender;

	/**
	 *头像
	 **/
	private String header;

	/**
	 *手机号码
	 **/
	private String mobile;

	/**
	 *状态：待审核（0），审核通过（1），默认（2），审核未通过（3），禁用（5）
	 **/
	private Integer status;

	/**
	 *生日
	 **/
	private Date birthday;


	private String education;


	private String collegeCode;


	private String collegeName;


	private String certNo;


	private String title;


	private String sign;


	private String openId;


	private String wechatId;


	private String qq;


	private Date loginTime;

	private String ip;


	private String province;

	private String city;


	private String district;


	private Integer weight;


	private String nickname;

	public String getRealname(){
		return realname;
	}
	public void setRealname(String realname){
		this.realname = realname;
	}

	public String getUsername(){
		return username;
	}
	public void setUsername(String username){
		this.username = username;
	}

	public String getPassword(){
		return password;
	}
	public void setPassword(String password){
		this.password = password;
	}

	public Integer getGender(){
		return gender;
	}
	public void setGender(Integer gender){
		this.gender = gender;
	}

	public String getHeader(){
		return header;
	}
	public void setHeader(String header){
		this.header = header;
	}

	public String getMobile(){
		return mobile;
	}
	public void setMobile(String mobile){
		this.mobile = mobile;
	}

	public Integer getStatus(){
		return status;
	}
	public void setStatus(Integer status){
		this.status = status;
	}

	public Date getBirthday(){
		return birthday;
	}
	public void setBirthday(Date birthday){
		this.birthday = birthday;
	}

	public String getEducation(){
		return education;
	}
	public void setEducation(String education){
		this.education = education;
	}
	public String getCollegeCode() {
		return collegeCode;
	}
	public void setCollegeCode(String collegeCode) {
		this.collegeCode = collegeCode;
	}
	public String getCollegeName() {
		return collegeName;
	}
	public void setCollegeName(String collegeName) {
		this.collegeName = collegeName;
	}
	public String getCertNo(){
		return certNo;
	}
	public void setCertNo(String certNo){
		this.certNo = certNo;
	}

	public String getTitle(){
		return title;
	}
	public void setTitle(String title){
		this.title = title;
	}

	public String getSign(){
		return sign;
	}
	public void setSign(String sign){
		this.sign = sign;
	}

	public String getOpenId(){
		return openId;
	}
	public void setOpenId(String openId){
		this.openId = openId;
	}

	public String getWechatId(){
		return wechatId;
	}
	public void setWechatId(String wechatId){
		this.wechatId = wechatId;
	}

	public String getQq(){
		return qq;
	}
	public void setQq(String qq){
		this.qq = qq;
	}

	public Date getLoginTime(){
		return loginTime;
	}
	public void setLoginTime(Date loginTime){
		this.loginTime = loginTime;
	}

	public String getIp(){
		return ip;
	}
	public void setIp(String ip){
		this.ip = ip;
	}

	public String getProvince(){
		return province;
	}
	public void setProvince(String province){
		this.province = province;
	}

	public String getCity(){
		return city;
	}
	public void setCity(String city){
		this.city = city;
	}

	public String getDistrict(){
		return district;
	}
	public void setDistrict(String district){
		this.district = district;
	}

	public Integer getWeight() {
		return weight;
	}
	public void setWeight(Integer weight) {
		this.weight = weight;
	}

	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

}

