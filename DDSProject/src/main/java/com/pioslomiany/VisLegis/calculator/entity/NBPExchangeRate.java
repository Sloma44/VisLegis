package com.pioslomiany.VisLegis.calculator.entity;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public class NBPExchangeRate {

	private String code;
	private List<Rate> rates;
	
	@Getter @Setter @NoArgsConstructor
	public static class Rate {
		@JsonFormat(pattern="yyyy-MM-dd")
		private LocalDate effectiveDate;
		private String mid;
	}
	
}
