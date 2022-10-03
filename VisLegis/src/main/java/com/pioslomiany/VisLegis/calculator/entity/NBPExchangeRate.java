package com.pioslomiany.VisLegis.calculator.entity;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/*("Kurs euro NBP dla ostaniego dnia miesiąca")*/

@Getter @Setter @NoArgsConstructor
public class NBPExchangeRate {

	private String code;
	private List<Rate> rates;
	
	@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Data
	public static class Rate{
		@JsonFormat(pattern="yyyy-MM-dd")
		private LocalDate effectiveDate;
		private String mid;

	}
	
}
