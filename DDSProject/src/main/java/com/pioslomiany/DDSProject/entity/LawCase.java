package com.pioslomiany.DDSProject.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="law_case")
public class LawCase {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="case_id")
	private int caseId;
	
	@Column(name="signature")
	private String signature;
	
	@Column(name="case_description")
	private String caseDescription;
	
	@Column(name="authority")
	private String authority;
	
	@Column(name="sort")
	private String sort;
	
	@ManyToOne
	@JoinColumn(name="customerId")
	private Customer customer;
	
	public LawCase() {
		
	}

	public LawCase(String signature, String caseDescription, String authority, Customer customer) {
		this.signature = signature;
		this.caseDescription = caseDescription;
		this.authority = authority;
		this.customer = customer;
	}

	public int getCaseId() {
		return caseId;
	}

	public void setCaseId(int caseId) {
		this.caseId = caseId;
	}

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

	public String getCaseDescription() {
		return caseDescription;
	}

	public void setCaseDescription(String caseDescription) {
		this.caseDescription = caseDescription;
	}

	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
}
