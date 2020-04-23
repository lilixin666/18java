package com.czxy.domain;

import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * CREATE TABLE `user` (
 *   `uid` VARCHAR(32) NOT NULL,
 *   `username` VARCHAR(20) DEFAULT NULL,		#用户名
 *   `password` VARCHAR(32) DEFAULT NULL,		#密码
 *   `name` VARCHAR(20) DEFAULT NULL,			#昵称
 *   `email` VARCHAR(30) DEFAULT NULL,			#电子邮箱
 *   `telephone` VARCHAR(20) DEFAULT NULL,		#电话
 *   `birthday` DATE DEFAULT NULL,				#生日
 *   `sex` VARCHAR(10) DEFAULT NULL,			#性别
 *   `state` INT(11) DEFAULT 0,				#状态：0=未激活，1=已激活
 *   `code` VARCHAR(64) DEFAULT NULL,			#激活码
 *   PRIMARY KEY (`uid`)
 * ) ;
 */

@Table(name="user")
public class User {

	@Id
	private String uid;
	private String username;
	private String password;
	private String name;
	private String email;
	private String telephone;
	private Date birthday;
	private String sex;
	private Integer state;
	private String code;


	@Override
	public String toString() {
		return "User{" +
				"uid='" + uid + '\'' +
				", username='" + username + '\'' +
				", password='" + password + '\'' +
				", name='" + name + '\'' +
				", email='" + email + '\'' +
				", telephone='" + telephone + '\'' +
				", birthday=" + birthday +
				", sex='" + sex + '\'' +
				", state=" + state +
				", code='" + code + '\'' +
				'}';
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public User(String uid, String username, String password, String name, String email, String telephone, Date birthday, String sex, Integer state, String code) {

		this.uid = uid;
		this.username = username;
		this.password = password;
		this.name = name;
		this.email = email;
		this.telephone = telephone;
		this.birthday = birthday;
		this.sex = sex;
		this.state = state;
		this.code = code;
	}

	public User() {

	}
}
