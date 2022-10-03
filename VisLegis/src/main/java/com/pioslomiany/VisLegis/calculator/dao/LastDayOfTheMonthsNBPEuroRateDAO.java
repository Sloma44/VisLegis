package com.pioslomiany.VisLegis.calculator.dao;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import com.pioslomiany.VisLegis.calculator.client.NBPApiClient;
import com.pioslomiany.VisLegis.calculator.entity.DateRangeForm;
import com.pioslomiany.VisLegis.calculator.entity.NBPExchangeRate;
import com.pioslomiany.VisLegis.calculator.entity.NBPExchangeRate.Rate;

/*("Kurs euro NBP dla ostaniego dnia miesiąca")*/

@Repository
public class LastDayOfTheMonthsNBPEuroRateDAO {
	
	private DateRangeForm dateRange;

	public List<Rate> getLastDaysNBPExchangeRates() {
		
		NBPApiClient nbpClient = new NBPApiClient(dateRange);
		
		NBPExchangeRate nbpExchangeRate = nbpClient.getNbpExchangeRateFromApi();
		
		List<Rate> nbpExchangeRateForLastDaysOfMonths = buildLastDaysNBPExchangeRates(nbpExchangeRate);
		
		return nbpExchangeRateForLastDaysOfMonths;
	}
	
	private List<Rate> buildLastDaysNBPExchangeRates(NBPExchangeRate nbpExchangeRate) {
		List<Rate> rateList = nbpExchangeRate.getRates();
		
		Map<String, List<Rate>> map = rateList.stream()
										.collect(Collectors.groupingBy((Rate r) -> 
										r.getEffectiveDate().toString().substring(0, 7)));

		List<Rate> result = new ArrayList<>();
		
		for(String s : map.keySet()) {
			int listSizeByMapKey = map.get(s).size() - 1;
			Rate LastDayRateByMapKey = map.get(s).get(listSizeByMapKey);
			result.add(LastDayRateByMapKey);
		}
		
		result.sort((Rate r1, Rate r2) -> r1.getEffectiveDate().compareTo(r2.getEffectiveDate()));
		
		return result;
	}
	
	public void setDateRange(DateRangeForm dateRange) {
		if (isLowerThenOneYear(dateRange)) {
			this.dateRange = dateRange;			
		} else {
			this.dateRange = changeStartDate(dateRange);
		}
	}
	
	private boolean isLowerThenOneYear(DateRangeForm dateRange) {
		int VALID_TIME_RANGE_IN_DAYS = 367;
		long dateDifferendeInDays =  getTimeDifferenceInDays(dateRange);
		
		if (dateDifferendeInDays > VALID_TIME_RANGE_IN_DAYS) {
			return false;
		}
		return true;
	}

	private DateRangeForm changeStartDate(DateRangeForm dateRange) {
		LocalDate newStartDate = dateRange.getEndDate().minusDays(364);
		dateRange.setStartDate(newStartDate);
		return dateRange;
	}
	
	
	private long getTimeDifferenceInDays(DateRangeForm dateRange) {
		Date startDate = Date.from(dateRange.getStartDate().atStartOfDay(ZoneId.systemDefault()).toInstant());
		Date endDate = Date.from(dateRange.getEndDate().atStartOfDay(ZoneId.systemDefault()).toInstant());
		
		long dateDifferendeInMilliseconds = Math.abs(startDate.getTime() - endDate.getTime());
		
		return TimeUnit.DAYS.convert(dateDifferendeInMilliseconds, TimeUnit.MILLISECONDS);
	}

}
