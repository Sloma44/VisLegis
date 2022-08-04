package com.pioslomiany.VisLegis.views.service;

import java.util.List;

import com.pioslomiany.VisLegis.views.entity.CustomerCaseCourtHearingView;
import com.pioslomiany.VisLegis.views.entity.CustomerCaseIncomeView;
import com.pioslomiany.VisLegis.views.entity.CustomerCaseJournalView;

public interface CustomerServiceViews {
	
	public List<CustomerCaseJournalView> getAllJournals();
	
	public List<CustomerCaseCourtHearingView> getAllCourtHearings();
	
	public List<CustomerCaseIncomeView> getAllIncomes();
}
