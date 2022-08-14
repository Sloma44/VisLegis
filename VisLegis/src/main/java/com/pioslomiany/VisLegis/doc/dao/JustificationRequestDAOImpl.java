package com.pioslomiany.VisLegis.doc.dao;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.HashMap;

import org.docx4j.model.datastorage.migration.VariablePrepare;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.docx4j.openpackaging.parts.WordprocessingML.MainDocumentPart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.pioslomiany.VisLegis.doc.entity.Court;
import com.pioslomiany.VisLegis.doc.entity.JustificationRequestForm;

/* Wniosek o uzasadnienie */

@Repository
public class JustificationRequestDAOImpl {
	
	@Autowired
	CourtDAOImpl courtDAOImpl;

	public ByteArrayOutputStream generateJustificationRequestFormFile(JustificationRequestForm justificationRequestForm) throws Throwable {
		String actDateInNewFormat = DocGeneratorStatic.changeDatePattern(justificationRequestForm.getActDate());
		String verdictDateInNewFormat = DocGeneratorStatic.changeDatePattern(justificationRequestForm.getVerdictDate());
		
		Court theCourt = courtDAOImpl.getCourtById(justificationRequestForm.getCourtId());
		
		String stringVerdict = DocGeneratorStatic.generateVerdict(justificationRequestForm.getVerdict());
		
		String[] stringCostFreeList = DocGeneratorStatic.generateCostFreeStrings(justificationRequestForm.getCostFree());
				
		File file = new File(DocGeneratorStatic.URL + DocGeneratorStatic.WNIOSEK_UZADANIENIE_NAME);
		
	    WordprocessingMLPackage wordMLPackage = WordprocessingMLPackage.load(file);
	 
	    MainDocumentPart documentPart = wordMLPackage.getMainDocumentPart();
	 
	    VariablePrepare.prepare(wordMLPackage);
	    
	    HashMap<String, String> variables = new HashMap<>();
	    variables.put("actDate", actDateInNewFormat);
	    variables.put("destination", theCourt.getName());
	    variables.put("department", theCourt.getDepartment());
	    variables.put("firstName", justificationRequestForm.getFirstName());
	    variables.put("lastName", justificationRequestForm.getLastName());
	    variables.put("caseSignature", justificationRequestForm.getCaseSignature());
	    variables.put("verdictDate", verdictDateInNewFormat);
	    variables.put("verdict", stringVerdict);
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
