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

import com.pioslomiany.VisLegis.toDoList.entity.ToDoItem;
import com.pioslomiany.VisLegis.toDoList.service.ToDoItemService;

@Controller
@RequestMapping("vislegis/toDoList")
public class ToDoListController {
	
	/*
	 *Simple ToDoList with the possibility to read, add, update and delete.
	 *User has also possibility to change the status of an item as done NOT done ("W trakcie" / "Zakończono")
	 *Special button added to delete all items marked as done.
	 *Controller is using DB "todo_list" to store all items
	 */
	
	
	@Autowired
	ToDoItemService toDoItemService;
	
	@GetMapping("")
	public String toDoList(Model model) {
		
		List<ToDoItem> ToDoList = toDoItemService.getAllItems();
		
		model.addAttribute("toDoList", ToDoList);
		
		return "toDoList/toDoList-view";
	}
	
	@GetMapping("newTaskForm")
	public String newTaskForm(Model model) {
		
		model.addAttribute("item", new ToDoItem());
		
		return "toDoList/save-todo-item-form";
	}
	
	@GetMapping("updateTaskForm")
	public String updateTaskForm(@RequestParam("itemId") int itemId, Model model) {
		
		ToDoItem toDoItem = toDoItemService.getItemById(itemId);
		
		model.addAttribute("item", toDoItem);
		
		return "toDoList/save-todo-item-form";
	}
	
	@PostMapping("saveNewTask")
	public String saveNewTask(@Valid @ModelAttribute("item") ToDoItem toDoItem, BindingResult bindingResult,
									Model model) {
		
		if (bindingResult.hasErrors()) {
			return "toDoList/save-todo-item-form";			
		}
		toDoItemService.saveItem(toDoItem);
		
		return "redirect:/vislegis/toDoList";
	}
	
	// marking item as done / not done ("W trakcie" / "Zakończono")
	@GetMapping("markItem")
	public String markItem(@RequestParam("itemId") int itemId, Model model) {
		
		toDoItemService.setItemAsDone(itemId);
		
		return "redirect:/vislegis/toDoList";
	}
	
	@GetMapping("deleteItem")
	public String deleteItem(@RequestParam("itemId") int itemId, Model model) {

		toDoItemService.deleteItemById(itemId);
		
		return "redirect:/vislegis/toDoList";
	}
	
	// deleting all items marked as done
	@GetMapping("deleteDoneItems")
	public String deleteDoneItems() {
		toDoItemService.deleteDoneItems();
		
		return "redirect:/vislegis/toDoList";
	}
}
