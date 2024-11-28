package com.project.crud.controller;

import com.project.crud.model.Faculty;
import com.project.crud.model.Student;
import com.project.crud.service.FacultyService;
import com.project.crud.service.StudentService;
import lombok.Builder;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import javax.annotation.PostConstruct;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;

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
    public String viewRegistration(Model model) {

        List<Student> students = studentService.findAll();

        model.addAttribute("allFaculties", faculties);

        model.addAttribute("students", students);
        return "registration";

    }

    @PostMapping("/register")
    public String postData(@RequestParam String name, @RequestParam String email, @RequestParam String faculty,
            Model model) {
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
            response = true;
        }

        return "redirect:/register";
    }

}
