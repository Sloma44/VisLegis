package com.pioslomiany.DDSProject.calculator.service;

import java.util.ArrayList;
import java.util.List;

import com.pioslomiany.DDSProject.calculator.entity.CriminalCalculatorConstances;
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
	private final double OKR_I_INST = 600;
	private final double APEL_II_INST = 800;
	private final double SLEDZTWO_WYB = 50.0;
	private final double DOCHODZENIE_WYB = 60.0;
	private final double REJ_I_INST_WYB = 70;
	private final double OKR_II_INST_WYB = 80;
	private final double OKR_I_INST_WYB = 90;
	private final double APEL_II_INST_WYB = 100;
	private final double VAT = 23;
	private final double NEXT_COURT_HEARING_PERCENTAGE = 20;
	private final double PREMIA = 50;
	
	private CriminalCourtCostForm criminalCourtCostForm;
	
	private CriminalCourtPreparatoryProceedingCost preparatoryProceeding;
	private List<CriminalCourtCost> resultsList;
	
	private List<Double> allCostsSums;
	private List<Double> allFirstInstancCostsSums;
	private List<Double> allSecondInstancCostsSums;
	
	
	private List<Integer> firstInstanceOccurence;
	private List<Integer> secondInstanceOccurence;
	
	public CriminalCourtCostService(CriminalCourtCostForm criminalCourtCostForm) {
		this.criminalCourtCostForm = criminalCourtCostForm;
		resultsList = new ArrayList<>();
		allCostsSums = new ArrayList<>();
		allFirstInstancCostsSums = new ArrayList<>();
		allSecondInstancCostsSums = new ArrayList<>();
	}
	
	public void buildCriminalCalculation() {
		buildPreparatoryProceeding();
		buildResultList();
	}
	
	private void buildResultList() {

		firstInstanceOccurence = convertStringToIntegerList(criminalCourtCostForm.getFirstInstance());
		secondInstanceOccurence = convertStringToIntegerList(criminalCourtCostForm.getSecondInstance());

		
		if(!criminalCourtCostForm.getByChoice()) {
			if(!criminalCourtCostForm.getHigherCourt()) {		
				for (int i = 0; i < secondInstanceOccurence.size(); i++) {
					resultsList.add(new CriminalCourtCost(true, REJ_I_INST, VAT, firstInstanceOccurence.get(i), NEXT_COURT_HEARING_PERCENTAGE));
					resultsList.add(new CriminalCourtCost(false, OKR_II_INST, VAT, secondInstanceOccurence.get(i), NEXT_COURT_HEARING_PERCENTAGE));
				}
				if (firstInstanceOccurence.size() > secondInstanceOccurence.size()) {
					int lastIndex = firstInstanceOccurence.get(firstInstanceOccurence.size()-1);
					resultsList.add(new CriminalCourtCost(true, REJ_I_INST, VAT, lastIndex, NEXT_COURT_HEARING_PERCENTAGE));
				}
			} else {
				for (int i = 0; i < secondInstanceOccurence.size(); i++) {
					resultsList.add(new CriminalCourtCost(true, OKR_I_INST, VAT, firstInstanceOccurence.get(i), NEXT_COURT_HEARING_PERCENTAGE));
					resultsList.add(new CriminalCourtCost(false, APEL_II_INST, VAT, secondInstanceOccurence.get(i), NEXT_COURT_HEARING_PERCENTAGE));
				}
				if (firstInstanceOccurence.size() > secondInstanceOccurence.size()) {
					int lastIndex = firstInstanceOccurence.get(firstInstanceOccurence.size()-1);
					resultsList.add(new CriminalCourtCost(true, OKR_I_INST, VAT, lastIndex, NEXT_COURT_HEARING_PERCENTAGE));
				}
			}
		} else {
			if(!criminalCourtCostForm.getHigherCourt()) {		
				for (int i = 0; i < secondInstanceOccurence.size(); i++) {
					resultsList.add(new CriminalCourtCost(true, REJ_I_INST_WYB, VAT, firstInstanceOccurence.get(i), NEXT_COURT_HEARING_PERCENTAGE));
					resultsList.add(new CriminalCourtCost(false, OKR_II_INST_WYB, VAT, secondInstanceOccurence.get(i), NEXT_COURT_HEARING_PERCENTAGE));
				}
				if (firstInstanceOccurence.size() > secondInstanceOccurence.size()) {
					int lastIndex = firstInstanceOccurence.get(firstInstanceOccurence.size()-1);
					resultsList.add(new CriminalCourtCost(true, REJ_I_INST_WYB, VAT, lastIndex, NEXT_COURT_HEARING_PERCENTAGE));
				}
			} else {
				for (int i = 0; i < secondInstanceOccurence.size(); i++) {
					resultsList.add(new CriminalCourtCost(true, OKR_I_INST_WYB, VAT, firstInstanceOccurence.get(i), NEXT_COURT_HEARING_PERCENTAGE));
					resultsList.add(new CriminalCourtCost(false, APEL_II_INST_WYB, VAT, secondInstanceOccurence.get(i), NEXT_COURT_HEARING_PERCENTAGE));
				}
				if (firstInstanceOccurence.size() > secondInstanceOccurence.size()) {
					int lastIndex = firstInstanceOccurence.get(firstInstanceOccurence.size()-1);
					resultsList.add(new CriminalCourtCost(true, OKR_I_INST_WYB, VAT, lastIndex, NEXT_COURT_HEARING_PERCENTAGE));
				}
			}
		}
		

		allCostsSums = generateCostSums(null);
		allFirstInstancCostsSums = generateCostSums(CriminalCalculatorConstances.FIRST_INSTANCE_NAME);
		allSecondInstancCostsSums = generateCostSums(CriminalCalculatorConstances.SECOND_INSTANCE_NAME);			
		
	}
	
	private List<Double> generateCostSums(String instance) {
		List<Double> list = new ArrayList<>();
		
		double sumNet = 0;
		double sumVat = 0;
		double sumGross = 0;
		
		for(CriminalCourtCost result : resultsList) {
			if (result.getName().equals(instance)) {
				sumNet += result.getSumNet();
				sumVat += result.getSumVat();
				sumGross += result.getSumGross();				
			}
			
			if(instance == null) {
				sumNet += result.getSumNet();
				sumVat += result.getSumVat();
				sumGross += result.getSumGross();	
			}
		}
		
		list.add(CriminalCalculatorConstances.roundUp(sumNet));
		list.add(CriminalCalculatorConstances.roundUp(sumVat));
		list.add(CriminalCalculatorConstances.roundUp(sumGross));
		list.add(CriminalCalculatorConstances.roundUp(sumGross + (sumGross * (PREMIA / 100))));
		
		return list;
		
	}
	
	
	private void buildPreparatoryProceeding() {
		if (!criminalCourtCostForm.getByChoice()) {
			if(criminalCourtCostForm.getPreparatoryProceeding()) {
				if (criminalCourtCostForm.getInvestigation() == true) {
					preparatoryProceeding = new CriminalCourtPreparatoryProceedingCost(DOCHODZENIE, VAT);
				} else {
					preparatoryProceeding = new CriminalCourtPreparatoryProceedingCost(SLEDZTWO, VAT);
				}
			} else {
				preparatoryProceeding = new CriminalCourtPreparatoryProceedingCost(0, 0);
			}
		} else {
			if(criminalCourtCostForm.getPreparatoryProceeding()) {
				if (criminalCourtCostForm.getInvestigation() == true) {
					preparatoryProceeding = new CriminalCourtPreparatoryProceedingCost(DOCHODZENIE_WYB, VAT);
				} else {
					preparatoryProceeding = new CriminalCourtPreparatoryProceedingCost(SLEDZTWO_WYB, VAT);
				}
			} else {
				preparatoryProceeding = new CriminalCourtPreparatoryProceedingCost(0, 0);
			}
		}
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
