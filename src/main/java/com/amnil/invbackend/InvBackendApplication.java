package com.amnil.invbackend;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * The type Inv backend application.
 */
@SpringBootApplication
public class InvBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(InvBackendApplication.class, args);
	}
	@Bean
	public ModelMapper modelMapper(){
		return new ModelMapper();
	}

}
