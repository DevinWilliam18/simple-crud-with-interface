package com.project.crud.service;

import com.project.crud.model.Faculty;
import com.project.crud.repository.FacultyRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.annotation.PostConstruct;

@Service
public class FacultyService {

//    List<Faculty> faculties = new ArrayList<>();

    @Autowired
    private FacultyRepo facultyRepo;


    @PostConstruct
    public void initData(){
        facultyRepo.save(Faculty.builder().id(new Random().nextInt(1000)).name("Faculty of Medicine").build());
        facultyRepo.save(Faculty.builder().id(new Random().nextInt(1000)).name("Faculty of Engineering").build());
        facultyRepo.save(Faculty.builder().id(new Random().nextInt(1000)).name("Faculty of Mathematics and Computer Science").build());
        facultyRepo.save(Faculty.builder().id(new Random().nextInt(1000)).name("Faculty of Psychology").build());

    }

    public void addFaculty(Faculty faculty){
        facultyRepo.save(faculty);
    }

    public List<Faculty> getFaculties(){
        return facultyRepo.findAll();
    }

    public Faculty findFaculty(String name){
        Faculty getFaculty = null;
        for (Faculty faculty: facultyRepo.findAll()){
            if (faculty.getName().equals(name)){
                getFaculty = faculty;
                break;
            }
        }
        return getFaculty;
    }

}
