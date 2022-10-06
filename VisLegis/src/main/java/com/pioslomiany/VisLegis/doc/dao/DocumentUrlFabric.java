package com.pioslomiany.VisLegis.doc.dao;

import com.pioslomiany.VisLegis.doc.entity.FormType;

public class DocumentUrlFabric {
	
	private static String URL = "src/main/resources/static/docTemplates/";
	
	public static String getDocumentUrl(FormType formType) {
		if (formType.equals(FormType.CLAUSE_REQUEST_FORM)) {
			return URL + UrlFragments.CLAUSE_REQUEST_TEMPLATE_NAME.getField();
		} else if (formType.equals(FormType.PROSECUTOR_ACCESION_FORM)) {
			return URL + UrlFragments.PROSECUTOR_ACCESSION_TEMPLATE_NAME.getField();
		} else if (formType.equals(FormType.JOINING_THE_CASE_FORM)) {
			return URL + UrlFragments.JOINING_THE_CASE_TEMPLATE_NAME.getField();
		} else if (formType.equals(FormType.JUSTIFICATION_REQUEST_FORM)) {
			return URL + UrlFragments.JUSTIFICATION_REQUEST_TEMPLATE_NAME.getField();
		}
		return null;
	}

}
