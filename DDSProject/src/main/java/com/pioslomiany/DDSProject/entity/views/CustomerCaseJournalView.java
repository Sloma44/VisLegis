package com.pioslomiany.DDSProject.entity.views;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Immutable;

/* Entity for MySQL VIEW
 * It combines Tables of: 'customer', 'law_case' and 'correspondence_journal'.
 * It is used to display full table of all correspondence with additional information:
 * customer first name ('customer'), last name ('customer'),
 * and case description('law_case').
 */

@Entity
@Immutable
@Table(name="customer_case_journal")
public class CustomerCaseJournalView {


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
	@Column(name="journal_id")
	private int letterId;
	
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

	public int getLetterId() {
		return letterId;
	}

	public Date getLetterDate() {
		return letterDate;
	}

	public String getSort() {
		return sort;
	}

	public String getInternalNumber() {
		return internalNumber;
	}

	public String getRecipient() {
		return recipient;
	}

	public String getContent() {
		return content;
	}

	public double getCost() {
		return cost;
	}
}
