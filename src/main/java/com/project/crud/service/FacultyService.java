package com.project.crud.service;

import com.project.crud.model.Faculty;

import java.util.ArrayList;
import java.util.List;

public class FacultyService {

    List<Faculty> faculties = new ArrayList<>();

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
