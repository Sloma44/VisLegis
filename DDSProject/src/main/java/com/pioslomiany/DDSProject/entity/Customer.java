package com.pioslomiany.DDSProject.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="customer")
@Getter @Setter @NoArgsConstructor
public class Customer {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="first_name")
	@NotEmpty(message="Pole nie może być puste")
	private String firstName;
	
	@Column(name="last_name")
	@NotEmpty(message="Pole nie może być puste")
	private String lastName;
	
	 @OneToOne(mappedBy = "customer", cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	 @PrimaryKeyJoinColumn
	 private CustomerContactInfo customerContactInfo;
	 
	 @OneToMany(mappedBy="customer", cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	 private List<LawCase> lawCases;
}
