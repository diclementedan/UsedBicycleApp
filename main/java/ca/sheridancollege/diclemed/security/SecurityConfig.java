package ca.sheridancollege.diclemed.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import ca.sheridancollege.diclemed.services.UserDetailsServicesImpl;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	@Autowired
	private LoggingAccessDeniedHandler accessDeniedHandler;
	
	@Autowired
	UserDetailsServicesImpl userDetailsService;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {  
		http.csrf().disable();
		http.headers().frameOptions().disable();  						// allow access to h2 console
		
		http.authorizeRequests()
		.antMatchers("/secure/insert").hasRole("USER") 					// only allow users to access insert.html
		.antMatchers("/secure/update").hasRole("USER") 					// only allow users to access update.html
		.antMatchers("/secure/delete").hasRole("ADMIN")					// only allow administrator to access delete.html
		.antMatchers("/","/css/**","/images/**","/**").permitAll()
		
		.antMatchers("/h2-console/**").permitAll() 						// allow access to /h2-console
		.anyRequest().authenticated()
		.and().formLogin()
			.loginPage("/login").permitAll()							// allow anyone access to login.html
		.and().logout()													// and logout page (auto generated)
			.invalidateHttpSession(true)
			.clearAuthentication(true)
			.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
			.logoutSuccessUrl("/login?logout").permitAll()
			.and().exceptionHandling()
				.accessDeniedHandler(accessDeniedHandler);
	}
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {  
		auth.userDetailsService(userDetailsService)
			.passwordEncoder(passwordEncoder());
	}
}
