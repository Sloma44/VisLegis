package com.pioslomiany.VisLegis;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		//adding hard coded users
		UserBuilder users = User.withDefaultPasswordEncoder();
		
			auth.inMemoryAuthentication()
				.withUser(users.username("a").password("a").roles("ADMIN", "MANAGER", "EMPLOYEE", "CUSTOMER"))
				.withUser(users.username("m").password("m").roles("MANAGER", "EMPLOYEE", "CUSTOMER"))
				.withUser(users.username("e").password("e").roles("EMPLOYEE", "CUSTOMER"))
				.withUser(users.username("c").password("c").roles("CUSTOMER"));
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
								.antMatchers("/vislegis/customerList/updateCustomerForm").hasRole("MANAGER")
								.antMatchers("/vislegis/customerList/customerDetails/deleteCustomer").hasRole("MANAGER")
								.antMatchers("/vislegis/customerList/customerDetails/updateLawCaseForm").hasRole("MANAGER")
								.antMatchers("/vislegis/customerList/customerDetails/caseDetails/deleteLawCase").hasRole("MANAGER")
								.antMatchers("/vislegis/customerList/customerDetails/caseDetails/updateIncomeForm").hasRole("MANAGER")
								.antMatchers("/vislegis/customerList/customerDetails/caseDetails/deleteIncome").hasRole("MANAGER")
								.antMatchers("/vislegis/customerList/customerDetails/caseDetails/updateCustomerCaseCostForm").hasRole("MANAGER")
								.antMatchers("/vislegis/customerList/customerDetails/caseDetails/deleteCustomerCaseCost").hasRole("MANAGER")
								.antMatchers("/vislegis/customerList/**").hasRole("EMPLOYEE")
								.antMatchers("/vislegis/summary/letters/deleteLetter").hasRole("MANAGER")
								.antMatchers("/vislegis/summary/letters**").hasRole("EMPLOYEE")
								.antMatchers("/vislegis/summary/incomes/**").hasRole("MANAGER")
								.antMatchers("/vislegis/summary/courtHearings/deleteHearing").hasRole("MANAGER")
								.antMatchers("/vislegis/summary/courtHearings**").hasRole("EMPLOYEE")
								.antMatchers("/vislegis/summary/**").hasRole("EMPLOYEE")
								.antMatchers("/vislegis/calculator/values/updateValueForm").hasRole("ADMIN")
								.antMatchers("/vislegis/calculator/**").hasRole("EMPLOYEE")
								.antMatchers("/vislegis/docGenerator/courtsList/updateCourtForm").hasRole("MANAGER")
								.antMatchers("/vislegis/docGenerator/courtsList/deleteCourt").hasRole("MANAGER")
								.antMatchers("/vislegis/docGenerator/**").hasRole("EMPLOYEE")
								.antMatchers("/vislegis/toDoList/**").hasRole("MANAGER")
								.antMatchers("/vislegis").hasRole("EMPLOYEE")//wymga modyfikacji o customera
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
