package com.pioslomiany.VisLegis.doc.dao;

public enum DocumentFragments {

	VERDICT_WYROK ("wyroku"),
	VERDICT_POSTANOWIENIE ("postanowienia"),
	
	VALIDITY_PRAWOMOCNOSC ("prawomocność"),
	VALIDITY_WYMAGALNOSC ("wymagalność"),
	
	COST_FREE ("Jednocześnie wskazuję, iż mój klient został w nin. sprawie zwolniony od kosztów sądowych, wobec czego od nin. wniosku nie uiszczono opłaty."),
	
	COST_NOT_FREE_FIRST ("W załączeniu przedkładam dowód uiszczenia opłaty od nin. wniosku."),
	COST_NOT_FREE_SECOND ("Załącznik:"),
	COST_NOT_FREE_THIRD ("- dowód uiszczenia opłaty kancelaryjnej od nin. wniosku");
	
	private String fragment;
	
	DocumentFragments(String fragment) {
		this.fragment = fragment;
	}
	
	public String getFragment() {
		return fragment;
	}
}
