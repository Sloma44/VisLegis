package com.pioslomiany.VisLegis.calculator.client;

import org.springframework.web.client.RestTemplate;

import com.pioslomiany.VisLegis.calculator.entity.DateRangeForm;
import com.pioslomiany.VisLegis.calculator.entity.NBPExchangeRate;

public class NBPApiClient {

	private DateRangeForm dateRange;
	
	private final String NBP_API_URL = "http://api.nbp.pl/api/exchangerates/rates/a/eur/";
	
	public NBPApiClient(DateRangeForm dateRange) {
		this.dateRange = dateRange;
	}
	
	public NBPExchangeRate getNbpExchangeRateFromApi() {
		
		RestTemplate restTemplate = new RestTemplate();
		
		String url = buildUrl();
		
		return restTemplate.getForObject(url, NBPExchangeRate.class);
	}
	
	private String buildUrl() {
		String startDate = dateRange.getStartDate().toString();
		String endDate = dateRange.getEndDate().toString();
		
		StringBuilder urlBuilder = new StringBuilder()
				.append(NBP_API_URL)
				.append(startDate)
				.append("/")
				.append(endDate);
		
		return urlBuilder.toString();
	}
	
}
