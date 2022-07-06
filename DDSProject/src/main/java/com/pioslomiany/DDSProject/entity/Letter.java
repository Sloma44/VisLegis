package com.pioslomiany.DDSProject.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="correspondence_journal")
public class Letter {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="letter_date")
	private Date letterDate;
	
	@Column(name="sort")
	private String sort;
	
	@Column(name="internal_number")
	private String internalNumber;
	
	@Column(name="recipient_name")
	private String recipient;

	@Column(name="content")
	private String content;

	@Column(name="cost")
	private double cost;
	
	@ManyToOne
	@JoinColumn(name="caseId")
	private LawCase lawCase;
	
	public Letter() {
		
	}

	public Letter(int id, Date letterDate, String sort, String internalNumber, String recipient,
			String content, double cost, LawCase lawCase) {
		this.id = id;
		this.letterDate = letterDate;
		this.sort = sort;
		this.internalNumber = internalNumber;
		this.recipient = recipient;
		this.content = content;
		this.cost = cost;
		this.lawCase = lawCase;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getLetterDate() {
		return letterDate;
	}

	public void setLetterDate(Date letterDate) {
		this.letterDate = letterDate;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public String getInternalNumber() {
		return internalNumber;
	}

	public void setInternalNumber(String internalNumber) {
		this.internalNumber = internalNumber;
	}

	public String getRecipient() {
		return recipient;
	}

	public void setRecipient(String recipient) {
		this.recipient = recipient;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

	public LawCase getLawCase() {
		return lawCase;
	}

	public void setLawCase(LawCase lawCase) {
		this.lawCase = lawCase;
	}

	
	
}
