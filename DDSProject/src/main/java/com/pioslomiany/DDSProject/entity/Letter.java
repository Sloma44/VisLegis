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

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="correspondence_journal")
@Getter @Setter @NoArgsConstructor
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
	
}
