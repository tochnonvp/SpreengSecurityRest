package ru.itmentor.spring.boot_security.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.itmentor.spring.boot_security.demo.models.Person;
import ru.itmentor.spring.boot_security.demo.repositories.PersonRepository;
import ru.itmentor.spring.boot_security.demo.repositories.PersonRepositoryImpl;
import ru.itmentor.spring.boot_security.demo.security.PersonDetails;
import ru.itmentor.spring.boot_security.demo.services.PersonService;

import java.security.Principal;
import java.util.List;


@Controller
public class HelloController {

    private final PersonRepositoryImpl personRepository;

    public HelloController(PersonRepositoryImpl personRepository) {
        this.personRepository = personRepository;
    }


    @GetMapping("/hello")
    public String sayHello(){
        return "hello";
    }

    @GetMapping("/showUserInfo")
    public String showUserInfo(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        PersonDetails personDetails = (PersonDetails)authentication.getPrincipal();
        System.out.println("personDetails: " + personDetails.getPerson());
        return "hello";
    }

    @Autowired
    private PersonService personService;

    @GetMapping("/profile")
    public String userProfile(Model model, Principal principal) {
        String username = principal.getName();
        Person person = personService.getUserByUsername(username);
        model.addAttribute("user", person);
        return "profile";
    }




}