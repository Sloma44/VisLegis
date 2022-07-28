package com.pioslomiany.DDSProject.doc.dao;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.HashMap;

import org.docx4j.model.datastorage.migration.VariablePrepare;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.docx4j.openpackaging.parts.WordprocessingML.MainDocumentPart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.pioslomiany.DDSProject.doc.entity.Court;
import com.pioslomiany.DDSProject.doc.entity.DocxForm;

@Repository
public class JoiningTheCaseDAOImpl {
	
	@Autowired
	private CourtDAOImpl courtDAOImpl;

	public ByteArrayOutputStream generateJoiningTheCaseFormFile(DocxForm joiningTheCaseForm) throws Throwable {
			
			String dateInNewFormat = DocGeneratorStatic.changeDatePattern(joiningTheCaseForm.getActDate());
			
			Court theCourt = courtDAOImpl.getCourtById(joiningTheCaseForm.getCourtId());
			
			File file = new File(DocGeneratorStatic.URL + DocGeneratorStatic.WSTAPIENIE_DO_SPRAWY_NAME);
			
		    WordprocessingMLPackage wordMLPackage = WordprocessingMLPackage.load(file);
		 
		    MainDocumentPart documentPart = wordMLPackage.getMainDocumentPart();
		 
		    VariablePrepare.prepare(wordMLPackage);
		 
		    HashMap<String, String> variables = new HashMap<>();
		    variables.put("actDate", dateInNewFormat);
		    variables.put("destination", theCourt.getName());
		    variables.put("department", theCourt.getDepartment());
		    variables.put("firstName", joiningTheCaseForm.getFirstName());
		    variables.put("lastName", joiningTheCaseForm.getLastName());
		    variables.put("caseSignature", joiningTheCaseForm.getCaseSignature());
			
		    documentPart.variableReplace(variables);
		    
		    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		    wordMLPackage.save(outputStream);
		    return outputStream;
		}
}
