package com.project.crud.controller;

import lombok.Builder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Builder
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

        return "registration";
    }

    @PostMapping("/register")
    public String listData (@ModelAttribute String name, @ModelAttribute String email, @ModelAttribute String faculty, Model model){
        model.addAttribute("name", name);
        model.addAttribute("email", email);
        model.addAttribute("faculty", faculty);

        return "registration";
    }

}
