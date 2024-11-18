package com.project.crud;

import com.project.crud.service.FacultyService;
import com.project.crud.service.StudentService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CrudApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrudApplication.class, args);
	}

	@Bean
	public StudentService crudService(){
		return new StudentService();
    }

	@Bean
	public FacultyService facultyService(){
		return new FacultyService();
	}

}