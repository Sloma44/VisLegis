package com.pioslomiany.VisLegis.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/vislegis")
public class MainPageController {

	@GetMapping("")
	public String mainPage() {
		return "main-page";
	}
	
	
	@GetMapping("/login")
	public String login() {
		return "security/login-view";
	}
	
	@GetMapping("/login-authenticaton")
	public String loginAuthenticaton() {
		return "redirect:/vislegis";
	}
	
	@GetMapping("/login-error")
	  public String loginError(Model model) {
	    model.addAttribute("loginError", true);
	    return "security/login-view";
	  }
	
	@GetMapping("/logout")
	public String logout() {
		return "security/logout-view";
	}
	
	@GetMapping("/accessDenied")
	public String accessDenied() {
		return "security/access-denied-view";
	}
}
