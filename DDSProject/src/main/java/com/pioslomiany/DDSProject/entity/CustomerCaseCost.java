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
@Table(name="customer_case_cost")
public class CustomerCaseCost {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;

	@Column(name="cost_date")
	private Date costDate;
	
	@Column(name="cost_value")
	private double costValue;
	
	@Column(name="cost_comment")
	private String costComment;
	
	@ManyToOne
	@JoinColumn(name="caseId")
	private LawCase lawCase;
	
	public CustomerCaseCost() {
		
	}

	public CustomerCaseCost(int id, Date costDate, double costValue, String costComment, LawCase lawCase) {
		this.id = id;
		this.costDate = costDate;
		this.costValue = costValue;
		this.costComment = costComment;
		this.lawCase = lawCase;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getCostDate() {
		return costDate;
	}

	public void setCostDate(Date costDate) {
		this.costDate = costDate;
	}

	public double getCostValue() {
		return costValue;
	}

	public void setCostValue(double costValue) {
		this.costValue = costValue;
	}

	public String getCostComment() {
		return costComment;
	}

	public void setCostComment(String costComment) {
		this.costComment = costComment;
	}

	public LawCase getLawCase() {
		return lawCase;
	}

	public void setLawCase(LawCase lawCase) {
		this.lawCase = lawCase;
	}
}
