package com.pioslomiany.DDSProject.calculator.dao;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import com.pioslomiany.DDSProject.calculator.entity.NBPExchangeRate;
import com.pioslomiany.DDSProject.calculator.entity.NBPExchangeRate.Rate;

@Repository
public class NBPExchangeRateDAO {
	
	NBPExchangeRate nbpExchangeRate;

	public List<Rate> getLastDaysNBPExchangeRates(String startDate, String endDate) {
		
		RestTemplate restTemplate = new RestTemplate();
		
		String url = "http://api.nbp.pl/api/exchangerates/rates/a/eur/" + startDate + "/" + endDate;
		
		nbpExchangeRate = restTemplate.getForObject(url, NBPExchangeRate.class);
		
		return buildLastDaysNBPExchangeRates(nbpExchangeRate.getRates());
	}
	
	
//	This method return the List of Strings with the dates of last days of each month from specific range
	private List<String> buildLastDaysOfTheMonthsList(List<Rate> nbpExchangeRate) {
		List<String> lastDaysOfTheMonthsList = new ArrayList<>();
		
		//this are the steps to for the last months
		LocalDate localDate = LocalDate.parse(nbpExchangeRate.get(nbpExchangeRate.size()-1).getEffectiveDate());
		
		localDate = setDateForTheLastDayOfTheMonth(localDate);
		
		lastDaysOfTheMonthsList.add(localDate.toString());
		
		
		// this fragment is needed to establish number of months for the iteration (for loop)
		LocalDate startDate = LocalDate.parse(nbpExchangeRate.get(0).getEffectiveDate());
		LocalDate endDate = LocalDate.parse(nbpExchangeRate.get(nbpExchangeRate.size()-1).getEffectiveDate());
		
		Period age = Period.between(startDate, endDate);
		int months = age.getMonths();
		
		for(int i = 0; i < months; i++) {
			localDate = localDate.minusMonths(1);
			
			localDate = setDateForTheLastDayOfTheMonth(localDate);
			
			lastDaysOfTheMonthsList.add(localDate.toString());
		}
		
		Collections.reverse(lastDaysOfTheMonthsList);
		
		return lastDaysOfTheMonthsList;
	}
	
	private LocalDate setDateForTheLastDayOfTheMonth (LocalDate localDate) {
		int lastDayOfTheMonth = localDate.lengthOfMonth();
		String temp = localDate.toString();
		temp = temp.substring(0, 8) + lastDayOfTheMonth;
		return LocalDate.parse(temp);
	}

	
/*	this method returns list of rates for the last days of the months from specific range
	* it compares two list to find the last day of the month that was received from the api
	* if the last day specified by the user is a suturday/sunday/bank holiday api does not return this value
	* so the last day of the month returned by the api must be found
*/
	public List<Rate> buildLastDaysNBPExchangeRates(List<Rate> nbpExchangeRate) {
		
		List<Rate> lastDaysNBPExchangeRates = new ArrayList<>();
		
		List<String> lastDaysOfTheMonthsList = buildLastDaysOfTheMonthsList(nbpExchangeRate);
		
		int flag = 3;
		LocalDate localDate;

		for (int i = 0; i < lastDaysOfTheMonthsList.size(); i++) {
			for (int j = 0; j < nbpExchangeRate.size(); j++) {
				//if the last day of the month is the same as the last from api it is added to the list
				if (lastDaysOfTheMonthsList.get(i).equals(nbpExchangeRate.get(j).getEffectiveDate())) {
					lastDaysNBPExchangeRates.add(nbpExchangeRate.get(j));
					flag = 1;
					break;
				}
			}
			if (flag == 0) {
				/* if day was not added flag == 0 and the method needs to find earlier day
				 * the date on the list of last days of the months is changed minus one day 
				 * until it is the same as the date from api
				 * if is the same the flag == 1 and this fragment is skipped
				*/
				localDate = LocalDate.parse(lastDaysOfTheMonthsList.get(i));
				localDate = localDate.minusDays(1);
				lastDaysOfTheMonthsList.set(i, localDate.toString());
				i--;
			}
			flag = 0;
		}
		
		return lastDaysNBPExchangeRates;
	}
	

}
