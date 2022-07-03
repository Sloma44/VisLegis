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

@Entity
@Table(name="customer")
public class Customer {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="first_name")
	private String firstName;
	
	@Column(name="last_name")
	private String lastName;
	
	 @OneToOne(mappedBy = "customer", cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	 @PrimaryKeyJoinColumn
	 private CustomerContactInfo customerContactInfo;
	 
	 @OneToMany(mappedBy="customer", cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	 private List<LawCase> lawCases;

	public Customer() {
		
	}
	
	public Customer(int id, String firstName, String lastName) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public CustomerContactInfo getCustomerContactInfo() {
		return customerContactInfo;
	}

	public void setCustomerContactInfo(CustomerContactInfo customerContactInfo) {
		this.customerContactInfo = customerContactInfo;
	}

	public List<LawCase> getLawCases() {
		return lawCases;
	}

	public void setLawCases(List<LawCase> lawCases) {
		this.lawCases = lawCases;
	}

	
	
}
