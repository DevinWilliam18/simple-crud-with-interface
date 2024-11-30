package com.project.crud.service;

import com.project.crud.model.Faculty;
import com.project.crud.model.Student;

import lombok.extern.slf4j.Slf4j;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Slf4j
public class StudentService {

    List<Student> students = new ArrayList<>();

    @Autowired
    private FacultyService facultyService;

    @PostConstruct
    private void initData() {
        students.add(Student.builder().id(1).name("Vivin").email("vivin@yahoo.co.id")
                .faculty(facultyService.findFaculty("Faculty of Medicine")).build());
        students.add(Student.builder().id(2).name("Nonong").email("nonong@gmail.com")
                .faculty(facultyService.findFaculty("Faculty of Medicine")).build());
    }

    public void add(Student student) {
        students.add(student);
    }

    public List<Student> findAll() {
        return students;
    }

    public Student findStudent(String id) {
        Student student = null;

        for (Student target : findAll()) {
            if (target.getId() == Integer.parseInt(id)) {
                student = target;
                break;
            }
        }
        return student;
    }

    public int findIndex(String id) {
        int index = 0;
        Student student = findStudent(id);

        List<Student> getAll = findAll();

        for (int i = 0; i < findAll().size(); i++) {
            if (getAll.get(i).getId() == Integer.parseInt(id)) {
                index = i;
                break;
            }
        }
        return index;
    }

    public boolean editStudent(String id, String email, String name, Faculty faculty) {
        boolean resp = false;
        Student studentReference = findStudent(id);
        Student student = new Student();

        if (studentReference != null) {
            int index = findIndex(id);
            student.setId(Integer.parseInt(id));
            student.setName(name);
            student.setEmail(email);
            student.setFaculty(faculty);

            findAll().set(index, student);
            resp = true;
        }

        return resp;
    }

}
