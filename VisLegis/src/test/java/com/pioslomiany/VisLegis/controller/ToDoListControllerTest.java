package com.pioslomiany.VisLegis.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.ModelAndViewAssert;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.servlet.ModelAndView;

import com.pioslomiany.VisLegis.VisLegisProjectApplication;
import com.pioslomiany.VisLegis.toDoList.dao.ToDoItemDAOImpl;
import com.pioslomiany.VisLegis.toDoList.entity.ToDoItem;

@SpringBootTest(classes=VisLegisProjectApplication.class)
@TestPropertySource("/application-test.properties")
@AutoConfigureMockMvc
@WithMockUser(username = "testUser", roles = {"ADMIN"})
public class ToDoListControllerTest {

	@Autowired
	JdbcTemplate jdbc;
	
	@Autowired
	MockMvc mockMvc;
	
	@Autowired
	ToDoItemDAOImpl todoItemDao;
	
	private final String URL = "/vislegis/toDoList";
	
	@BeforeEach
	void databaseSetup() {
		jdbc.execute("INSERT INTO todo_list (id, category, dead_line_date, description, status) "
				+ "VALUES (1, 'green', '2023-12-10', 'Shopping', 'W trakcie')");
	}
	
	@AfterEach
	void databaseCleanup() {
		jdbc.execute("DELETE FROM todo_list");
	}
	
	@Test
	void getToDoListItemsHttpRequestTest() throws Exception {
		MvcResult mvcResult = mockMvc.perform(get(URL))
				.andExpect(status().isOk())
				.andReturn();
		
		ModelAndView modelAndView = mvcResult.getModelAndView();
		
		ModelAndViewAssert.assertViewName(modelAndView, "toDoList/toDoList-view");
	}
	
	@Test
	void newTaskFormHttpRequestTest() throws Exception {
		MvcResult mvcResult = mockMvc.perform(get(URL + "/newTaskForm"))
				.andExpect(status().isOk())
				.andReturn();
		
		ModelAndView modelAndView = mvcResult.getModelAndView();
		
		ModelAndViewAssert.assertViewName(modelAndView, "toDoList/save-todo-item-form");
	}
	
	@Test
	void updateTaskFormHttpRequestTest() throws Exception {
		MvcResult mvcResult = mockMvc.perform(get(URL + "/updateTaskForm")
					.param("itemId", "1"))
				.andExpect(status().isOk())
				.andReturn();
		
		ModelAndView modelAndView = mvcResult.getModelAndView();
		
		ModelAndViewAssert.assertViewName(modelAndView, "toDoList/save-todo-item-form");
	}
	
	@Test
	void saveNewItemHttpRequestTest() throws Exception {
		// because of csrf cannot execute sql statement from @BeforeEach with imposed id
		databaseCleanup();
		
		MvcResult mvcResult = mockMvc.perform(post(URL + "/saveNewTask")
					.with(csrf())
					.param("category", "blue")
					.param("deadLineDate", "2099-10-10")
					.param("description", "Working")
					)
				.andExpect(status().isFound())
				.andReturn();
		
		ModelAndView modelAndView = mvcResult.getModelAndView();
		
		ModelAndViewAssert.assertViewName(modelAndView, "redirect:/vislegis/toDoList");
		
		List<ToDoItem> todoItemList = todoItemDao.getAllItems();
		assertEquals(1, todoItemList.size());
	}
	
	@Test
	void saveNewItemHttpRequestWithoutDescriptionTest() throws Exception {
		// because of csrf cannot execute sql statement from @BeforeEach with imposed id
		databaseCleanup();
		
		MvcResult mvcResult = mockMvc.perform(post(URL + "/saveNewTask")
				.with(csrf())
				.param("category", "blue")
				.param("deadLineDate", "2099-10-10")
				.param("description", "")
				)
			.andExpect(status().isOk())
			.andReturn();
	
		ModelAndView modelAndView = mvcResult.getModelAndView();
		
		ModelAndViewAssert.assertViewName(modelAndView, "toDoList/save-todo-item-form");
		
		List<ToDoItem> todoItemList = todoItemDao.getAllItems();
		assertEquals(0, todoItemList.size(), "Item should not be added to the list");
	}
	
	@Test
	void markItemHttpRequestTest() throws Exception {
		
		ToDoItem todoItemVerification = todoItemDao.getItemById(1);
		assertEquals("W trakcie", todoItemVerification.getIsDone());
		
		MvcResult mvcResult = mockMvc.perform(get(URL + "/markItem")
					.param("itemId", "1")
					)
				.andExpect(status().isFound())
				.andReturn();
		
		ModelAndView modelAndView = mvcResult.getModelAndView();
		
		ModelAndViewAssert.assertViewName(modelAndView, "redirect:/vislegis/toDoList");
		
		todoItemVerification = todoItemDao.getItemById(1);
		assertEquals("Zakończono", todoItemVerification.getIsDone());
	}
	
	@Test
	void deleteItemHttpRequestTest() throws Exception {
		List<ToDoItem> todoItems = todoItemDao.getAllItems();
		assertEquals(1, todoItems.size());
		
		MvcResult mvcResult = mockMvc.perform(get(URL + "/deleteItem")
					.param("itemId", "1")
					)
				.andExpect(status().isFound())
				.andReturn();
		
		ModelAndView modelAndView = mvcResult.getModelAndView();
		ModelAndViewAssert.assertViewName(modelAndView, "redirect:/vislegis/toDoList");
		
		todoItems = todoItemDao.getAllItems();
		assertEquals(0, todoItems.size());
	}
	
	
	@Test
	@Sql("/todoListInsertData.sql")
	void deleteDoneItemsHttpRequestTest() throws Exception {

		List<ToDoItem> todoItems = todoItemDao.getAllItems();
		assertEquals(4, todoItems.size());
		
		MvcResult mvcResult = mockMvc.perform(get(URL + "/deleteDoneItems"))
				.andExpect(status().isFound())
				.andReturn();
		
		ModelAndView modelAndView = mvcResult.getModelAndView();
		ModelAndViewAssert.assertViewName(modelAndView, "redirect:/vislegis/toDoList");
		
		todoItems = todoItemDao.getAllItems();
		assertEquals(2, todoItems.size());
	}
	
}
