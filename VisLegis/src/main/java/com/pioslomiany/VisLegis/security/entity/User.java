package com.pioslomiany.VisLegis.security.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="users")
@Getter @Setter
public class User {
	
	@Id
	@Column(name="username")
	private String userName;

	@Column(name="password")
	private String password;
	
	@Column(name="enabled")
	private int enabled;

	//when new User is created it has by default status active
	 public User() {
		 this.enabled = 1;
	 }
}
