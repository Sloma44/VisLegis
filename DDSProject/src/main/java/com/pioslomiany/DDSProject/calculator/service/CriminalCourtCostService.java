package com.pioslomiany.DDSProject.calculator.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.pioslomiany.DDSProject.calculator.entity.CriminalCalculatorConstances;
import com.pioslomiany.DDSProject.calculator.entity.CriminalCourtCost;
import com.pioslomiany.DDSProject.calculator.entity.CriminalCourtCostForm;
import com.pioslomiany.DDSProject.calculator.entity.CriminalCourtPreparatoryProceedingCost;

import lombok.Getter;

public class CriminalCourtCostService {
	
	CalculatorService calculatorService;

	//tax stake
	private double vat;

	private CriminalCourtCostForm criminalCourtCostForm;
	
	// references that are required in the templates to generate the tables
	@Getter
	private CriminalCourtPreparatoryProceedingCost preparatoryProceeding;
	@Getter
	private List<CriminalCourtCost> resultsList;
	@Getter
	private List<Double> allCostsSums;
	@Getter
	private List<Double> allFirstInstancCostsSums;
	@Getter
	private List<Double> allSecondInstancCostsSums;
	
	// list of court hearings for first and second court instance
	private List<Integer> firstInstanceOccurence;
	private List<Integer> secondInstanceOccurence;
	
	public CriminalCourtCostService(CriminalCourtCostForm criminalCourtCostForm, CalculatorService calculatorService) {
		this.criminalCourtCostForm = criminalCourtCostForm;
		this.calculatorService = calculatorService;
		
		preparatoryProceeding = new CriminalCourtPreparatoryProceedingCost(0, 0);
		resultsList = new ArrayList<>();
		allCostsSums = new ArrayList<>();
		allFirstInstancCostsSums = new ArrayList<>();
		allSecondInstancCostsSums = new ArrayList<>();
		
		vat = calculatorService.getEntityValueById(15);
	}

//	This one method runs all method in this class in the right order to generate the calculation
	public void buildCriminalCalculation() {
		buildPreparatoryProceeding();
		buildResultList();
	}
	
	
//	Based on User response in the form this method generate cost for different Courts with different stakes
//	Load the stakes from DB
//	There are 4 kinds of stakes in DB identified by id: 3, 4, 5, 6, 9, 10, 11, 12
// 	In the end it summarize tree kinds of costs: 2 sort of courts and general sum 
	private void buildResultList() {
		
		double firstInstanceBaseValue;
		double secondInstanceBaseValue;
		double courtHearingPercentage = calculatorService.getEntityValueById(13);

		firstInstanceOccurence = convertStringToIntegerList(criminalCourtCostForm.getFirstInstance());
		secondInstanceOccurence = convertStringToIntegerList(criminalCourtCostForm.getSecondInstance());
		
		if(!criminalCourtCostForm.getByChoice()) {
			if(!criminalCourtCostForm.getHigherCourt()) {
				firstInstanceBaseValue = calculatorService.getEntityValueById(3);
				secondInstanceBaseValue = calculatorService.getEntityValueById(4);				
			} else {
				firstInstanceBaseValue = calculatorService.getEntityValueById(5);
				secondInstanceBaseValue = calculatorService.getEntityValueById(6);	
			}
		} else {
			if(!criminalCourtCostForm.getHigherCourt()) {
				firstInstanceBaseValue = calculatorService.getEntityValueById(9);
				secondInstanceBaseValue = calculatorService.getEntityValueById(10);				
			} else {
				firstInstanceBaseValue = calculatorService.getEntityValueById(11);
				secondInstanceBaseValue = calculatorService.getEntityValueById(12);	
			}
		}
		

//		Merging both criminalCourtCost alternately	
		Iterator<Integer> itA = firstInstanceOccurence.iterator();
	    Iterator<Integer> itB = secondInstanceOccurence.iterator();
	    
	    while (itA.hasNext() || itB.hasNext()) {
	        if (itA.hasNext()) {
	        	resultsList.add(new CriminalCourtCost(true, firstInstanceBaseValue, vat, itA.next(), courtHearingPercentage));
	        }
	        if (itB.hasNext()) {
	        	resultsList.add(new CriminalCourtCost(false, secondInstanceBaseValue, vat, itB.next(), courtHearingPercentage));
	        }
	    }
	    
//		If number of court hearing is 0 it is removed from the list
	   for (int i = 0; i < resultsList.size(); i++) {
		   if (resultsList.get(i).getNumberOfHearings() == 0) {
			   resultsList.remove(i);
			   i--;
		   }
	   }
		
		//generate summation of costs
		allCostsSums = generateCostSums(null);
		allFirstInstancCostsSums = generateCostSums(CriminalCalculatorConstances.FIRST_INSTANCE_NAME);
		allSecondInstancCostsSums = generateCostSums(CriminalCalculatorConstances.SECOND_INSTANCE_NAME);			
	}
	
	
// 	Generate summation of all cost based by the Court Name (courtName is defined as final in class CriminalCalculatorConstance)
// if String id null it summarize all cost 
	private List<Double> generateCostSums(String instance) {
		double bonus = calculatorService.getEntityValueById(14);
		
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
		
//		Adding to general sum cost of preparatory proceeding
		if (instance == null) {
			sumNet += preparatoryProceeding.getBaseValue();
			sumVat += preparatoryProceeding.getVatValue();
			sumGross += preparatoryProceeding.getSum();
		}
		
		list.add(CriminalCalculatorConstances.roundUp(sumNet));
		list.add(CriminalCalculatorConstances.roundUp(sumVat));
		list.add(CriminalCalculatorConstances.roundUp(sumGross));
		list.add(CriminalCalculatorConstances.roundUp(sumGross + (sumGross * (bonus / 100))));
		
		return list;
	}
	
	
//	Based on User response in the form this method generate PreparatoryProceeding costs
//	Load the stakes from DB
//	There are 4 kinds of stakes in DB identified by id: 1, 2, 7, 8
// 	If User do not chose preparatory proceeding the values is 0
	private void buildPreparatoryProceeding() {
		double valueInvestigationLower;
		double valueInvestigationHigher;
		
		
		if (!criminalCourtCostForm.getByChoice()) {
			valueInvestigationLower = calculatorService.getEntityValueById(1);
			valueInvestigationHigher = calculatorService.getEntityValueById(2);
		} else {
			valueInvestigationLower = calculatorService.getEntityValueById(7);
			valueInvestigationHigher = calculatorService.getEntityValueById(8);
		}	
		
		if(criminalCourtCostForm.getPreparatoryProceeding()) {
			if (criminalCourtCostForm.getInvestigation()) {
				preparatoryProceeding = new CriminalCourtPreparatoryProceedingCost(valueInvestigationLower, vat);
			} else {
				preparatoryProceeding = new CriminalCourtPreparatoryProceedingCost(valueInvestigationHigher, vat);
			}
		} else {
			preparatoryProceeding = new CriminalCourtPreparatoryProceedingCost(0, 0);
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
