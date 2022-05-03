package ru.digitalleague.cities;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
public class CitiesApplication {

	public static void main(String[] args) {
		SpringApplication.run(CitiesApplication.class, args);
	}

}
