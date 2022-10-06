package com.pioslomiany.VisLegis.doc.dao;

public enum UrlFragments {

	PROSECUTOR_ACCESSION_TEMPLATE_NAME ("prokuratura-wstapienie.docx"),
	JUSTIFICATION_REQUEST_TEMPLATE_NAME ("wniosekOUzasadnienie.docx"),
	CLAUSE_REQUEST_TEMPLATE_NAME ("wniosekOKlauzule.docx"),
	JOINING_THE_CASE_TEMPLATE_NAME ("wstapienieDoSprawy.docx");
	
	private String fieldName;
	
	UrlFragments(String fieldName) {
		this.fieldName = fieldName;
	}
	
	public String getField() {
		return fieldName;
	}
	
}
