package com.pioslomiany.VisLegis.doc.dao;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

import org.docx4j.model.datastorage.migration.VariablePrepare;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.docx4j.openpackaging.parts.WordprocessingML.MainDocumentPart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.pioslomiany.VisLegis.doc.entity.ClauseRequestForm;
import com.pioslomiany.VisLegis.doc.entity.Court;

@Repository
public class ClauseRequestDAOImpl {
	
	@Autowired
	CourtDAOImpl courtDAOImpl;
	
//	Load the document from tamplate (url, file names and methods from DocGeneratorStatic)

	public ByteArrayOutputStream generateClauseRequestFormFile(ClauseRequestForm clauseRequestForm) throws Throwable {
		//set date from LocalDate to String in format dd.MM.yyyy
		String actDateInNewFormat = clauseRequestForm.getActDate().format(DateTimeFormatter.ofPattern("dd.MM.yyyy")).toString();
		String verdictDateInNewFormat = clauseRequestForm.getVerdictDate().format(DateTimeFormatter.ofPattern("dd.MM.yyyy")).toString();
		
		Court theCourt = courtDAOImpl.getCourtById(clauseRequestForm.getCourtId());
		
		// generates right Strings based on the user choices from the form
		String stringVerdict = DocGeneratorStatic.generateVerdict(clauseRequestForm.getVerdict());
		String stringValidity = DocGeneratorStatic.generateValidity(clauseRequestForm.getValidity());
		String[] stringCostFreeList = DocGeneratorStatic.generateCostFreeStrings(clauseRequestForm.getCostFree());
		
		// load the file from templates and replace the variables with the strings
		File file = new File(DocGeneratorStatic.URL + DocGeneratorStatic.WNIOSEK_KLAUZULA_NAME);
		
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
	    variables.put("stringCostFree", stringCostFreeList[0]);
	    variables.put("stringCostNotFreeFirstSentense", stringCostFreeList[1]);
	    variables.put("stringCostNotFreeSecondSentense", stringCostFreeList[2]);
	    variables.put("stringCostNotFreeThirdSentense", stringCostFreeList[3]);
		
	    documentPart.variableReplace(variables);
	    
	    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
	    wordMLPackage.save(outputStream);
	    return outputStream;
	}
}
