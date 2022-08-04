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
import com.pioslomiany.VisLegis.doc.entity.DocxForm;

@Repository
public class ProsecutorAccesionDAOImpl {
	
	@Autowired
	private CourtDAOImpl courtDAOImpl;

	public ByteArrayOutputStream generateProsecutorAccesionFile(DocxForm prosecutorAccessionForm) throws Throwable {
			
			String dateInNewFormat = DocGeneratorStatic.changeDatePattern(prosecutorAccessionForm.getActDate());
			
			Court theCourt = courtDAOImpl.getCourtById(prosecutorAccessionForm.getCourtId());
			
			File file = new File(DocGeneratorStatic.URL + DocGeneratorStatic.PROKURATURA_WSTAPIENIE_NAME);
			
		    WordprocessingMLPackage wordMLPackage = WordprocessingMLPackage.load(file);
		 
		    MainDocumentPart documentPart = wordMLPackage.getMainDocumentPart();
		 
		    VariablePrepare.prepare(wordMLPackage);
		 
		    HashMap<String, String> variables = new HashMap<>();
		    variables.put("actDate", dateInNewFormat);
		    variables.put("destination", theCourt.getName());
		    variables.put("firstName", prosecutorAccessionForm.getFirstName());
		    variables.put("lastName", prosecutorAccessionForm.getLastName());
		    variables.put("caseSignature", prosecutorAccessionForm.getCaseSignature());
			
		    documentPart.variableReplace(variables);
		    
		    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		    wordMLPackage.save(outputStream);
		    return outputStream;
		}
}
