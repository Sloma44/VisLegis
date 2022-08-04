package com.pioslomiany.VisLegis.controller;

import java.io.ByteArrayOutputStream;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.pioslomiany.VisLegis.doc.entity.ClauseRequestForm;
import com.pioslomiany.VisLegis.doc.entity.Court;
import com.pioslomiany.VisLegis.doc.entity.DocxForm;
import com.pioslomiany.VisLegis.doc.entity.JustificationRequestForm;
import com.pioslomiany.VisLegis.doc.service.DocGeneratorService;

@Controller
@RequestMapping("/vislegis/docGenerator")
public class DocGeneratorController {
	
	@Autowired
	DocGeneratorService docGeneratorService;
	
	private final String ERROR_MESSAGE = "Oops... something went wrong. Could not generate file. Check if all fields are filled.";

	
//	Main page for document generator
	@GetMapping("")
	public String docxGeneratorMain() {
		return "docGenerator/main-docGenerator";
	}
	

//	Generates document for "Prokuratura wstąpienie"

	@GetMapping("createProsecutorAccessionDocxForm")
	public String createProsecutorAccessionDocxForm(Model model) {
		
		List<Court> courts = docGeneratorService.getAllCourts();
		
		model.addAttribute("courts", courts);
		model.addAttribute("prosecutorAccession", new DocxForm());
		
		return "docGenerator/prosecutorAccession-docx-form";
	}
	
	@PostMapping("createProsecutorAccessionDocx")
	public void createProsecutorAccessionDocx(@Valid @ModelAttribute("prosecutorAccession") DocxForm prosecutorAccessionForm,
												BindingResult bindingResult ,HttpServletResponse response) throws Throwable {
		
		// if one of the fields is blnk it will not generate the document
		// printing message for user as HttpResponse
		if(!bindingResult.hasErrors()) {
			// Generate template with replaced strings as outputStream
			ByteArrayOutputStream outputStream = docGeneratorService.generateProsecutorAccesionFile(prosecutorAccessionForm);
			
			// output file name (Customer lastName + string)
			String fileName = prosecutorAccessionForm.getLastName() + "Prokuratura-wstapienie.docx";
			
			response.setContentType("application/docx");
			response.addHeader("Content-Disposition", "attachment; filename=" + fileName);
			
			outputStream.writeTo(response.getOutputStream());
			response.getOutputStream().flush();			
		} else {
			response.getWriter().write(ERROR_MESSAGE);
			response.getWriter().flush();
		}
	}
	
	
//	Generates document for "Wniosek o uzasadnienie"
	
	@GetMapping("createJustificationRequestDocxForm")
	public String createJustificationRequestDocxForm(Model model) {
		
		List<Court> courts = docGeneratorService.getAllCourts();
		
		model.addAttribute("courts", courts);
		model.addAttribute("justificationRequest", new JustificationRequestForm());
		
		return "docGenerator/justificationRequest-docx-form";
	}
	
	@PostMapping("createJustificationRequestDocx")
	public void createJustificationRequestDocx(@Valid @ModelAttribute("justificationRequest") JustificationRequestForm justificationRequestForm,
												BindingResult bindingResult ,HttpServletResponse response) throws Throwable {
		
		// if one of the fields is blnk it will not generate the document
		// printing message for user as HttpResponse
		if(!bindingResult.hasErrors()) {
			// Generate template with replaced strings as outputStream
			ByteArrayOutputStream outputStream = docGeneratorService.generateJustificationRequestFormFile(justificationRequestForm);
			
			// output file name (Customer lastName + string)
			String fileName = justificationRequestForm.getLastName() + "WniosekUzasadnienie.docx";
			
			response.setContentType("application/docx");
			response.addHeader("Content-Disposition", "attachment; filename=" + fileName);
			
			outputStream.writeTo(response.getOutputStream());
			response.getOutputStream().flush();			
		} else {
			response.getWriter().write(ERROR_MESSAGE);
			response.getWriter().flush();
		}
	}
	
	
//	Generates document for "Wniosek o klauzulę"
	
	@GetMapping("createClauseRequestDocxForm")
	public String createClauseRequestDocxForm(Model model) {
		
		List<Court> courts = docGeneratorService.getAllCourts();
		
		model.addAttribute("courts", courts);
		model.addAttribute("clauseRequest", new ClauseRequestForm());
		
		return "docGenerator/clauseRequest-docx-form";
	}
	
	@PostMapping("createClauseRequestDocx")
	public void createClauseRequestDocx(@Valid @ModelAttribute("clauseRequest") ClauseRequestForm clauseRequestForm,
												BindingResult bindingResult ,HttpServletResponse response) throws Throwable {
		
		// if one of the fields is blnk it will not generate the document
		// printing message for user as HttpResponse
		if(!bindingResult.hasErrors()) {
			// Generate template with replaced strings as outputStream
			ByteArrayOutputStream outputStream = docGeneratorService.generateClauseRequestFormFile(clauseRequestForm);
			
			// output file name (Customer lastName + string)
			String fileName = clauseRequestForm.getLastName() + "WniosekKlauzula.docx";
			
			response.setContentType("application/docx");
			response.addHeader("Content-Disposition", "attachment; filename=" + fileName);
			
			outputStream.writeTo(response.getOutputStream());
			response.getOutputStream().flush();			
		} else {
			response.getWriter().write(ERROR_MESSAGE);
			response.getWriter().flush();
		}
	}
	
//	Generates document for "Wstąpienie do sprawy"
//	The same form is used as in "prosecutorAccession"
	@GetMapping("createJoiningTheCaseDocxForm")
	public String createJoiningTheCaseForm(Model model) {
		
		List<Court> courts = docGeneratorService.getAllCourts();
		
		model.addAttribute("courts", courts);
		model.addAttribute("joinTheCase", new DocxForm());
		
		return "docGenerator/joinTheCase-docx-form";
	}
	
	@PostMapping("createJoiningTheCaseDocx")
	public void createJoiningTheCaseDocx(@Valid @ModelAttribute("joinTheCase") DocxForm joiningTheCaseForm,
												BindingResult bindingResult ,HttpServletResponse response) throws Throwable {
		
		// if one of the fields is blnk it will not generate the document
		// printing message for user as HttpResponse
		if(!bindingResult.hasErrors()) {
			
			// Generate template with replaced strings as outputStream
			ByteArrayOutputStream outputStream = docGeneratorService.generateJoiningTheCaseFile(joiningTheCaseForm);
			
			// output file name (Customer lastName + string)
			String fileName = joiningTheCaseForm.getLastName() + "WstapienieDoSprawy.docx";
			
			response.setContentType("application/docx");
			response.addHeader("Content-Disposition", "attachment; filename=" + fileName);
			
			outputStream.writeTo(response.getOutputStream());
			response.getOutputStream().flush();			
		} else {
			response.getWriter().write(ERROR_MESSAGE);
			response.getWriter().flush();
		}
	}
	
	
//	Typical CRUD for Court entity
	
	@GetMapping("courtsList")
	public String courtsList(Model model) {
		
		List<Court> courts = docGeneratorService.getAllCourts();
		
		model.addAttribute("courts", courts);
		
		return "docGenerator/courts-list";
	}
	
	@GetMapping("courtsList/saveCourtForm")
	public String saveCourtForm (Model model) {
		
		model.addAttribute("court", new Court());
		
		return "docGenerator/save-court-form";
	}
	
	@GetMapping("courtsList/updateCourtForm")
	public String updateCourtForm(@RequestParam("courtId") int theId, Model model) {
		Court theCourt = docGeneratorService.getCourtById(theId);
		
		model.addAttribute("court", theCourt);
		
		return "docGenerator/save-court-form";
	}
	
	@PostMapping("courtsList/saveCourt")
	public String saveCourt(@Valid @ModelAttribute Court theCourt, BindingResult bindingResult, Model model) {
		
		if (bindingResult.hasErrors()) {
			return "docGenerator/save-court-form";
		}
		
		docGeneratorService.saveCourt(theCourt);
		
		return "redirect:/vislegis/docGenerator/courtsList";
	}
	
	@GetMapping("courtsList/deleteCourt")
	public String deleteCourt(@RequestParam("courtId") int theId, Model model) {
		
		docGeneratorService.deleteCourtById(theId);
		
		return "redirect:/vislegis/docGenerator/courtsList";
	}
}
