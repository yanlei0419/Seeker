package org.vegetto.common.base.hibernate.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;


@Entity
// 表示为实体类
@Table(name = "t_teacher")
// 表名注解
public class UserPo {
	@Id
	// 表示主键
	@GenericGenerator(name = "generator", strategy = "increment")
	@GeneratedValue(generator = "generator")
	// 自增长
	@Column(name = "id")
	// 类属性对应着表字段
	private String id;
	@Column(name = "t_username")
	// 类属性对应着表字段
	private String username;
	private String password;
	@Column(name = "t_age")
	// 在实体类属性进行注解，类属性对应着表字段
	private String birthday;
	private String status;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
