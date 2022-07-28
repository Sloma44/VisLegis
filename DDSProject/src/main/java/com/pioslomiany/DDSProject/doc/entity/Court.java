package com.pioslomiany.DDSProject.doc.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="court_registry")
@Getter @Setter @NoArgsConstructor
public class Court {

	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(name="name")
	@NotEmpty(message="Pole nie może być puste")
	private String name;
	
	@Column(name="department")
	@NotEmpty(message="Pole nie może być puste")
	private String department;
	
	@Column(name="street_and_number")
	@NotEmpty(message="Pole nie może być puste")
	private String streetAndNumber;
	
	@Column(name="postal_and_city")
	@NotEmpty(message="Pole nie może być puste")
	private String postalAndCity;
	
}
