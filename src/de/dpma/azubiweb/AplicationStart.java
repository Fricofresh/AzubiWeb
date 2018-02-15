package de.dpma.azubiweb;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class AplicationStart {
	
	public static void main(String[] args) {
		
		new SpringApplicationBuilder().sources(AplicationStart.class).run(args);
		// SpringApplication.run(AplicationStart.class, args);
	}
}
