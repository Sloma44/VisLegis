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
@Table(name="income_for_case")
public class CaseIncome {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="income_date")
	private String incomeDate;
	
	@Column(name="income_value")
	private double incomeValue;
	
	@Column(name="income_comment")
	private String incomeComment;
	
	@ManyToOne
	@JoinColumn(name="caseId")
	LawCase lawCase;
	
	public CaseIncome() {
		
	}

	public CaseIncome(int id, String incomeDate, double incomeValue, String incomeComment, LawCase lawCase) {
		this.id = id;
		this.incomeDate = incomeDate;
		this.incomeValue = incomeValue;
		this.incomeComment = incomeComment;
		this.lawCase = lawCase;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getIncomeDate() {
		return incomeDate;
	}

	public void setIncomeDate(String incomeDate) {
		this.incomeDate = incomeDate;
	}

	public double getIncomeValue() {
		return incomeValue;
	}

	public void setIncomeValue(double incomeValue) {
		this.incomeValue = incomeValue;
	}

	public String getIncomeComment() {
		return incomeComment;
	}

	public void setIncomeComment(String incomeComment) {
		this.incomeComment = incomeComment;
	}

	public LawCase getLawCase() {
		return lawCase;
	}

	public void setLawCase(LawCase lawCase) {
		this.lawCase = lawCase;
	}
	
	
	
}
