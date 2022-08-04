package com.pioslomiany.VisLegis.customer.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="customer_contact_info")
@Getter @Setter @NoArgsConstructor
public class CustomerContactInfo {
	
	@Id
	@Column(name="customer_id")
	private int id;
	
	@Column(name="country")
	private String country;
	
	@Column(name="city")
	private String city;
	
	@Column(name="postal_code")
	private String postalCode;
	
	@Column(name="street")
	private String street;
	
	@Column(name="house_number")
	private String houseNumber;
	
	@Column(name="telephone")
	private String telephone;
	
	@Column(name="email")
	private String email;
	
	@OneToOne(cascade= CascadeType.ALL)
	@MapsId
	@JoinColumn(name="customer_id")
	private Customer customer;

}
