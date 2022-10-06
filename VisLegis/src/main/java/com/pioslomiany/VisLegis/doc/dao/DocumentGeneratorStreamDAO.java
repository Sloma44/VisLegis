package com.pioslomiany.VisLegis.doc.dao;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.Map;

import org.docx4j.model.datastorage.migration.VariablePrepare;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.docx4j.openpackaging.parts.WordprocessingML.MainDocumentPart;

import com.pioslomiany.VisLegis.doc.entity.Court;
import com.pioslomiany.VisLegis.doc.entity.Form;
import com.pioslomiany.VisLegis.doc.entity.FormType;


public class DocumentGeneratorStreamDAO {
	
	
	String documentUrl;
	
	public ByteArrayOutputStream generateDocumentStream(Court court, Form form, FormType formType) throws Exception {
		documentUrl = DocumentUrlFabric.getDocumentUrl(formType);
		return generateDocumentOutputStream(court, form, formType);
	}
	
	private ByteArrayOutputStream generateDocumentOutputStream(Court court, Form form, FormType formType) throws Exception {
		File file = new File(documentUrl);
		
		WordprocessingMLPackage wordMLPackage = WordprocessingMLPackage.load(file);
		MainDocumentPart documentPart = wordMLPackage.getMainDocumentPart();
		
		VariablePrepare.prepare(wordMLPackage);
		
		DocumentsFragmentsFabric documentsFragmentsFabric = new DocumentsFragmentsFabric(court, form, formType);
		
		Map<String, String> documentFragments = documentsFragmentsFabric.prepareFragments();
		documentPart.variableReplace(documentFragments);
		
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		wordMLPackage.save(outputStream);
		
		
		
		return outputStream;
	}
}
