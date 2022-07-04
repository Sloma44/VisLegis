package com.pioslomiany.DDSProject.entity;

import java.sql.Date;
import java.sql.Time;

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
public class CourtHearingAgenda {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="court_hearing_date")
	private Date courtHearingDate;
	
	@Column(name="court_hearing_hour")
	private Time courtHearingHour;
	
	@Column(name="place")
	private String place;
	
	@Column(name="room")
	private String room;
	
	@ManyToOne
	@JoinColumn(name="caseId")
	private LawCase lawCase;
	
	public CourtHearingAgenda() {
		
	}

	public CourtHearingAgenda(int id, Date courtHearingDate, Time courtHearingHour, String place, String room,
			LawCase lawCase) {
		this.id = id;
		this.courtHearingDate = courtHearingDate;
		this.courtHearingHour = courtHearingHour;
		this.place = place;
		this.room = room;
		this.lawCase = lawCase;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getCourtHearingDate() {
		return courtHearingDate;
	}

	public void setCourtHearingDate(Date courtHearingDate) {
		this.courtHearingDate = courtHearingDate;
	}

	public Time getCourtHearingHour() {
		return courtHearingHour;
	}

	public void setCourtHearingHour(Time courtHearingHour) {
		this.courtHearingHour = courtHearingHour;
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
