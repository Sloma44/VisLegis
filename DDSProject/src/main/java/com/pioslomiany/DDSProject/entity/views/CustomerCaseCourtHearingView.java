package com.pioslomiany.DDSProject.entity.views;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Immutable;

/* Entity for MySQL VIEW
 * It combines Tables of: 'customer', 'law_case' and 'court_hearing_agenda'.
 * It is used to display full table of all court hearings with additional information:
 * customer first name ('customer'), last name ('customer'),
 * case signature('law_case') and case description('law_case').
 */

@Entity
@Immutable
@Table(name="customer_case_court_hearing")
public class CustomerCaseCourtHearingView {

	@Column(name="customer_id")
	private String customerId;
	
	@Column(name="first_name")
	private String firstName;
	
	@Column(name="last_name")
	private String lastName;
	
	@Column(name="case_id")
	private int caseId;
	
	@Column(name="signature")
	private String signature;
	
	@Column(name="case_description")
	private String caseDescription;
	
	@Id
	@Column(name="hearing_id")
	private int hearingId;
	
	@Column(name="court_hearing_date")
	private String hearingDate;
	
	@Column(name="court_hearing_hour")
	private String hearingHour;
	
	@Column(name="place")
	private String place;
	
	@Column(name="room")
	private String room;

	public String getCustomerId() {
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
	
	public String getSignature() {
		return signature;
	}

	public String getCaseDescription() {
		return caseDescription;
	}

	public int getHearingId() {
		return hearingId;
	}

	public String getHearingDate() {
		return hearingDate;
	}

	public String getHearingHour() {
		return hearingHour;
	}

	public String getPlace() {
		return place;
	}

	public String getRoom() {
		return room;
	}
}
