package ru.itmentor.spring.boot_security.demo.controllers;


import org.apache.tomcat.jni.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ru.itmentor.spring.boot_security.demo.models.Person;
import ru.itmentor.spring.boot_security.demo.repositories.PersonRepositoryImpl;
import ru.itmentor.spring.boot_security.demo.security.PersonDetails;

import java.util.List;


@Controller
@RequestMapping("/admin")
public class AdminController {

    private final PersonRepositoryImpl personRepository;

    public AdminController(PersonRepositoryImpl personRepository) {
        this.personRepository = personRepository;
    }


    @GetMapping("/showUserInfo")
    public String showUserInfo(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        PersonDetails personDetails = (PersonDetails)authentication.getPrincipal();
        System.out.println("personDetails: " + personDetails.getPerson());
        return "hello";
    }


    @GetMapping(value = "/admin")
    public String getAllUsers(ModelMap model) {
        model.addAttribute("admin", personRepository.findAllUsers());
        return "admin";
    }

}