package com.luotianyi.ssh2.entiy;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@SuppressWarnings("serial")
@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = { "username" }))
public class User implements Serializable {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(nullable = false)
	private Integer uid;
	@Column(length = 16, nullable = false)
	private String username;
	@Column(length = 255, nullable = false)
	private String password;
	@Column(columnDefinition = "tinyint")
	private int age;
	@Column(length = 10, nullable = false)
	private String gender;
	@Column(nullable = false)
	private String email;
	@Column(nullable = false)
	private String fromname;
	@Column(nullable = true)
	private String fav;
	@Column(nullable = true)
	private String other;
	@Column(columnDefinition = "date")
	private String birthday;

	public User() {
		super();
	}

	public Integer getUid() {
		return uid;
	}

	public User(Integer uid, String username, String password, int age, String gender, String email, String fromname,
			String fav, String other, String birthday) {
		super();
		this.uid = uid;
		this.username = username;
		this.password = password;
		this.age = age;
		this.gender = gender;
		this.email = email;
		this.fromname = fromname;
		this.fav = fav;
		this.other = other;
		this.birthday = birthday;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public void setUid(Integer uid) {
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

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFromname() {
		return fromname;
	}

	public void setFromname(String fromname) {
		this.fromname = fromname;
	}

	public String getFav() {
		return fav;
	}

	public void setFav(String fav) {
		this.fav = fav;
	}

	public String getOther() {
		return other;
	}

	public void setOther(String other) {
		this.other = other;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	@Override
	public String toString() {
		return "User [uid=" + uid + ", username=" + username + ", password=" + password + ", age=" + age + ", gender="
				+ gender + ", email=" + email + ", fromname=" + fromname + ", fav=" + fav + ", other=" + other
				+ ", birthday=" + birthday + "]";
	}

}
