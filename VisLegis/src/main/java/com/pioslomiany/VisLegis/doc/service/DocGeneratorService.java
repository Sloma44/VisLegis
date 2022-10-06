package com.pioslomiany.VisLegis.doc.service;

import java.io.ByteArrayOutputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pioslomiany.VisLegis.doc.dao.CourtDAOImpl;
import com.pioslomiany.VisLegis.doc.dao.DocumentGeneratorStreamDAO;
import com.pioslomiany.VisLegis.doc.entity.Court;
import com.pioslomiany.VisLegis.doc.entity.Form;
import com.pioslomiany.VisLegis.doc.entity.FormType;

@Service
public class DocGeneratorService {
	
	@Autowired
	CourtDAOImpl courtDAOImpl;
	
	public ByteArrayOutputStream generateDocumentStream(Form form, FormType formType) throws Exception {
		int courtId = form.getCourtId();
		Court court = courtDAOImpl.getCourtById(courtId);
		DocumentGeneratorStreamDAO documentGeneratorStreamDAO = new DocumentGeneratorStreamDAO();
		return documentGeneratorStreamDAO.generateDocumentStream(court, form, formType);
	}
	
	
	/* Courts */
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
