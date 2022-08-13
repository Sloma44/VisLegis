package com.pioslomiany.VisLegis.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.pioslomiany.VisLegis.security.entity.Role;
import com.pioslomiany.VisLegis.security.entity.User;
import com.pioslomiany.VisLegis.security.entity.UserRoleView;
import com.pioslomiany.VisLegis.security.service.SecurityService;

@Controller
@RequestMapping("/vislegis/security")
public class SecurityController {
	
	@Autowired
	SecurityService securityService;
	
	@GetMapping("")
	public String getMainSecurity() {
		return "security/main-security";
	}
	
	@GetMapping("/users")
	public String getEmployees(Model model) {
		
		List<UserRoleView> usersAndRoles = securityService.getUsersAndRoles();
		
		model.addAttribute("usersAndRoles", usersAndRoles);
		
		return "security/security-users";
	}
	
	@GetMapping("users/changeStatus")
	public String changeStatus(@RequestParam("userName") String userName, Model model) {
		
		securityService.changeUserStatusByName(userName);
		
		return "redirect:/vislegis/security/users";
	}
	
	@GetMapping("saveUserForm")
	public String saveUserForm(Model model) {
		
		model.addAttribute("user", new User());
		model.addAttribute("RoleOfUser", new Role());
		
		return "security/security-new-user-form";
	}
	
	@PostMapping("saveUser")
	public String saveUser(@ModelAttribute("user") User theUser,
							@ModelAttribute("RoleOfUser") Role theRole, 
							Model model) {
		
		securityService.saveUserAndRole(theUser, theRole);
		
		return "redirect:/vislegis/security/users";
	}
	
	@GetMapping("users/deleteUser")
	public String deleteUser(@RequestParam("userName") String userName, Model model) {
		securityService.deleteUser(userName);
		
		return "redirect:/vislegis/security/users";
	}

}
