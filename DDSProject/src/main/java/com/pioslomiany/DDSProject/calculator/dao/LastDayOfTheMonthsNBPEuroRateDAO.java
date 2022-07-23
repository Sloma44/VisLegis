package com.pioslomiany.DDSProject.calculator.dao;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import com.pioslomiany.DDSProject.calculator.entity.DateRangeForm;
import com.pioslomiany.DDSProject.calculator.entity.NBPExchangeRate;
import com.pioslomiany.DDSProject.calculator.entity.NBPExchangeRate.Rate;

@Repository
public class LastDayOfTheMonthsNBPEuroRateDAO {
	
	NBPExchangeRate nbpExchangeRate;
	
	private DateRangeForm dateRange;
	
	public void setStartEndDate(DateRangeForm dateRange) {
		this.dateRange = dateRange;
	}

	public List<Rate> getLastDaysNBPExchangeRates() {
		checkDateRange();
		
		RestTemplate restTemplate = new RestTemplate();
		
		String url = "http://api.nbp.pl/api/exchangerates/rates/a/eur/" + dateRange.getStartDate().toString() + "/" + dateRange.getEndDate().toString();
		
		nbpExchangeRate = restTemplate.getForObject(url, NBPExchangeRate.class);
		
		return buildLastDaysNBPExchangeRates(nbpExchangeRate.getRates());
	}
	
	
	/* NBP api maximal period to be checked with one request is the range of 367 days
	 * This method check if the dates and if the days difference is grater then 367 it changes the start date
	 * Here is potential of future development. It could send a series of request to API to get the desired date range,
	 * instead of just limiting the date
	*/
	private void checkDateRange() {
		long diff = 0;
		
	    Date firstDate = Date.from(dateRange.getStartDate().atStartOfDay(ZoneId.systemDefault()).toInstant());
		Date secondDate = Date.from(dateRange.getEndDate().atStartOfDay(ZoneId.systemDefault()).toInstant());	    	
		long diffInMillies = Math.abs(secondDate.getTime() - firstDate.getTime());
		diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);

		if (diff > 367) {
			LocalDate newStartDate = dateRange.getEndDate().minusDays(364);
			dateRange.setStartDate(newStartDate);
		}
	}
	
	
//	This method return the List of Strings with the dates of last days of each month from specific range
	private List<LocalDate> buildLastDaysOfTheMonthsList(List<Rate> nbpExchangeRate) {
		List<LocalDate> lastDaysOfTheMonthsList = new ArrayList<>();
		
		//this are the steps to for the last months
		LocalDate localDate = nbpExchangeRate.get(nbpExchangeRate.size()-1).getEffectiveDate();
		
		localDate = setDateForTheLastDayOfTheMonth(localDate);
		
		lastDaysOfTheMonthsList.add(localDate);
		
		
		// this fragment is needed to establish number of months for the iteration (for loop)
		LocalDate startDate = nbpExchangeRate.get(0).getEffectiveDate();
		LocalDate endDate = nbpExchangeRate.get(nbpExchangeRate.size()-1).getEffectiveDate();
		
		Period age = Period.between(startDate, endDate);
		int months = age.getMonths();
		
		for(int i = 0; i < months; i++) {
			localDate = localDate.minusMonths(1);
			
			localDate = setDateForTheLastDayOfTheMonth(localDate);
			
			lastDaysOfTheMonthsList.add(localDate);
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
		
		List<LocalDate> lastDaysOfTheMonthsList = buildLastDaysOfTheMonthsList(nbpExchangeRate);
		
		int flag = 3;
		LocalDate localDate;

		for (int i = 0; i < lastDaysOfTheMonthsList.size(); i++) {
			for (int j = 0; j < nbpExchangeRate.size(); j++) {
				//if the last day of the month is the same as the last from api it is added to the list
				if (lastDaysOfTheMonthsList.get(i).isEqual(nbpExchangeRate.get(j).getEffectiveDate())) {
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
				localDate = lastDaysOfTheMonthsList.get(i);
				localDate = localDate.minusDays(1);
				lastDaysOfTheMonthsList.set(i, localDate);
				i--;
			}
			flag = 0;
		}
		
		return lastDaysNBPExchangeRates;
	}
	

}
