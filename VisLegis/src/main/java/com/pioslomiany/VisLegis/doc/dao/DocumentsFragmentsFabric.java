package com.pioslomiany.VisLegis.doc.dao;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

import com.pioslomiany.VisLegis.doc.entity.*;

public class DocumentsFragmentsFabric {
	
	private Form form;
	private Court court;
	private FormType formType;
	
	protected DocumentsFragmentsFabric(Court court, Form form, FormType formType) {
		this.form = form;
		this.court = court;
		this.formType = formType;
	}
	
	protected Map<String, String> prepareFragments() {
		if (formType.equals(FormType.PROSECUTOR_ACCESION_FORM)) {
			return prepareProsecutorAccesionFragments();
		} else if (formType.equals(FormType.JOINING_THE_CASE_FORM)) {
			return prepareJoiningTheCaseFragments();
		} else if (formType.equals(FormType.JUSTIFICATION_REQUEST_FORM)) {
			return prepareJustificationRequestFragments();
		} else {
			return prepareClauseRequestFragments();
		}
	}
	
	private Map<String, String> prepareProsecutorAccesionFragments() {
		Map<String, String> documentFragments = new HashMap<>();
		documentFragments.put(TemplateFields.actDate.name(), getActDate());
		documentFragments.put(TemplateFields.destination.name(), getCourtName());
		documentFragments.put(TemplateFields.firstName.name(), getFirstName());
		documentFragments.put(TemplateFields.lastName.name(), getLastName());
		documentFragments.put(TemplateFields.caseSignature.name(), getCaseSignature());
		
		return documentFragments;
	}
	
	private Map<String, String> prepareJoiningTheCaseFragments() {
		Map<String, String> documentFragments = prepareProsecutorAccesionFragments();
		documentFragments.put(TemplateFields.department.name(), getCourtDepartment());
		return documentFragments;
	}
	
	private Map<String, String> prepareJustificationRequestFragments() {
		Map<String, String> documentFragments = prepareJoiningTheCaseFragments();
		documentFragments.put(TemplateFields.verdictDate.name(), getVerdictDate());
		documentFragments.put(TemplateFields.verdict.name(), getVerdict());
		documentFragments.put(TemplateFields.stringCostFree.name(), getCostFree());
		documentFragments.put(TemplateFields.stringCostNotFreeFirstSentense.name(), getCostNotFreeFirstSentence());
		documentFragments.put(TemplateFields.stringCostNotFreeSecondSentense.name(), getCostNotFreeSecondSentence());
		documentFragments.put(TemplateFields.stringCostNotFreeThirdSentense.name(), getCostNotFreeThirdSentence());
		
		return documentFragments;
	}
	
	private Map<String, String> prepareClauseRequestFragments() {
		Map<String, String> documentFragments = prepareJustificationRequestFragments();
		documentFragments.put(TemplateFields.validity.name(), getValidity());
		
		return documentFragments;
	}

	

	private String getActDate() {
		LocalDate actDateAsLocalDate = form.getActDate();
		return convertLocalDateToString(actDateAsLocalDate);
	}
	
	private String getCourtName() {
		return court.getName();
	}
	
	private String getCourtDepartment() {
		return court.getDepartment();
	}
	
	private String getFirstName() {
		return form.getFirstName();
	}
	
	private String getLastName() {
		return form.getLastName();
	}
	
	private String getCaseSignature() {
		return form.getCaseSignature();
	}
	
	private String getVerdictDate() {
		LocalDate verdictDateAsLocalDate = form.getVerdictDate();
		return convertLocalDateToString(verdictDateAsLocalDate);
	}
	
	private String getVerdict() {
		boolean isVerdict = form.getVerdict();
		return generateVerdict(isVerdict);
	}
	
	private String getValidity() {
		boolean isValidity = form.getValidity();
		return generateValidity(isValidity);
	}
	
	private String getCostFree() {
		String[] costFreeTable = getCostFreeTable();
		return costFreeTable[0];
	}
	
	private String getCostNotFreeFirstSentence() {
		String[] costFreeTable = getCostFreeTable();
		return costFreeTable[1];
	}
	
	private String getCostNotFreeSecondSentence() {
		String[] costFreeTable = getCostFreeTable();
		return costFreeTable[2];
	}
	
	private String getCostNotFreeThirdSentence() {
		String[] costFreeTable = getCostFreeTable();
		return costFreeTable[3];
	}
	
	private String[] getCostFreeTable() {
		boolean isCostFree = form.getCostFree();
		return generateCostFreeStrings(isCostFree);
	}
	
	private String convertLocalDateToString(LocalDate date) {
		return date.format(DateTimeFormatter.ofPattern("dd.MM.yyyy")).toString();
	}
	
	
	private static String[] generateCostFreeStrings(boolean costFree) {
		String[] stringList = new String[4];
		if (costFree) {
			stringList[0] = DocumentFragments.COST_FREE.getFragment();
			stringList[1] = " ";
			stringList[2] = " ";
			stringList[3] = " ";
		} else {
			stringList[0] = " ";
			stringList[1] = DocumentFragments.COST_NOT_FREE_FIRST.getFragment();
			stringList[2] = DocumentFragments.COST_NOT_FREE_SECOND.getFragment();
			stringList[3] = DocumentFragments.COST_NOT_FREE_THIRD.getFragment();
		}
		
		return stringList;
	}
	
	private static String generateVerdict(boolean verdict) {
		if (verdict) {
			return DocumentFragments.VERDICT_WYROK.getFragment();
		} else {
			return DocumentFragments.VERDICT_POSTANOWIENIE.getFragment();			
		}
	}
	
	private static String generateValidity(boolean validity) {
		if (validity) {
			return DocumentFragments.VALIDITY_PRAWOMOCNOSC.getFragment();
		} else {
			return DocumentFragments.VALIDITY_WYMAGALNOSC.getFragment();			
		}
	}
	
}
