package com.pioslomiany.DDSProject.entity.views;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Immutable;


@Entity
@Immutable
@Table(name="customer_case_income")
public class CustomerCaseIncomeView {

	@Column(name="customer_id")
	private int customerId;
	
	@Column(name="first_name")
	private String firstName;
	
	@Column(name="last_name")
	private String lastName;
	
	@Column(name="case_id")
	private int caseId;
	
	@Column(name="case_description")
	private String caseDescription;
	
	@Id
	@Column(name="income_id")
	private int incomeId;
	
	@Column(name="income_date")
	private String incomeDate;
	
	@Column(name="income_value")
	private double incomeValue;
	
	@Column(name="income_comment")
	private String incomeComment;

	public int getCustomerId() {
		return customerId;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public int getCaseId() {
		return caseId;
	}

	public String getCaseDescription() {
		return caseDescription;
	}

	public int getIncomeId() {
		return incomeId;
	}

	public String getIncomeDate() {
		return incomeDate;
	}

	public double getIncomeValue() {
		return incomeValue;
	}

	public String getIncomeComment() {
		return incomeComment;
	}
}
