package com.pioslomiany.DDSProject.toDoList.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pioslomiany.DDSProject.toDoList.dao.ToDoItemDAOImpl;
import com.pioslomiany.DDSProject.toDoList.entity.ToDoItem;

@Service
public class ToDoItemService {

	@Autowired
	ToDoItemDAOImpl toDoItemDAOImpl;
	
	@Transactional
	public List<ToDoItem> getAllItems() {
		return toDoItemDAOImpl.getAllItems();
	}

	@Transactional
	public ToDoItem getItemById(int itemId) {
		return toDoItemDAOImpl.getItemById(itemId);
	}
	
	@Transactional
	public void saveItem(ToDoItem toDoItem) {
		toDoItemDAOImpl.saveItem(toDoItem);
	}

	@Transactional
	public void deleteItemById(int itemId) {
		toDoItemDAOImpl.deleteItemById(itemId);
	}
	
	@Transactional
	public void setItemAsDone(int itemId) {
		toDoItemDAOImpl.setItemAsDone(itemId);
	}

	@Transactional
	public void deleteDoneItems() {
		toDoItemDAOImpl.deleteDoneItems();
	}
}
