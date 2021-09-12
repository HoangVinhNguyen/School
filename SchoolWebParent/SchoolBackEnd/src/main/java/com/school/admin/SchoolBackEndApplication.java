package com.school.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan({"com.school.common.entity", "com.school.amdin.user"})
public class SchoolBackEndApplication {

	public static void main(String[] args) {
		SpringApplication.run(SchoolBackEndApplication.class, args);
	}

}
