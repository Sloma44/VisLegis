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

}
