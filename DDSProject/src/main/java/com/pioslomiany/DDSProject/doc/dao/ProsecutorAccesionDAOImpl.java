package com.pioslomiany.DDSProject.doc.dao;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

import org.docx4j.model.datastorage.migration.VariablePrepare;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.docx4j.openpackaging.parts.WordprocessingML.MainDocumentPart;
import org.springframework.stereotype.Repository;

import com.pioslomiany.DDSProject.doc.entity.ProsecutorAccessionForm;

@Repository
public class ProsecutorAccesionDAOImpl {

	public ByteArrayOutputStream generateProsecutorAccesionFile(ProsecutorAccessionForm prosecutorAccessionForm) throws Throwable {
			
			String dateInNewFormat = prosecutorAccessionForm.getActDate().format(DateTimeFormatter.ofPattern("dd.MM.yyyy")).toString();
			
			File file = new File("src/main/resources/static/docTemplates/prokuratura-wstapienie.docx");
			
		    WordprocessingMLPackage wordMLPackage = WordprocessingMLPackage.load(file);
		 
		    MainDocumentPart documentPart = wordMLPackage.getMainDocumentPart();
		 
		    VariablePrepare.prepare(wordMLPackage);
		 
		    HashMap<String, String> variables = new HashMap<>();
		    variables.put("actDate", dateInNewFormat);
		    variables.put("destination", prosecutorAccessionForm.getDestination());
		    variables.put("firstName", prosecutorAccessionForm.getFirstName());
		    variables.put("lastName", prosecutorAccessionForm.getLastName());
		    variables.put("caseSignature", prosecutorAccessionForm.getCaseSignature());
			
		    documentPart.variableReplace(variables);
		    
		    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		    wordMLPackage.save(outputStream);
		    return outputStream;
		}
}
