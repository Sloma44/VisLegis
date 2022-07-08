package com.pioslomiany.DDSProject.service.views;

import java.util.List;

import com.pioslomiany.DDSProject.entity.views.CustomerCaseCourtHearingView;
import com.pioslomiany.DDSProject.entity.views.CustomerCaseIncomeView;
import com.pioslomiany.DDSProject.entity.views.CustomerCaseJournalView;

public interface CustomerServiceViews {
	
	public List<CustomerCaseJournalView> getAll();
	
	public List<CustomerCaseCourtHearingView> getAllCourtHearings();
	
	public List<CustomerCaseIncomeView> getAllIncomes();

}
