package com.pioslomiany.DDSProject.doc.service;

import java.io.ByteArrayOutputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pioslomiany.DDSProject.doc.dao.ClauseRequestDAOImpl;
import com.pioslomiany.DDSProject.doc.dao.CourtDAOImpl;
import com.pioslomiany.DDSProject.doc.dao.JoiningTheCaseDAOImpl;
import com.pioslomiany.DDSProject.doc.dao.JustificationRequestDAOImpl;
import com.pioslomiany.DDSProject.doc.dao.ProsecutorAccesionDAOImpl;
import com.pioslomiany.DDSProject.doc.entity.ClauseRequestForm;
import com.pioslomiany.DDSProject.doc.entity.Court;
import com.pioslomiany.DDSProject.doc.entity.DocxForm;
import com.pioslomiany.DDSProject.doc.entity.JustificationRequestForm;

@Service
public class DocGeneratorService {
	
	@Autowired
	ProsecutorAccesionDAOImpl prosecutorAccesionDAOImpl;
	
	@Autowired
	JustificationRequestDAOImpl justificationRequestDAOImpl;
	
	@Autowired
	ClauseRequestDAOImpl clauseRequestDAOImpl;
	
	@Autowired
	JoiningTheCaseDAOImpl joiningTheCaseDAOImpl;
	
	@Autowired
	CourtDAOImpl courtDAOImpl;
	
	public ByteArrayOutputStream generateProsecutorAccesionFile(DocxForm prosecutorAccessionForm) throws Throwable {
	    return prosecutorAccesionDAOImpl.generateProsecutorAccesionFile(prosecutorAccessionForm);
	}
	
	public ByteArrayOutputStream generateJustificationRequestFormFile(JustificationRequestForm justificationRequestForm) throws Throwable {
		return justificationRequestDAOImpl.generateJustificationRequestFormFile(justificationRequestForm);
	}

	public ByteArrayOutputStream generateClauseRequestFormFile(ClauseRequestForm clauseRequestForm) throws Throwable {
		return clauseRequestDAOImpl.generateClauseRequestFormFile(clauseRequestForm);
	}
	
	public ByteArrayOutputStream generateJoiningTheCaseFile(DocxForm joiningTheCaseForm) throws Throwable {
		return joiningTheCaseDAOImpl.generateJoiningTheCaseFormFile(joiningTheCaseForm);
	}
	
	@Transactional
	public List<Court> getAllCourts() {
		return courtDAOImpl.getAllCourts();
	}
	
	@Transactional
	public Court getCourtById (int theId) {
		return courtDAOImpl.getCourtById(theId);
	}
	
	@Transactional
	public void saveCourt (Court theCourt) {
		courtDAOImpl.saveCourt(theCourt);
	}
	
	@Transactional
	public void deleteCourtById (int theId) {
		courtDAOImpl.deleteCourtById(theId);
	}


	
}
