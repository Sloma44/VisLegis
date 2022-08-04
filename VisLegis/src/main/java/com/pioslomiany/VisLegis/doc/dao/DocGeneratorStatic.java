package com.pioslomiany.VisLegis.doc.dao;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/*
 * Static class where is stored localization to all template files with names and
 * all strings used to create documents
 * Static methods used in doc.DAO to generate right string based on the user choices in the form
 */

public class DocGeneratorStatic {

// templates location and file names
	protected static final String PROKURATURA_WSTAPIENIE_NAME = "prokuratura-wstapienie.docx";
	protected static final String WNIOSEK_UZADANIENIE_NAME = "wniosekOUzasadnienie.docx";
	protected static final String WNIOSEK_KLAUZULA_NAME = "wniosekOKlauzule.docx";
	protected static final String WSTAPIENIE_DO_SPRAWY_NAME = "wstapienieDoSprawy.docx";
	protected static final String URL = "src/main/resources/static/docTemplates/";
	
	private static final String VERDICT_WYROK = "wyroku";
	private static final String VERDICT_POSTANOWIENIE = "postanowienia";
	
	private static final String VALIDITY_PRAWOMOCNOSC = "prawomocność";
	private static final String VALIDITY_WYMAGALNOSC = "wymagalność";
	
	private static final String COST_FREE = "Jednocześnie wskazuję, iż mój klient został w nin. sprawie zwolniony od kosztów sądowych, wobec czego od nin. wniosku nie uiszczono opłaty.";
	
	private static final String COST_NOT_FREE_FIRST = "W załączeniu przedkładam dowód uiszczenia opłaty od nin. wniosku.";
	private static final String COST_NOT_FREE_SECOND = "Załącznik:";
	private static final String COST_NOT_FREE_THIRD = "- dowód uiszczenia opłaty kancelaryjnej od nin. wniosku";


	protected static String[] generateCostFreeStrings(boolean costFree) {
		String[] stringList = new String[4];
		if (costFree) {
			stringList[0] = DocGeneratorStatic.COST_FREE;
			stringList[1] = " ";
			stringList[2] = " ";
			stringList[3] = " ";
		} else {
			stringList[0] = " ";
			stringList[1] = DocGeneratorStatic.COST_NOT_FREE_FIRST;
			stringList[2] = DocGeneratorStatic.COST_NOT_FREE_SECOND;
			stringList[3] = DocGeneratorStatic.COST_NOT_FREE_THIRD;
		}
		
		return stringList;
	}
	
	protected static String generateVerdict(boolean verdict) {
		
		String stringVerdict;
		if (verdict) {
			stringVerdict = DocGeneratorStatic.VERDICT_WYROK;
		} else {
			stringVerdict = DocGeneratorStatic.VERDICT_POSTANOWIENIE;			
		}
		
		return stringVerdict;
	}
	
	protected static String generateValidity(boolean validity) {
		String stringValidity;
		if (validity) {
			stringValidity = DocGeneratorStatic.VALIDITY_PRAWOMOCNOSC;
		} else {
			stringValidity = DocGeneratorStatic.VALIDITY_WYMAGALNOSC;			
		}
		
		return stringValidity;
	}
	
	protected static String changeDatePattern(LocalDate date) {
		return date.format(DateTimeFormatter.ofPattern("dd.MM.yyyy")).toString();
	}

}
