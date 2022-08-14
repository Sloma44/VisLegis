package com.pioslomiany.VisLegis.calculator.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.pioslomiany.VisLegis.calculator.entity.CriminalCalculatorConstances;
import com.pioslomiany.VisLegis.calculator.entity.CriminalCourtCost;
import com.pioslomiany.VisLegis.calculator.entity.CriminalCourtCostForm;
import com.pioslomiany.VisLegis.calculator.entity.PreparatoryProceeding;
import com.pioslomiany.VisLegis.calculator.service.CalculatorService;

import lombok.Setter;

/*("Koszt spraw karnych")*/

@Repository
public class CriminalCourtCostDAO {
	
	@Setter
	CalculatorService calculatorService;
	
	@Setter
	private CriminalCourtCostForm criminalCourtCostForm;
	
	private PreparatoryProceeding preparatoryProceeding;
	
	private List<CriminalCourtCost> resultsList;

	private List<Double> allCostsSums;

	private List<Double> allFirstInstancCostsSums;

	private List<Double> allSecondInstancCostsSums;
	
	public CriminalCourtCostDAO() {
		resultsList = new ArrayList<>();
		allCostsSums = new ArrayList<>();
		allFirstInstancCostsSums = new ArrayList<>();
		allSecondInstancCostsSums = new ArrayList<>();
	}
	
//	Based on User response in the form this method generate PreparatoryProceeding costs
//	Load the stakes from DB
//	There are 4 kinds of stakes in DB identified by id: 1, 2, 7, 8
// 	If User do not chose preparatory proceeding the values is 0
	public PreparatoryProceeding getPreparatoryProceeding() {
		preparatoryProceeding = new PreparatoryProceeding(criminalCourtCostForm, calculatorService);
		return preparatoryProceeding;
	}
	

//	Based on User response in the form this method generate cost for different Courts with different stakes
//	Load the stakes from DB
//	There are 4 kinds of stakes in DB identified by id: 3, 4, 5, 6, 9, 10, 11, 12
// 	In the end it summarize tree kinds of costs: 2 sort of courts and general sum 
	public List<CriminalCourtCost> getResultsList() {
		
		double firstInstanceBaseValue;
		double secondInstanceBaseValue;
		double vat;
		double courtHearingPercentage = calculatorService.getEntityValueById(13);

		List<Integer> firstInstanceOccurence = convertStringToIntegerList(criminalCourtCostForm.getFirstInstance());
		List<Integer> secondInstanceOccurence = convertStringToIntegerList(criminalCourtCostForm.getSecondInstance());
		
		if(!criminalCourtCostForm.getByChoice()) {
			vat = calculatorService.getEntityValueById(15);
			if(!criminalCourtCostForm.getHigherCourt()) {
				firstInstanceBaseValue = calculatorService.getEntityValueById(3);
				secondInstanceBaseValue = calculatorService.getEntityValueById(4);				
			} else {
				firstInstanceBaseValue = calculatorService.getEntityValueById(5);
				secondInstanceBaseValue = calculatorService.getEntityValueById(6);	
			}
		} else {
			vat = 0;
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
//		allCostsSums = generateCostSums(null);
//		allFirstInstancCostsSums = generateCostSums(CriminalCalculatorConstances.FIRST_INSTANCE_NAME);
//		allSecondInstancCostsSums = generateCostSums(CriminalCalculatorConstances.SECOND_INSTANCE_NAME);	
		
		return resultsList;
	}
	
	public List<Double> getAllCostsSums() {
		allCostsSums = generateCostSums(null);
		return allCostsSums;
	}

	public List<Double> getAllFirstInstancCostsSums() {
		allFirstInstancCostsSums = generateCostSums(CriminalCalculatorConstances.FIRST_INSTANCE_NAME);
		return allFirstInstancCostsSums;
	}

	public List<Double> getAllSecondInstancCostsSums() {
		allSecondInstancCostsSums = generateCostSums(CriminalCalculatorConstances.SECOND_INSTANCE_NAME);
		return allSecondInstancCostsSums;
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
