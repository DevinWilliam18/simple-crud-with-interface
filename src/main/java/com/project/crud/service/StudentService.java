package com.project.crud.service;

import com.project.crud.model.Faculty;
import com.project.crud.model.Student;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StudentService {

    List<Student> students = new ArrayList<>();

    @Autowired
    private FacultyService facultyService;

    @PostConstruct
    private void initData(){
        students.add(Student.builder().id(1).name("Vivin").email("vivin@yahoo.co.id").faculty(facultyService.findFaculty("Faculty of Medicine")).build());
        students.add(Student.builder().id(2).name("Nonong").email("nonong@gmail.com").faculty(facultyService.findFaculty("Faculty of Medicine")).build());
    }

    public void add(Student student){
        students.add(student);
    }

    public List<Student> findAll(){
        return students;
    }

}
