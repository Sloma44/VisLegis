package com.pioslomiany.DDSProject.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.pioslomiany.DDSProject.doc.entity.Court;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="law_case")
@Getter @Setter @NoArgsConstructor @ToString
public class LawCase {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="case_id")
	private int caseId;
	
	@Column(name="signature")
	private String signature;
	
	@Column(name="case_description")
	private String caseDescription;
	
	//poprawić CascadeType
	@OneToOne(cascade= CascadeType.ALL)
	@JoinColumn(name="authority_id")
	private Court court;
	
	@Column(name="sort")
	private String sort;
	
	@ManyToOne
	@JoinColumn(name="customerId")
	private Customer customer;
	
	@OneToMany(mappedBy="lawCase", cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	private List<CaseIncome> caseIncoms;
	
	@OneToMany(mappedBy="lawCase", cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	private List<Letter> correspondanceJournal;
	
	@OneToMany(mappedBy="lawCase", cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	private List<CustomerCaseCost> customerCaseCosts;
	
	@OneToMany(mappedBy="lawCase", cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	private List<CourtHearing> courtHearings;
	
}
