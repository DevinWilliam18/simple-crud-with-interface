package com.project.crud.service;

import com.project.crud.model.Faculty;
import com.project.crud.model.Student;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StudentService {

    List<Student> students = new ArrayList<>();
    List<Faculty> faculties = new ArrayList<>(Arrays.asList(
            Faculty.builder().id(1).name("Faculty of Medicine").build(),
            Faculty.builder().id(2).name("Faculty of Engineering").build(),
            Faculty.builder().id(3).name("Faculty of Mathematics and Computer Science").build(),
            Faculty.builder().id(3).name("Faculty of Psychology").build()
    ));

    @PostConstruct
    private void initData(){
        students.add(Student.builder().id(1).name("Vivin").email("vivin@yahoo.co.id").faculty(faculties.get(0)).build());
        students.add(Student.builder().id(2).name("Nonong").email("nonong@gmail.com").faculty(faculties.get(2)).build());
    }

    public void add(Student student){
        students.add(student);
    }

    public List<Student> findAll(){
        return students;
    }

}
