package com.pioslomiany.VisLegis.controller.test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import com.pioslomiany.VisLegis.SecurityConfiguration;
import com.pioslomiany.VisLegis.controller.MainPageController;
import com.pioslomiany.VisLegis.security.service.SecurityService;

@WebMvcTest(controllers = {MainPageController.class})
@ComponentScan(basePackageClasses = SecurityConfiguration.class)
public class MainPageContollerSecurityTest {
	
	@Autowired
    private MockMvc mvc;
	
	@Autowired
	private MainPageController mainController;
	
	@Autowired
	SecurityConfiguration security;

	@MockBean
	SecurityService securityService;
	
	@MockBean
	EntityManager entityManager;
	
	@MockBean
	DataSource dataSource;
	
	@Test
	public void contextLoads() throws Exception {
		assertThat(mainController).isNotNull();
	}
	
	@Test
	@WithMockUser(username = "testUser", roles = {"ADMIN"})
	public void adminLoggingTest() throws Exception {
		
		mvc.perform(get("http://localhost:8080/vislegis/security").contentType(MediaType.ALL_VALUE))
		.andExpect(status().isOk());		
	}
	
	@Test
	@WithMockUser(username = "testUser", roles = {"employee"})
	public void employeeLoggingAccessDeniedTest() throws Exception {
		
		mvc.perform(get("http://localhost:8080/vislegis/security").contentType(MediaType.ALL_VALUE))
		.andExpect(status().isForbidden());		
	}
}
