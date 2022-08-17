package com.pioslomiany.VisLegis;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.thymeleaf.extras.springsecurity5.dialect.SpringSecurityDialect;

@Configuration
public class SecurityConfiguration {
	
	
	// Needed for thymeleaf springsecurity5 to use sec:authorize (hide fields according to user role)
	@Bean
	public SpringSecurityDialect securityDialect() {
	    return new SpringSecurityDialect();
	}
	
	
	// Passwords are saved in DB as BCrypt(10)
	@Bean
	public PasswordEncoder encoder() {
	    return new BCryptPasswordEncoder();
	}

	
	// Users and Roles defined in DB
    @Bean
    public UserDetailsManager users(DataSource dataSource) {
        JdbcUserDetailsManager users = new JdbcUserDetailsManager(dataSource);
        return users;
    }
    
    
    // Resources ignored by authorization (login page styles and login page logo)
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
    	return (web) -> web.ignoring().antMatchers("/styles/login.css", "/images/VL_logo_white.png");
    }
   	
    
    // Definition of access
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		
		http
			.authorizeRequests((authz) -> authz
								.antMatchers("/vislegis/customerList/updateCustomerForm").hasAnyRole("ADMIN", "MANAGER")
								.antMatchers("/vislegis/customerList/customerDetails/deleteCustomer").hasAnyRole("ADMIN", "MANAGER")
								.antMatchers("/vislegis/customerList/customerDetails/updateLawCaseForm").hasAnyRole("ADMIN", "MANAGER")
								.antMatchers("/vislegis/customerList/customerDetails/caseDetails/deleteLawCase").hasAnyRole("ADMIN", "MANAGER")
								.antMatchers("/vislegis/customerList/customerDetails/caseDetails/updateIncomeForm").hasAnyRole("ADMIN", "MANAGER")
								.antMatchers("/vislegis/customerList/customerDetails/caseDetails/deleteIncome").hasAnyRole("ADMIN", "MANAGER")
								.antMatchers("/vislegis/customerList/customerDetails/caseDetails/updateCustomerCaseCostForm").hasAnyRole("ADMIN", "MANAGER")
								.antMatchers("/vislegis/customerList/customerDetails/caseDetails/deleteCustomerCaseCost").hasAnyRole("ADMIN", "MANAGER")
								.antMatchers("/vislegis/customerList/**").hasAnyRole("ADMIN", "MANAGER", "EMPLOYEE")
								.antMatchers("/vislegis/summary/letters/deleteLetter").hasAnyRole("ADMIN", "MANAGER")
								.antMatchers("/vislegis/summary/letters**").hasAnyRole("ADMIN", "MANAGER", "EMPLOYEE")
								.antMatchers("/vislegis/summary/incomes/**").hasAnyRole("ADMIN", "MANAGER")
								.antMatchers("/vislegis/summary/courtHearings/deleteHearing").hasAnyRole("ADMIN", "MANAGER")
								.antMatchers("/vislegis/summary/courtHearings**").hasAnyRole("ADMIN", "MANAGER", "EMPLOYEE")
								.antMatchers("/vislegis/summary/**").hasAnyRole("ADMIN", "MANAGER", "EMPLOYEE")
								.antMatchers("/vislegis/calculator/values/updateValueForm").hasRole("ADMIN")
								.antMatchers("/vislegis/calculator/**").hasAnyRole("ADMIN", "MANAGER", "EMPLOYEE")
								.antMatchers("/vislegis/docGenerator/courtsList/updateCourtForm").hasAnyRole("ADMIN", "MANAGER")
								.antMatchers("/vislegis/docGenerator/courtsList/deleteCourt").hasAnyRole("ADMIN", "MANAGER")
								.antMatchers("/vislegis/docGenerator/**").hasAnyRole("ADMIN", "MANAGER", "EMPLOYEE")
								.antMatchers("/vislegis/toDoList/**").hasAnyRole("ADMIN", "MANAGER")
								.antMatchers("/vislegis/security/**").hasRole("ADMIN")
								.antMatchers("/vislegis").hasAnyRole("ADMIN", "MANAGER", "EMPLOYEE")
			)
			.formLogin((formLogin) -> formLogin
											.loginPage("/vislegis/login")
											.loginProcessingUrl("/vislegis/login-authenticaton")
											.defaultSuccessUrl("/vislegis")
											.failureUrl("/vislegis/login-error")
											.permitAll()
			)
			.logout((logout) -> logout.deleteCookies("remove")
									.logoutUrl("/vislegis/logout")
									.logoutSuccessUrl("/vislegis/logout")
									.permitAll()
			)
			.exceptionHandling((access) -> access
				.accessDeniedPage("/vislegis/accessDenied")
			)
			.httpBasic();
		
		return http.build();
	}
}
