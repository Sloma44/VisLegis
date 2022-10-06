package com.pioslomiany.VisLegis.doc.entity;

import java.time.LocalDate;

public interface Form {
	
	LocalDate getActDate();
	
	int getCourtId();
	
	String getFirstName();
	
	String getLastName();
	
	String getCaseSignature();
	
	LocalDate getVerdictDate();
	
	boolean getVerdict();
	
	boolean getValidity();
	
	boolean getCostFree();

}
