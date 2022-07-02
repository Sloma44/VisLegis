package com.pioslomiany.DDSProject.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pioslomiany.DDSProject.dao.CusomerDAOImpl;
import com.pioslomiany.DDSProject.entity.Customer;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CusomerDAOImpl customerDAO;
	
	@Override
	@Transactional
	public List<Customer> getAll() {
		return customerDAO.getAll();
	}

	
	
}
