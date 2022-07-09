package com.pioslomiany.DDSProject.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
	
	@OneToMany(mappedBy="lawCase", cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	private List<CaseIncome> caseIncoms;
	
	@OneToMany(mappedBy="lawCase", cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	private List<Letter> correspondanceJournal;
	
	@OneToMany(mappedBy="lawCase", cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	private List<CustomerCaseCost> customerCaseCosts;
	
	@OneToMany(mappedBy="lawCase", cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	private List<CourtHearing> courtHearings;
	
	public LawCase() {
		
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

	public List<CaseIncome> getCaseIncoms() {
		return caseIncoms;
	}

	public void setCaseIncoms(List<CaseIncome> caseIncoms) {
		this.caseIncoms = caseIncoms;
	}

	public List<Letter> getCorrespondanceJournal() {
		return correspondanceJournal;
	}

	public void setCorrespondanceJournal(List<Letter> correspondanceJournal) {
		this.correspondanceJournal = correspondanceJournal;
	}

	public List<CustomerCaseCost> getCustomerCaseCosts() {
		return customerCaseCosts;
	}

	public void setCustomerCaseCosts(List<CustomerCaseCost> customerCaseCosts) {
		this.customerCaseCosts = customerCaseCosts;
	}

	public List<CourtHearing> getCourtHearing() {
		return courtHearings;
	}

	public void setCourtHearing(List<CourtHearing> courtHearings) {
		this.courtHearings = courtHearings;
	}
}
