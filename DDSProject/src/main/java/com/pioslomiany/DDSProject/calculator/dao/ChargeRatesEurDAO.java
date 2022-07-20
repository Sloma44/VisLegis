package com.pioslomiany.DDSProject.calculator.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.pioslomiany.DDSProject.calculator.entity.ChargeRatesEur;

@Repository
public class ChargeRatesEurDAO {

	private List<ChargeRatesEur> chargeRatesEurList;
	
	public ChargeRatesEurDAO() {
		chargeRatesEurList = new ArrayList<>();
	}
	
	public List<ChargeRatesEur> getListOfChargeRatesEur() {
		return chargeRatesEurList;
	}
	
	public void saveChargeRatesEur(ChargeRatesEur theChargeRatesEur) {
		chargeRatesEurList.add(theChargeRatesEur);
	}
	
	public void resetChargeRateList() {
		chargeRatesEurList.clear();
	}
	
	public void deleteRecordByHashCode(int theHashCode) {
		for (int i = 0; i < chargeRatesEurList.size(); i++) {
			if (chargeRatesEurList.get(i).hashCode() == theHashCode) {
				chargeRatesEurList.remove(i);
			}
//			if (chargeRatesEurList.get(i).toString().equals(theHashCode)) {
//				chargeRatesEurList.remove(i);
//			}
		}
	}
	
}
