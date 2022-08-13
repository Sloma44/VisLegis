package com.pioslomiany.VisLegis.security.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;

@Entity
@Table(name="users_authorities")
@Getter
public class UserRoleView {

	@Id
	@Column(name="username")
	private String userName;
	
	@Column(name="enabled")
	private int enabled;
	
	@Column(name="authority")
	private String role;
	
}