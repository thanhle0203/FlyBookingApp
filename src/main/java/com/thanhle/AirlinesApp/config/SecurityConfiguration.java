package com.thanhle.AirlinesApp.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
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
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;



@Configuration
@EnableWebSecurity
@ComponentScan
public class SecurityConfiguration {

	//@Autowired UserDetailsService userDetailsService;
    //@Autowired BCryptPasswordEncoder bCryptPasswordEncoder;

	private final UserDetailsService userDetailsService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    
    @Autowired
    public SecurityConfiguration(UserDetailsService userDetailsService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userDetailsService = userDetailsService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }
    
   
    
	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
		
		http.authorizeHttpRequests().anyRequest().permitAll(); // bypasses all the http security
		return http.build();
	}
	
	
    /*
	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
	    http.authorizeHttpRequests()
	        .requestMatchers("/signup").permitAll()
	        .anyRequest().authenticated();
	    return http.build();
	}
	*/
	
	
	/*
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests((authz) -> authz
                .requestMatchers("/admin/**").hasRole("ADMIN")
                .requestMatchers("/user/**").hasRole("USER")
                .anyRequest().authenticated()
            );
        return http.build();
    }
    */
	
    
	/*   
	@Bean
    @Primary
    public DaoAuthenticationProvider authProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(bCryptPasswordEncoder);
        return authProvider;
    }
    */
	
	
	/*
	@Bean
	public InMemoryUserDetailsManager userDetailsService() {
		
		List<UserDetails> users = new ArrayList<>();
		List<GrantedAuthority> authority1 = new ArrayList<>();
		authority1.add(new SimpleGrantedAuthority("Admin"));
		authority1.add(new SimpleGrantedAuthority("User"));
		
		List<GrantedAuthority> authority2 = new ArrayList<>();
		authority2.add(new SimpleGrantedAuthority("HR"));
		authority2.add(new SimpleGrantedAuthority("User"));
		
		List<GrantedAuthority> authority3 = new ArrayList<>();
		authority3.add(new SimpleGrantedAuthority("Programmer"));
		authority3.add(new SimpleGrantedAuthority("User"));
		
		List<GrantedAuthority> authority4 = new ArrayList<>();
		authority4.add(new SimpleGrantedAuthority("User"));
		
		UserDetails user1 = new User("lemar@gmail.com", bCrypt.encode("lemar"), authority1);
		users.add(user1);
		
		UserDetails user2 = new User("garric@gmail,com", bCrypt.encode("garric"), authority1);
		users.add(user2);
		
		
		
		
		return new InMemoryUserDetailsManager(users);
		
	}
	*/
    

}
