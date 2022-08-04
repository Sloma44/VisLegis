package com.pioslomiany.VisLegis.calculator.entity;

import java.time.LocalDate;

import javax.validation.constraints.Min;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pioslomiany.VisLegis.calculator.validators.CurrentDateConstraint;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public class RecompenseRate {
	
	private String invoice;
	
	@CurrentDateConstraint
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private LocalDate invoiceDate;
	
	@CurrentDateConstraint
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private LocalDate dateOfPayement;
	
	@Min(0)
	private double valueGross;
	
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private LocalDate payementClaimDate;
	
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private LocalDate lastDayEurRateDate;
	private int fineValue;
	private double nbpEuroRate;
	private double recompense;
	private double recompenseRounded;
	
	
	/* Constructor takes four parameters from the user (from the form)
	 * Based on that it:
	 * calculate fineValue - depending on valueGross there are tree stakes of fineValue
	 * calculate payemenyClaimDay - date of payment plus one day
	 * get the euro exchange rate from NBP api for the last day of the previous moths from paymentClaimDate 
	 * calculate the recompense and recompense rounded to the two decimal places
	 */
	public RecompenseRate(String invoice, LocalDate invoiceDate, LocalDate dateOfPayement, double valueGross) {
		this.invoice = invoice;
		this.invoiceDate = invoiceDate;
		this.dateOfPayement = dateOfPayement;
		this.valueGross = valueGross;
		this.fineValue = countFineValue(valueGross);
		this.payementClaimDate = dateOfPayement.plusDays(1);
		generateNBPApiValues();
		this.recompense = fineValue * nbpEuroRate;
		this.recompenseRounded = Math.round(recompense * 100.0) / 100.0;
		this.recompense = Math.round(recompense * 10000.0) / 10000.0;
	}
	
	private void generateNBPApiValues() {
		LocalDate lastDayOfTheMonthByNBPDate = generateDateOfLastDayOfTheMonth(payementClaimDate);
		for (int i = 0; i < 5; i++) {
			try {
				nbpApiConnector(lastDayOfTheMonthByNBPDate);			
			} catch (HttpClientErrorException | JsonProcessingException ex) {
				ex.printStackTrace();
			}
			if (lastDayEurRateDate != null) {
				break;
			} else {
				lastDayOfTheMonthByNBPDate = lastDayOfTheMonthByNBPDate.minusDays(1);
			}
		}
	}
	
	private LocalDate generateDateOfLastDayOfTheMonth(LocalDate payementClaimDate) {
		LocalDate localDate = payementClaimDate.minusMonths(1);
		int lengthOfTheMonth = localDate.lengthOfMonth();
		String lastDayOfTheMonth = localDate.toString().substring(0, 8) + lengthOfTheMonth;
		
		return LocalDate.parse(lastDayOfTheMonth);
	}
	
	private void nbpApiConnector(LocalDate requestDate) throws HttpClientErrorException, JsonMappingException, JsonProcessingException {
		RestTemplate restTemplate = new RestTemplate();
		
		String url = "http://api.nbp.pl/api/exchangerates/rates/a/eur/" + requestDate.toString();
		ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
		
		System.err.println("Http request status NOK");
		ObjectMapper mapper = new ObjectMapper();
		JsonNode item = mapper.readTree(response.getBody()).path("rates").get(0);
		
		lastDayEurRateDate = LocalDate.parse(item.path("effectiveDate").asText());
		nbpEuroRate = item.path("mid").asDouble();		
	}
	
	
	private int countFineValue(double valueGross) {
		int fineValue;
		
		if (valueGross <= 5000) {
			fineValue = 40;
		} else if (valueGross < 50000) {
			fineValue = 70;
		} else {
			fineValue = 100;
		}
		
		return fineValue;
	}	
}
