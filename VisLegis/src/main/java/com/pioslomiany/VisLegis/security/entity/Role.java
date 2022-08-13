package com.pioslomiany.VisLegis.security.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="authorities")
@Getter @Setter @NoArgsConstructor
public class Role {
	
	@Id
	@Column(name="username")
	private String userName;
	
	@Column(name="authority")
	private String role;
}
