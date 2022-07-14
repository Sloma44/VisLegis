package com.pioslomiany.DDSProject.calculator.service;

import java.util.ArrayList;
import java.util.List;

import com.pioslomiany.DDSProject.calculator.entity.CriminalCourtCost;
import com.pioslomiany.DDSProject.calculator.entity.CriminalCourtCostForm;
import com.pioslomiany.DDSProject.calculator.entity.CriminalCourtPreparatoryProceedingCost;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public class CriminalCourtCostService {
	
	private final double SLEDZTWO = 300.0;
	private final double DOCHODZENIE = 180.0;
	private final double REJ_I_INST = 420;
	private final double OKR_II_INST = 600;
	private final double OKR_I_INST = 420;
	private final double APEL_II_INST = 600;
	private final double VAT = 23;
	private final double NEXT_COURT_HEARING_PERCENTAGE = 20;;
	
	private CriminalCourtCostForm cc;
	
	private CriminalCourtPreparatoryProceedingCost pp;
	private List<CriminalCourtCost> resultsList;
	
	private List<Integer> firstInstanceOccurence;
	private List<Integer> secondInstanceOccurence;
	
	public CriminalCourtCostService(CriminalCourtCostForm criminalCourtCostForm) {
		this.cc = criminalCourtCostForm;
		resultsList = new ArrayList<>();
	}
	
	public void buildCriminalCalculation() {
		buildPreparatoryProceeding();
		buildResultList();	
	}
	
	private void buildResultList() {

		firstInstanceOccurence = convertStringToIntegerList(cc.getFirstInstance());
//		secondInstanceOccurence = convertStringToIntegerList(cc.getSecondInstance());

		
		if(!cc.getByChoice()) {			
			if(!cc.getHigherCourt()) {
				for (int i = 0; i < firstInstanceOccurence.size(); i++) {
					resultsList.add(new CriminalCourtCost(true, REJ_I_INST, VAT, firstInstanceOccurence.get(i), NEXT_COURT_HEARING_PERCENTAGE));
				}
			} else {
				for (int occurance : firstInstanceOccurence) {
					resultsList.add(new CriminalCourtCost(true, OKR_I_INST, VAT, occurance, NEXT_COURT_HEARING_PERCENTAGE));
				}
			}
		}
	}
	
	
	private void buildPreparatoryProceeding() {
		if (!cc.getByChoice()) {
			if(cc.getPreparatoryProceeding()) {
				if (cc.getInvestigation() == true) {
					pp = new CriminalCourtPreparatoryProceedingCost(DOCHODZENIE, VAT);
				} else {
					pp = new CriminalCourtPreparatoryProceedingCost(SLEDZTWO, VAT);
				}
			} else {
				pp = new CriminalCourtPreparatoryProceedingCost(0, 0);
			}
		}
		// dorobić dla spraw z wyboru
	}
	
	
//	Convert string in format "1,2,3,5,4,2" to the list of integers
//	Used to convert String provided by user in CriminalCourtCostForm
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
