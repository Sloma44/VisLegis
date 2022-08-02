package com.pioslomiany.DDSProject.doc.entity;

import lombok.Setter;

public class ClauseRequestForm extends JustificationRequestForm {
	
	@Setter
	private boolean validity;

	public boolean getValidity() {
		return validity;
	}
	
	public ClauseRequestForm() {
		super();
	}
	
	public ClauseRequestForm(int courtId, String firstName, String lastName, String caseSignature) {
		super(courtId, firstName, lastName, caseSignature);
	}

}
