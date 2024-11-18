package com.project.crud.service;

import com.project.crud.model.Faculty;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.annotation.PostConstruct;

public class FacultyService {

    List<Faculty> faculties = new ArrayList<>();

    @PostConstruct
    public void initData(){
        faculties.add(Faculty.builder().id(new Random().nextInt(1000)).name("Faculty of Medicine").build());
        faculties.add(Faculty.builder().id(new Random().nextInt(1000)).name("Faculty of Engineering").build());
        faculties.add(Faculty.builder().id(new Random().nextInt(1000)).name("Faculty of Mathematics and Computer Science").build());
        faculties.add(Faculty.builder().id(new Random().nextInt(1000)).name("Faculty of Psychology").build());
    }

    public void addFaculty(Faculty faculty){
        faculties.add(faculty);
    }

    public List<Faculty> getFaculties(){
        return faculties;
    }

    public Faculty findFaculty(String name){
        Faculty getFaculty = null;
        for (Faculty faculty: faculties){
            if (faculty.getName().equals(name)){
                getFaculty = faculty;
                break;
            }
        }
        return getFaculty;
    }

}
