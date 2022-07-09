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
@Table(name="court_hearing_agenda")
public class CourtHearing {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="court_hearing_date")
	private String hearingDate;
	
	@Column(name="court_hearing_hour")
	private String hearingHour;
	
	@Column(name="place")
	private String place;
	
	@Column(name="room")
	private String room;
	
	@ManyToOne
	@JoinColumn(name="caseId")
	private LawCase lawCase;
	
	public CourtHearing() {
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getHearingDate() {
		return hearingDate;
	}

	public void setHearingDate(String hearingDate) {		
		this.hearingDate = hearingDate;
	}

	public String getHearingHour() {

		return hearingHour;
	}

	public void setHearingHour(String hearingHour) {
		
		this.hearingHour = hearingHour;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public String getRoom() {
		return room;
	}

	public void setRoom(String room) {
		this.room = room;
	}

	public LawCase getLawCase() {
		return lawCase;
	}

	public void setLawCase(LawCase lawCase) {
		this.lawCase = lawCase;
	}
}
