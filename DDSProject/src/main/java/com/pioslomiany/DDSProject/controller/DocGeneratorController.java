package com.pioslomiany.DDSProject.controller;

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

import com.pioslomiany.DDSProject.doc.entity.ClauseRequestForm;
import com.pioslomiany.DDSProject.doc.entity.Court;
import com.pioslomiany.DDSProject.doc.entity.ProsecutorAccessionForm;
import com.pioslomiany.DDSProject.doc.service.DocGeneratorService;

@Controller
@RequestMapping("/dds/docGenerator")
public class DocGeneratorController {
	
	@Autowired
	DocGeneratorService docGeneratorService;

	@GetMapping("")
	public String docxGeneratorMain() {
		return "docGenerator/main-docGenerator";
	}
	
	@GetMapping("createProsecutorAccessionDocxForm")
	public String createProsecutorAccessionDocxForm(Model model) {
		
		List<Court> courts = docGeneratorService.getAllCourts();
		
		model.addAttribute("courts", courts);
		model.addAttribute("prosecutorAccession", new ProsecutorAccessionForm());
		
		return "docGenerator/prosecutorAccession-docx-form";
	}
	
	@PostMapping("createProsecutorAccessionDocx")
	public void createProsecutorAccessionDocx(@Valid @ModelAttribute("prosecutorAccession") ProsecutorAccessionForm prosecutorAccessionForm,
												BindingResult bindingResult ,HttpServletResponse response) throws Throwable {
		
		if(!bindingResult.hasErrors()) {
			ByteArrayOutputStream outputStream = docGeneratorService.generateProsecutorAccesionFile(prosecutorAccessionForm);
			
			String fileName = prosecutorAccessionForm.getLastName() + "Prokuratura-wstapienie.docx";
			
			response.setContentType("application/docx");
			response.addHeader("Content-Disposition", "attachment; filename=" + fileName);
			
			outputStream.writeTo(response.getOutputStream());
			response.getOutputStream().flush();			
		} else {
			response.getWriter().write("Ops... something went wrong. Could not generate file. Check if all fields are filled.");
			response.getWriter().flush();
		}
	}
	
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
		
		if(!bindingResult.hasErrors()) {
			ByteArrayOutputStream outputStream = docGeneratorService.generateClauseRequestFormFile(clauseRequestForm);
			
			String fileName = clauseRequestForm.getLastName() + "WniosekKlauzula.docx";
			
			response.setContentType("application/docx");
			response.addHeader("Content-Disposition", "attachment; filename=" + fileName);
			
			outputStream.writeTo(response.getOutputStream());
			response.getOutputStream().flush();			
		} else {
			response.getWriter().write("Ops... something went wrong. Could not generate file. Check if all fields are filled.");
			response.getWriter().flush();
		}
	}
	
	
	
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
		
		return "redirect:/dds/docGenerator/courtsList";
	}
	
	@GetMapping("courtsList/deleteCourt")
	public String deleteCourt(@RequestParam("courtId") int theId, Model model) {
		
		docGeneratorService.deleteCourtById(theId);
		
		return "redirect:/dds/docGenerator/courtsList";
	}
}
