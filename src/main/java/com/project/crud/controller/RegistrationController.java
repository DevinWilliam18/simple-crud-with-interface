package com.project.crud.controller;

import com.project.crud.model.Faculty;
import com.project.crud.model.Student;
import com.project.crud.service.FacultyService;
import com.project.crud.service.StudentService;
import lombok.Builder;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import javax.annotation.PostConstruct;

@Builder
@Controller
@Slf4j
public class RegistrationController {

    @Autowired
    private final StudentService studentService;

    @Autowired
    private final FacultyService facultyService;

    private List<String> faculties = new ArrayList<>();

    @PostConstruct
    public void initData() {
        faculties.add("Faculty of Medicine");
        faculties.add("Faculty of Engineering");
        faculties.add("Faculty of Mathematics and Computer Science");
        faculties.add("Faculty of Psychology");
    }

    @GetMapping("/register")
    public String viewRegistration(Model model, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "3") int size) {

        List<Student> students = new ArrayList<>();
        Page<Student> studentPagination = studentService.findPaginated(page, size);
        students = studentPagination.getContent();

        model.addAttribute("allFaculties", faculties);

        model.addAttribute("students", students);
        return "registration";

    }

    @PostMapping("/register")
    public String postData(@RequestParam String name, @RequestParam String email, @RequestParam String faculty,
            Model model) {
        log.info("post");
        Faculty getSelectedFaculty = facultyService.findFaculty(faculty);

        Student student = Student.builder().id(new Random().nextInt(1000)).name(name).email(email)
                .faculty(getSelectedFaculty).build();
        studentService.add(student);

        List<Student> students = studentService.findAll();

        model.addAttribute("students", students);
        model.addAttribute("allFaculties", faculties);

        return "registration";

    }

    @PostMapping("/register/{id}")
    public String updateData(@PathVariable String id, @RequestParam String editEmail, @RequestParam String editName,
            @RequestParam String editFaculty) {
        boolean response = false;
        System.out.println("updateData: " + id);
        Faculty getFaculty = facultyService.findFaculty(editFaculty);
        if (studentService.editStudent(id, editEmail, editName, getFaculty)) {
            log.info("student has been inserted");
            response = true;
        }

        return "redirect:/register";
    }

    @DeleteMapping("/student/{id}")
    public ResponseEntity<String> delete(@PathVariable String id) {
        boolean resp = studentService.deleteStudent(id);
        return resp == true ? new ResponseEntity<>(id, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }



}
