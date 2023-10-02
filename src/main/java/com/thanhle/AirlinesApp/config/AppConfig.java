package com.thanhle.AirlinesApp.config;


import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

@Configuration
@PropertySource(value="classpath:db.properties")
public class AppConfig {
	@Autowired
	Environment env;
	
	@Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
	
	@Bean
	public DriverManagerDataSource dataSource() {
		
		System.out.println("url: "+env.getProperty("url"));
		System.out.println("driver: "+env.getProperty("driver"));
		System.out.println("user name: "+env.getProperty("myusername"));
		System.out.println("password: "+env.getProperty("mypassword"));
		
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setUrl(env.getProperty("url"));
		dataSource.setDriverClassName(env.getProperty("driver"));
		dataSource.setUsername(env.getProperty("myusername"));
		dataSource.setPassword(env.getProperty("mypassword"));
		
		return dataSource;
	}
	
	
	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
	         LocalContainerEntityManagerFactoryBean entityManager = new  LocalContainerEntityManagerFactoryBean();
	         entityManager.setDataSource(dataSource());
	         entityManager.setPackagesToScan("com.thanhle.AirlinesApp.domain");
	         entityManager.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
	         entityManager.setJpaProperties(jpaProerties());
	         return entityManager;
	}
	
	
	Properties jpaProerties() {
		Properties jpaProerties = new Properties();
		jpaProerties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
		jpaProerties.setProperty("hibernate.show_sql", "true");
		jpaProerties.setProperty("hibernate.hbm2ddl.auto", "update");
		return jpaProerties;
	}
	
	@Bean
	public ViewResolver viewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setPrefix("/WEB-INF/jsp/");
		viewResolver.setSuffix(".jsp");
		viewResolver.setViewClass(JstlView.class);
		return viewResolver;
	}

}
