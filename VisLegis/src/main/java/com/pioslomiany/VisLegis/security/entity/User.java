package com.pioslomiany.VisLegis.security.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="users")
@Getter @Setter
public class User {
	
	@Id
	@Column(name="username")
	@NotEmpty(message="Pole nie może być puste")
	private String userName;

	@Column(name="password")
	@NotEmpty(message="Pole nie może być puste")
	private String password;
	
	@Column(name="enabled")
	private int enabled;

	//when new User is created it has by default status active
	 public User() {
		 this.enabled = 1;
	 }
}
