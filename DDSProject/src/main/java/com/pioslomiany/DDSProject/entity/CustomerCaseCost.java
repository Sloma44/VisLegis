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
@Table(name="customer_case_cost")
@Getter @Setter @NoArgsConstructor
public class CustomerCaseCost {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;

	@Column(name="cost_date")
	private Date costDate;
	
	@Column(name="cost_value")
	private double costValue;
	
	@Column(name="cost_comment")
	private String costComment;
	
	@ManyToOne
	@JoinColumn(name="caseId")
	private LawCase lawCase;
	
}
