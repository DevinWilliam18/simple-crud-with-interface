package com.project.crud.controller;

import com.project.crud.model.Faculty;
import com.project.crud.model.Student;
import com.project.crud.service.FacultyService;
import com.project.crud.service.StudentService;
import lombok.Builder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Builder
@Controller
public class RegistrationController {

    @Autowired
    private final StudentService studentService;

    @Autowired
    private final FacultyService facultyService;

    @GetMapping("/register")
    public String registration(Model model) {
        List<String> faculties = Arrays.asList(
                "Faculty of Medicine", "Faculty of Engineering",
                "Faculty of Mathematics and Computer Science","Faculty of Psychology"
        );

        List<Student> students = studentService.findAll();

        model.addAttribute("name", new String());
        model.addAttribute("email", new String());
        model.addAttribute("faculty", new String());

        model.addAttribute("allFaculties", faculties);

        model.addAttribute("students", students);
        return "registration";

    }

    @PostMapping("/register")
    public String listData (@ModelAttribute String name, @ModelAttribute String email, @ModelAttribute String faculty, Model model){

        Faculty getSelectedFaculty = facultyService.findFaculty(faculty);

        Student student = Student.builder().id(new Random().nextInt(1000)).name(name).email(email).faculty(getSelectedFaculty).build();

        studentService.add(student);

        List<Student> students = studentService.findAll();

        model.addAttribute("students", students);

        return "registration";
    }

}
