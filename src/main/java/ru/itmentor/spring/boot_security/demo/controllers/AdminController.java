package ru.itmentor.spring.boot_security.demo.controllers;


import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ru.itmentor.spring.boot_security.demo.models.Person;
import ru.itmentor.spring.boot_security.demo.repositories.PersonRepository;
import ru.itmentor.spring.boot_security.demo.security.PersonDetails;
import ru.itmentor.spring.boot_security.demo.services.PersonService;



@Controller
@RequestMapping("/admin")
public class AdminController {

    private final PersonRepository personRepository;
    private final PersonService personService;

    public AdminController(PersonRepository personRepository, PersonService personService) {
        this.personRepository = personRepository;
        this.personService = personService;
    }


    @GetMapping("/showUserInfo")
    public String showUserInfo() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        PersonDetails personDetails = (PersonDetails) authentication.getPrincipal();
        System.out.println("personDetails: " + personDetails.getPerson());
        return "hello";
    }


    @GetMapping(value = "/admin")
    public String getAllUsers(ModelMap model) {
        model.addAttribute("admin", personRepository.findAllUsers());
        return "admin";
    }


    @PostMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        personService.deleteUser(id);
        return "redirect:/admin/admin";
    }

    // Метод для обновления данных пользователя
    @PostMapping("/edit/{id}")
    public String editUser(Model model, @PathVariable("id") Long id) {
        Person user = personRepository.findById(id).orElse(null);
        if (user != null) {
            model.addAttribute("user", user);
        }
        return "/edit";
    }

    @PostMapping("/save/{id}")
    public String save(@ModelAttribute("user") Person person, @PathVariable("id") Long id) {
        person.setId(id);
        personService.updateUser(person, id);
        return "redirect:/admin/admin";
    }
    }

