package com.pioslomiany.DDSProject.calculator.entity;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public class NBPExchangeRate {

	private String code;
	private List<Rate> rates;
	
	@Getter @Setter @NoArgsConstructor
	public static class Rate {
		private String effectiveDate;
		private String mid;
	}
	
}
