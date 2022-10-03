package com.pioslomiany.VisLegis.calculator.dao.test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.pioslomiany.VisLegis.calculator.dao.LastDayOfTheMonthsNBPEuroRateDAO;
import com.pioslomiany.VisLegis.calculator.entity.DateRangeForm;
import com.pioslomiany.VisLegis.calculator.entity.NBPExchangeRate;
import com.pioslomiany.VisLegis.calculator.entity.NBPExchangeRate.Rate;

public class LastDayOfTheMonthsNBPEuroRateDAOTest {
	
	NBPExchangeRate nbpExchangeRate;
	LastDayOfTheMonthsNBPEuroRateDAO lastDayOfTheMonthsNbpEuroRate;
	
	private final LocalDate ONE_YEAR_TEST_START_DATE = LocalDate.parse("2021-10-03");
	private final LocalDate ONE_YEAR_TEST_END_DATE = LocalDate.parse("2022-10-03");
	
	List<Rate> oneYearResult;
	
	@BeforeEach
	public void dataPreparation() {
		oneYearResult = buildOneYearExpectedResult();
	}

	@Test
	void OneYearRangeTest() {
		lastDayOfTheMonthsNbpEuroRate = new LastDayOfTheMonthsNBPEuroRateDAO();
		
		DateRangeForm dateRange = new DateRangeForm();
		dateRange.setStartDate(ONE_YEAR_TEST_START_DATE);
		dateRange.setEndDate(ONE_YEAR_TEST_END_DATE);
		
		
		lastDayOfTheMonthsNbpEuroRate.setDateRange(dateRange);

		List<Rate> result = lastDayOfTheMonthsNbpEuroRate
				.getLastDaysNBPExchangeRates();

		Assertions.assertEquals(oneYearResult, result);
	}
	
	@Test
	void moreThenOneYearTest() {
		lastDayOfTheMonthsNbpEuroRate = new LastDayOfTheMonthsNBPEuroRateDAO();
		
		DateRangeForm dateRange = new DateRangeForm();
		dateRange.setStartDate(LocalDate.parse("2020-05-03"));
		dateRange.setEndDate(ONE_YEAR_TEST_END_DATE);
		
		lastDayOfTheMonthsNbpEuroRate.setDateRange(dateRange);
		
		List<Rate> result = lastDayOfTheMonthsNbpEuroRate
						.getLastDaysNBPExchangeRates();

		Assertions.assertEquals(oneYearResult, result);
	}

	
	@Test
	void oneDayRangeTest() {
		lastDayOfTheMonthsNbpEuroRate = new LastDayOfTheMonthsNBPEuroRateDAO();
		
		DateRangeForm dateRange = new DateRangeForm();
		dateRange.setStartDate(ONE_YEAR_TEST_END_DATE);
		dateRange.setEndDate(ONE_YEAR_TEST_END_DATE);
		
		lastDayOfTheMonthsNbpEuroRate.setDateRange(dateRange);
		
		List<Rate> result = lastDayOfTheMonthsNbpEuroRate
						.getLastDaysNBPExchangeRates();
		
		List<Rate> expected = new ArrayList<>();
		Rate oneRateExpected = new NBPExchangeRate.Rate(LocalDate.parse("2022-10-03"), "4.8272");
		expected.add(oneRateExpected);

		Assertions.assertEquals(expected, result);
	}
	
	@Test
	void alterantiveMehodTest() {
		lastDayOfTheMonthsNbpEuroRate = new LastDayOfTheMonthsNBPEuroRateDAO();
		
		DateRangeForm dateRange = new DateRangeForm();
		dateRange.setStartDate(ONE_YEAR_TEST_START_DATE);
		dateRange.setEndDate(ONE_YEAR_TEST_END_DATE);
		
		lastDayOfTheMonthsNbpEuroRate.setDateRange(dateRange);

		List<Rate> result = lastDayOfTheMonthsNbpEuroRate
				.getLastDaysNBPExchangeRates();

		Assertions.assertEquals(oneYearResult, result);
	}
	
	
	
	private List<Rate> buildOneYearExpectedResult() {
		List<Rate> expected = new ArrayList<>();
		Rate rateOctober2021 = new NBPExchangeRate.Rate(LocalDate.parse("2021-10-29"), "4.6208");
		expected.add(rateOctober2021);
		Rate rateNovember2021 = new NBPExchangeRate.Rate(LocalDate.parse("2021-11-30"), "4.6834");
		expected.add(rateNovember2021);
		Rate rateDecember2021 = new NBPExchangeRate.Rate(LocalDate.parse("2021-12-31"), "4.5994");
		expected.add(rateDecember2021);
		Rate rateJanuary2022 = new NBPExchangeRate.Rate(LocalDate.parse("2022-01-31"), "4.5982");
		expected.add(rateJanuary2022);
		Rate rateFebuary2022 = new NBPExchangeRate.Rate(LocalDate.parse("2022-02-28"), "4.6909");
		expected.add(rateFebuary2022);
		Rate rateMarch2022 = new NBPExchangeRate.Rate(LocalDate.parse("2022-03-31"), "4.6525");
		expected.add(rateMarch2022);
		Rate rateApril2022 = new NBPExchangeRate.Rate(LocalDate.parse("2022-04-29"), "4.6582");
		expected.add(rateApril2022);
		Rate rateMay2022 = new NBPExchangeRate.Rate(LocalDate.parse("2022-05-31"), "4.5756");
		expected.add(rateMay2022);
		Rate rateJune2022 = new NBPExchangeRate.Rate(LocalDate.parse("2022-06-30"), "4.6806");
		expected.add(rateJune2022);
		Rate rateJuly2022 = new NBPExchangeRate.Rate(LocalDate.parse("2022-07-29"), "4.7399");
		expected.add(rateJuly2022);
		Rate rateAugust2022 = new NBPExchangeRate.Rate(LocalDate.parse("2022-08-31"), "4.7265");
		expected.add(rateAugust2022);
		Rate rateSeptember2022 = new NBPExchangeRate.Rate(LocalDate.parse("2022-09-30"), "4.8698");
		expected.add(rateSeptember2022);
		Rate rateOctober2022 = new NBPExchangeRate.Rate(LocalDate.parse("2022-10-03"), "4.8272");
		expected.add(rateOctober2022);
		
		return expected;
	}
	
	
}
