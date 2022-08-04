package com.pioslomiany.VisLegis.customer.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="court_hearing_agenda")
@Getter @Setter @NoArgsConstructor
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
	
}
