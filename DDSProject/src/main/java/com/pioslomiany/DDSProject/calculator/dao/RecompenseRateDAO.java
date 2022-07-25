package com.pioslomiany.DDSProject.calculator.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.pioslomiany.DDSProject.calculator.entity.RecompenseRate;

@Repository
public class RecompenseRateDAO {

	private List<RecompenseRate> recompenseRatesList;
	
	public RecompenseRateDAO() {
		recompenseRatesList = new ArrayList<>();
	}
	
	public List<RecompenseRate> getRecompenseRatesList() {
		return recompenseRatesList;
	}
	
	public void saveRecompenseRate(RecompenseRate theRecompenseRate) {
		recompenseRatesList.add(theRecompenseRate);
	}
	
	public void resetRecompenseRatesList() {
		recompenseRatesList.clear();
	}
	
	public void deleteRecompenseRateByHashCode(int theHashCode) {
		for (int i = 0; i < recompenseRatesList.size(); i++) {
			if (recompenseRatesList.get(i).hashCode() == theHashCode) {
				recompenseRatesList.remove(i);
			}
		}
	}
	
}
