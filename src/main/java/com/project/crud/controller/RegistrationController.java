package com.project.crud.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
public class RegistrationController {

    @GetMapping("/register")
    public String registration(Model model) {
        List<String> faculties = Arrays.asList(
                "Faculty of Medicine", "Faculty of Engineering",
                "Faculty of Mathematics and Computer Science","Faculty of Psychology"
        );

        model.addAttribute("name", new String());
        model.addAttribute("email", new String());
        model.addAttribute("faculty", faculties);

        return "registration.html";
    }

}
