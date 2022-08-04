package com.pioslomiany.VisLegis.views.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Immutable;

import lombok.Getter;

/* Entity for MySQL VIEW
 * It combines Tables of: 'customer', 'law_case' and 'income_for_case'.
 * It is used to display full table of all incomes with additional information:
 * customer first name ('customer'), last name ('customer'),
 * and case description('law_case').
 */

@Entity
@Immutable
@Table(name="customer_case_income")
@Getter
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
	
}
