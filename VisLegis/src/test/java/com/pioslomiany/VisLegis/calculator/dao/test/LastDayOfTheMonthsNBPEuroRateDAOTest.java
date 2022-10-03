package com.pioslomiany.VisLegis.calculator.dao.test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.web.client.RestTemplate;

import com.pioslomiany.VisLegis.calculator.dao.LastDayOfTheMonthsNBPEuroRateDAO;
import com.pioslomiany.VisLegis.calculator.entity.DateRangeForm;
import com.pioslomiany.VisLegis.calculator.entity.NBPExchangeRate;
import com.pioslomiany.VisLegis.calculator.entity.NBPExchangeRate.Rate;

public class LastDayOfTheMonthsNBPEuroRateDAOTest {
	
	private LastDayOfTheMonthsNBPEuroRateDAO euroRate;
	
	private final String START_DATE = "2021-10-03";
	private final String ONE_YEAR_TEST_END_DATE = "2022-10-03";
	
	List<Rate> oneYearExpectedResult;
	
	@BeforeEach
	public void dataPreparation() {
		euroRate = new LastDayOfTheMonthsNBPEuroRateDAO();
		
		oneYearExpectedResult = buildOneYearExpectedResult();
	}

	@Test
	void OneYearRangeTest() {
		
		NBPExchangeRate nbpExchangeRate = getNbpApiData(START_DATE, ONE_YEAR_TEST_END_DATE);
		
		euroRate.setStartEndDate(null);
		
		List<Rate> result = euroRate.buildLastDaysNBPExchangeRates(nbpExchangeRate.getRates());

		Assertions.assertEquals(oneYearExpectedResult, result);
	}
	
	@Test
	void moreThenOneYearTest() {
		
		String startDate = "2020-05-02";
		
		NBPExchangeRate nbpExchangeRate = getNbpApiData(startDate, ONE_YEAR_TEST_END_DATE);
		
		List<Rate> result = euroRate.buildLastDaysNBPExchangeRates(nbpExchangeRate.getRates());
		
		Assertions.assertEquals(oneYearExpectedResult, result);
	}

	
	@Test
	void oneDayRangeTest() {
		
		String startDate = ONE_YEAR_TEST_END_DATE;
		
		NBPExchangeRate nbpExchangeRate = getNbpApiData(startDate, ONE_YEAR_TEST_END_DATE);
		
		List<Rate> result = euroRate.buildLastDaysNBPExchangeRates(nbpExchangeRate.getRates());
		
		Assertions.assertTrue(result.isEmpty());
	}
	
	private NBPExchangeRate getNbpApiData(String startDate, String endDate) {
		RestTemplate restTemplate = new RestTemplate();
		StringBuilder apiUrlBuilder = new StringBuilder();
		
		apiUrlBuilder.append("http://api.nbp.pl/api/exchangerates/rates/a/eur/")
					.append(startDate)
					.append("/")
					.append(endDate);
		
		String url = apiUrlBuilder.toString();
		
		return restTemplate.getForObject(url, NBPExchangeRate.class);
	}
	
	private List<Rate> buildOneYearExpectedResult() {
		List<Rate> oneYearExpectedResult = new ArrayList<>();
		Rate rateNovember2021 = new NBPExchangeRate.Rate(LocalDate.parse("2021-11-30"), "4.6834");
		oneYearExpectedResult.add(rateNovember2021);
		Rate rateDecember2021 = new NBPExchangeRate.Rate(LocalDate.parse("2021-12-31"), "4.5994");
		oneYearExpectedResult.add(rateDecember2021);
		Rate rateJanuary2022 = new NBPExchangeRate.Rate(LocalDate.parse("2022-01-31"), "4.5982");
		oneYearExpectedResult.add(rateJanuary2022);
		Rate rateFebuary2022 = new NBPExchangeRate.Rate(LocalDate.parse("2022-02-28"), "4.6909");
		oneYearExpectedResult.add(rateFebuary2022);
		Rate rateMarch2022 = new NBPExchangeRate.Rate(LocalDate.parse("2022-03-31"), "4.6525");
		oneYearExpectedResult.add(rateMarch2022);
		Rate rateApril2022 = new NBPExchangeRate.Rate(LocalDate.parse("2022-04-29"), "4.6582");
		oneYearExpectedResult.add(rateApril2022);
		Rate rateMay2022 = new NBPExchangeRate.Rate(LocalDate.parse("2022-05-31"), "4.5756");
		oneYearExpectedResult.add(rateMay2022);
		Rate rateJune2022 = new NBPExchangeRate.Rate(LocalDate.parse("2022-06-30"), "4.6806");
		oneYearExpectedResult.add(rateJune2022);
		Rate rateJuly2022 = new NBPExchangeRate.Rate(LocalDate.parse("2022-07-29"), "4.7399");
		oneYearExpectedResult.add(rateJuly2022);
		Rate rateAugust2022 = new NBPExchangeRate.Rate(LocalDate.parse("2022-08-31"), "4.7265");
		oneYearExpectedResult.add(rateAugust2022);
		Rate rateSeptember2022 = new NBPExchangeRate.Rate(LocalDate.parse("2022-09-30"), "4.8698");
		oneYearExpectedResult.add(rateSeptember2022);
		Rate rateOctober2022 = new NBPExchangeRate.Rate(LocalDate.parse("2022-10-03"), "4.8272");
		oneYearExpectedResult.add(rateOctober2022);
		
		return oneYearExpectedResult;
	}
	

	
	
}
