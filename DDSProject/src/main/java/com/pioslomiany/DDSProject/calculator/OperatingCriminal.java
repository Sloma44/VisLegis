package com.pioslomiany.DDSProject.calculator;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public class OperatingCriminal {
	
	private final double SLEDZTWO = 300.0;
	private final double DOCHODZENIE = 180.0;
	private final double REJ_I_INST = 420;
	private final double OKR_II_INST = 600;
	private final double OKR_I_INST = 420;
	private final double APEL_II_INST = 600;
	private final double VAT = 23;
	private final double NEXT_COURT_HEARING_PERCENTAGE = 20;;
	
	private CriminalCalculator cc;
	
	private PreparatoryProceeding pp;
	private List<OneResultInstance> resultsList;
	
	private List<Integer> firstInstanceOccurance;
	private List<Integer> secondInstanceOccurance;
	
	public OperatingCriminal(CriminalCalculator criminalCalculator) {
		this.cc = criminalCalculator;
		resultsList = new ArrayList<>();
	}
	
	public void buildCriminalCalculation() {
		buildPreparatoryProceeding();
		buildResultList();	
	}
	
	private void buildResultList() {
		
		System.err.println("STOP TUTAJ1");
		firstInstanceOccurance = convertStringToIntegerList(cc.getFirstInstance());
//		secondInstanceOccurance = convertStringToIntegerList(cc.getSecondInstance());
		System.err.println("Ile jest wysąpień w pierwszej instancji za: " + firstInstanceOccurance.size());
		
		for(int k : firstInstanceOccurance) {
			System.err.println(k);
		}
		
		if(!cc.getByChoice()) {			
			System.err.println("STOP TUTAJ3");
			if(!cc.getHigherCourt()) {
				for (int i = 0; i < firstInstanceOccurance.size(); i++) {
					System.err.println("Wystąpienie: " + i);
					resultsList.add(new OneResultInstance(true, REJ_I_INST, VAT, firstInstanceOccurance.get(i), NEXT_COURT_HEARING_PERCENTAGE));
				}
			} else {
				for (int occurance : firstInstanceOccurance) {
					resultsList.add(new OneResultInstance(true, OKR_I_INST, VAT, occurance, NEXT_COURT_HEARING_PERCENTAGE));
				}
			}
		}
	}
	
	
	private void buildPreparatoryProceeding() {
		if (!cc.getByChoice()) {
			if(cc.getPreparatoryProceeding()) {
				if (cc.getInvestigation() == true) {
					pp = new PreparatoryProceeding(DOCHODZENIE, VAT);
				} else {
					pp = new PreparatoryProceeding(SLEDZTWO, VAT);
				}
			} else {
				pp = new PreparatoryProceeding(0, 0);
			}
		}
		// dorobić dla spraw z wyboru
	}
	
	private List<Integer> convertStringToIntegerList(String str) {
		List<Integer> list = new ArrayList<>();
		
		str.replaceAll(" ", "");
		
		String[] strTable = str.split(",");
		
		for (int i = 0; i < strTable.length; i++) {
			list.add(Integer.valueOf(strTable[i]));
		}
		
		return list;
	}
	
	

}
