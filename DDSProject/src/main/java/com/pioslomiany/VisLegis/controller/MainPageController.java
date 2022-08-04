package com.pioslomiany.VisLegis.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/vislegis")
public class MainPageController {

	@GetMapping("")
	public String mainPage() {
		return "main-page";
	}
	
}
