package com.pioslomiany.DDSProject.controller;

import java.io.ByteArrayOutputStream;

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

import com.pioslomiany.DDSProject.doc.entity.ProsecutorAccessionForm;
import com.pioslomiany.DDSProject.doc.service.DocGeneratorService;

@Controller
@RequestMapping("/dds/docGenerator")
public class DocGenerator {
	
	@Autowired
	DocGeneratorService docGeneratorService;

	@GetMapping("")
	public String docxGeneratorMain() {
		return "docGenerator/main-docGenerator";
	}
	
	@GetMapping("createProsecutorAccessionDocxForm")
	public String createProsecutorAccessionDocxForm(Model model) {
		
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
}
