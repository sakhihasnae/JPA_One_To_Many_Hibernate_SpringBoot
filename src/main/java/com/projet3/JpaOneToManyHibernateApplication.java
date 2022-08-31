package com.projet3;
/** @author Hasnae SAKHI */
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class JpaOneToManyHibernateApplication {

	public static void main(String[] args) {
		SpringApplication.run(JpaOneToManyHibernateApplication.class, args);
	}

	@Bean 
	public ModelMapper modelMapper()
	{ return new ModelMapper(); }
	
}
