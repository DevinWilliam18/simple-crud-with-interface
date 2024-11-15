package com.project.crud.service;

import com.project.crud.model.Faculty;
import com.project.crud.model.Student;
import org.apache.catalina.LifecycleState;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CrudService {

    List<Student> students = new ArrayList<>();
    List<Faculty> faculties = new ArrayList<>(Arrays.asList(
            Faculty.builder().id(1).name("Faculty of Medicine").build(),
            Faculty.builder().id(2).name("Faculty of Engineering").build(),
            Faculty.builder().id(3).name("Faculty of Mathematics and Computer Science").build(),
            Faculty.builder().id(3).name("Faculty of Psychology").build()
    ));

    @PostConstruct
    private void initData(){
        students.add(Student.builder().build());
    }


}
