package com.pioslomiany.DDSProject.doc.dao;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

import org.docx4j.model.datastorage.migration.VariablePrepare;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.docx4j.openpackaging.parts.WordprocessingML.MainDocumentPart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.pioslomiany.DDSProject.doc.entity.ClauseRequestForm;
import com.pioslomiany.DDSProject.doc.entity.Court;

@Repository
public class ClauseRequestDAOImpl {
	
	@Autowired
	CourtDAOImpl courtDAOImpl;

	public ByteArrayOutputStream generateClauseRequestFormFile(ClauseRequestForm clauseRequestForm) throws Throwable {
		String actDateInNewFormat = clauseRequestForm.getActDate().format(DateTimeFormatter.ofPattern("dd.MM.yyyy")).toString();
		String verdictDateInNewFormat = clauseRequestForm.getVerdictDate().format(DateTimeFormatter.ofPattern("dd.MM.yyyy")).toString();
		
		Court theCourt = courtDAOImpl.getCourtById(clauseRequestForm.getCourtId());
		
		String stringVerdict;
		if (clauseRequestForm.getVerdict()) {
			stringVerdict = "wyroku";
		} else {
			stringVerdict = "postanowienia";			
		}
		
		String stringValidity;
		if (clauseRequestForm.getValidity()) {
			stringValidity = "prawomocność";
		} else {
			stringValidity = "wymagalność";			
		}
		
		String stringCostFree;
		String stringCostNotFreeFirstSentense;
		String stringCostNotFreeSecondSentense;
		String stringCostNotFreeThirdSentense;
		
		if (clauseRequestForm.getCostFree()) {
			stringCostFree = "Jednocześnie wskazuję, iż mój klient został w nin. sprawie zwolniony od kosztów sądowych, wobec czego od nin. wniosku nie uiszczono opłaty.";
			stringCostNotFreeFirstSentense = " ";
			stringCostNotFreeSecondSentense = " ";
			stringCostNotFreeThirdSentense = " ";
		} else {
			stringCostFree = " ";
			stringCostNotFreeFirstSentense = "W załączeniu przedkładam dowód uiszczenia opłaty od nin. wniosku.";
			stringCostNotFreeSecondSentense = "Załącznik:";
			stringCostNotFreeThirdSentense = "- dowód uiszczenia opłaty kancelaryjnej od nin. wniosku";
		}
		
		File file = new File("src/main/resources/static/docTemplates/wniosekOKlauzule.docx");
		
	    WordprocessingMLPackage wordMLPackage = WordprocessingMLPackage.load(file);
	 
	    MainDocumentPart documentPart = wordMLPackage.getMainDocumentPart();
	 
	    VariablePrepare.prepare(wordMLPackage);
	    
	    HashMap<String, String> variables = new HashMap<>();
	    variables.put("actDate", actDateInNewFormat);
	    variables.put("destination", theCourt.getName());
	    variables.put("department", theCourt.getDepartment());
	    variables.put("firstName", clauseRequestForm.getFirstName());
	    variables.put("lastName", clauseRequestForm.getLastName());
	    variables.put("caseSignature", clauseRequestForm.getCaseSignature());
	    variables.put("verdictDate", verdictDateInNewFormat);
	    variables.put("verdict", stringVerdict);
	    variables.put("validity", stringValidity);
	    variables.put("stringCostFree", stringCostFree);
	    variables.put("stringCostNotFreeFirstSentense", stringCostNotFreeFirstSentense);
	    variables.put("stringCostNotFreeSecondSentense", stringCostNotFreeSecondSentense);
	    variables.put("stringCostNotFreeThirdSentense", stringCostNotFreeThirdSentense);
		
	    documentPart.variableReplace(variables);
	    
	    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
	    wordMLPackage.save(outputStream);
	    return outputStream;
	}
}
