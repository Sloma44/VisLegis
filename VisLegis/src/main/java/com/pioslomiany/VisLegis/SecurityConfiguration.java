package com.pioslomiany.VisLegis;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private DataSource securityDataSource;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication().dataSource(securityDataSource);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
//		 Resources that are available before login validation
	    String[] staticResources  =  {
	            "/styles/login.css",
	            "/images/VL_logo_white.png",
	        };
		
		http
			.authorizeRequests((authz) -> authz
								.antMatchers(staticResources).permitAll()
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
	}
	
	
	
	
	
//	@Bean
//	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//		
//		// Resources that are available before login validation
//	    String[] staticResources  =  {
//	            "/styles/login.css",
//	            "/images/VL_logo_white.png",
//	        };
//		
//		http
//			.authorizeHttpRequests((authz) -> authz
//													.antMatchers(staticResources).permitAll()
//													.anyRequest()
//													.authenticated()
//													.mvcMatchers("/vislegis/docGenerator/courtsList").access(new CustomAuthorizationManager())
//			)
//			.formLogin((formLogin) -> formLogin
//												.loginPage("/vislegis/login").permitAll()
//												.defaultSuccessUrl("/vislegis/")
//												.failureUrl("/vislegis/login-error")
//			)
//			.logout((logout) -> logout.deleteCookies("remove")
//										.logoutUrl("/vislegis/logout")
//										.logoutSuccessUrl("/vislegis/logout-success")
//			)
//			.httpBasic();
//		
//		return http.build();
//	}
//	
//	@Bean
//    public WebSecurityCustomizer webSecurityCustomizer() {
//        return (web) -> web.ignoring().antMatchers("/ignore1", "/ignore2");
//    }
//	
//	
//	// this part will be deleted and repleced with JDBC Authentication
//	@Bean
//    public InMemoryUserDetailsManager userDetailsService() {
//        UserDetails user = User.withDefaultPasswordEncoder()
//            .username("u")
//            .password("u")
//            .roles("USER")
//            .build();
//        return new InMemoryUserDetailsManager(user);
//    }
//	
//	@Bean
//    public InMemoryUserDetailsManager userDetailsService2() {
//        UserDetails user = User.withDefaultPasswordEncoder()
//            .username("a")
//            .password("a")
//            .roles("ADMIN")
//            .build();
//        return new InMemoryUserDetailsManager(user);
//    }

}
