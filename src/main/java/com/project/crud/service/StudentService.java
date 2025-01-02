package com.project.crud.service;

import com.project.crud.model.Faculty;
import com.project.crud.model.Student;

import com.project.crud.repository.StudentRepo;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Slf4j
@Service
public class StudentService {

    @Autowired
    private StudentRepo studentRepo;

    @Autowired
    private FacultyService facultyService;

    @PostConstruct
    private void initData() {
        studentRepo.save(Student.builder().id(11).name("Vivin").email("vivin@yahoo.co.id")
                .faculty(facultyService.findFaculty("Faculty of Medicine")).build());
        studentRepo.save(Student.builder().id(22).name("Nonong").email("nonong@gmail.com")
                .faculty(facultyService.findFaculty("Faculty of Medicine")).build());
        studentRepo.save(Student.builder().id(33).name("Rhenald Kalisa").email("rhenaldkalisa@gmail.com")
                .faculty(facultyService.findFaculty("Faculty of Mathematics and Computer Science")).build());
        studentRepo.save(Student.builder().id(44).name("Gby").email("gby99@gmail.com")
                .faculty(facultyService.findFaculty("Faculty of Psychology")).build());
        studentRepo.save(Student.builder().id(55).name("Dono Warkop").email("dono@gmail.com")
                .faculty(facultyService.findFaculty("Faculty of Psychology")).build());
    }

    public void add(Student student) {
        studentRepo.save(student);
    }

    public List<Student> findAll() {
        return studentRepo.findAll();
    }

    public Page<Student> findPaginated(int page, int size){
//        convert list into Page
        Pageable pageReq = PageRequest.of(page, size);

        int start = (int) pageReq.getOffset();
        int end = Math.min((start + pageReq.getPageSize()), findAll().size());

        List<Student> contents = findAll().subList(start, end);

        return new PageImpl<>(contents, pageReq, findAll().size());

    }

    public Student findStudent(String id) {
        Student student = studentRepo.findById(Integer.parseInt(id)).get();

//        for (Student target : findAll()) {
//            if (target.getId() == Integer.parseInt(id)) {
//                student = target;
//                break;
//            }
//        }

        return student;
    }

//    public int findIndex(String id) {
//        int index = -1;
//        Student student = findStudent(id);
//
//        List<Student> getAll = findAll();
//
//        for (int i = 0; i < findAll().size(); i++) {
//            if (getAll.get(i).getId() == Integer.parseInt(id)) {
//                index = i;
//                break;
//            }
//        }
//        return index;
//    }

    public boolean editStudent(String id, String email, String name, Faculty faculty) {
        boolean resp = false;
        Student studentRef = findStudent(id);

        if (studentRef != null) {
            studentRef.setName(name);
            studentRef.setEmail(email);
            studentRef.setFaculty(faculty);

            studentRepo.save(studentRef);
            resp = true;
        }
        return resp;
    }

    public boolean deleteStudent(String id){
        Student student = findStudent(id);
        boolean resp = false;

        if (student != null){
            studentRepo.deleteById(Integer.parseInt(id));
            resp = true;
        }

        return resp;
    }

}