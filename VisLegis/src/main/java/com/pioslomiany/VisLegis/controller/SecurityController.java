package com.pioslomiany.VisLegis.controller;

import java.util.List;

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

import com.pioslomiany.VisLegis.security.entity.Role;
import com.pioslomiany.VisLegis.security.entity.User;
import com.pioslomiany.VisLegis.security.entity.UserRoleView;
import com.pioslomiany.VisLegis.security.service.SecurityService;

	/*
	 * CRUD operations on users, with access only for ADMIN.
	 * User can be added, deleted or status change to active / inactive. 
	 * User cannot be modified. 
	 * Available users: employee / manager.
	 * Cannot create new ADMIN, cannot change ADMIN status and ADMIN cannot be deleted.
	 */

@Controller
@RequestMapping("/vislegis/security")
public class SecurityController {
	
	@Autowired
	SecurityService securityService;
	
	@GetMapping("")
	public String getMainSecurity() {
		return "security/main-security";
	}
	
	
	/* 
	 * List off all users with their status and role
	 * Using DB View to compile role with userName and status
	 */
	@GetMapping("/users")
	public String getEmployees(Model model) {
		
		List<UserRoleView> usersAndRoles = securityService.getUsersAndRoles();
		
		model.addAttribute("usersAndRoles", usersAndRoles);
		
		return "security/security-users";
	}
	
	/*
	 * User status can be changed to active / inactive.
	 * Cannot change status of the ADMIN.
	 */
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
	public String saveUser(@Valid @ModelAttribute("user") User theUser, BindingResult bindingResult,
							@ModelAttribute("RoleOfUser") Role theRole, 
							Model model) {
		
		if (bindingResult.hasErrors()) {
			return "security/security-new-user-form";
		}
		securityService.saveUserAndRole(theUser, theRole);
		
		return "redirect:/vislegis/security/users";
	}
	
	@GetMapping("users/deleteUser")
	public String deleteUser(@RequestParam("userName") String userName, Model model) {
		securityService.deleteUser(userName);
		
		return "redirect:/vislegis/security/users";
	}

}
