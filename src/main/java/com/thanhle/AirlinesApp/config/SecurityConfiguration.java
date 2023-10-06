package com.thanhle.AirlinesApp.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.HttpBasicConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

import com.thanhle.AirlinesApp.component.CustomAuthenticationProvider;


@Configuration
@EnableWebSecurity
@ComponentScan
public class SecurityConfiguration {
	
	@Autowired
	private HandlerMappingIntrospector requestMappingHandlerMapping;


	//@Autowired UserDetailsService userDetailsService;
    //@Autowired BCryptPasswordEncoder bCryptPasswordEncoder;

	private final UserDetailsService userDetailsService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    
    @Autowired
    public SecurityConfiguration(UserDetailsService userDetailsService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userDetailsService = userDetailsService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }
    
    @Autowired
    private CustomAuthenticationProvider customAuthProvider;

    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(customAuthProvider);
    }
    
    /*
    @Bean
    public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
            .requestMatchers("/api/**").permitAll()
            .anyRequest().authenticated()
            .and()
            .formLogin()
            .loginPage("/signin")
            .defaultSuccessUrl("/homepage", true);
        return http.build();
    }
    */

   
    @Order(1)
	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
		
		http.authorizeHttpRequests().anyRequest().permitAll(); // bypasses all the http security
		return http.build();
	}

	
    
    
    MvcRequestMatcher apiRequestMatcher = new MvcRequestMatcher(requestMappingHandlerMapping, "/api/**");
    
    @Order(2)
    @Bean
    public SecurityFilterChain filterChain2(HttpSecurity http) throws Exception {
      http
        .authorizeHttpRequests(authz -> authz
          .requestMatchers(apiRequestMatcher).permitAll()
          .anyRequest().authenticated()
        )
        .authenticationManager(authenticationManager -> authenticationManager);
      return http.build();
    }
    

    
	
	@Bean
    @Primary
    public DaoAuthenticationProvider authProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(bCryptPasswordEncoder);
        return authProvider;
    }
    
    
	
	
}
