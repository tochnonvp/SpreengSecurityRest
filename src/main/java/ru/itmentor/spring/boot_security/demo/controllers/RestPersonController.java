package ru.itmentor.spring.boot_security.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import ru.itmentor.spring.boot_security.demo.models.Person;
import ru.itmentor.spring.boot_security.demo.repositories.PersonRepository;
import ru.itmentor.spring.boot_security.demo.security.PersonDetails;
import ru.itmentor.spring.boot_security.demo.services.PersonService;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api")
public class RestPersonController {

    private final PersonRepository personRepository;
    private final PersonService personService;

    @Autowired
    public RestPersonController(PersonRepository personRepository, PersonService personService) {
        this.personRepository = personRepository;
        this.personService = personService;
    }

    @GetMapping("/hello")
    public String sayHello() {
        return "Hello from REST API!";
    }

    @GetMapping("/showUserInfo")
    public Person showUserInfo() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        PersonDetails personDetails = (PersonDetails) authentication.getPrincipal();
        return personDetails.getPerson();
    }

    @GetMapping("/profile")
    public Person userProfile(Principal principal) {
        String username = principal.getName();
        return personService.getUserByUsername(username);
    }

    @GetMapping("/admin")
    public List<Person> getAllUsers() {
        return personRepository.findAllUsers();
    }

    @DeleteMapping("/admin/delete/{id}")
    public void deleteUser(@PathVariable("id") Long id) {
        personService.deleteUser(id);
    }

    @PutMapping ("/admin/edit/{id}")
    public void editUser(@PathVariable("id") Long id, @RequestBody Person updatedPerson) {
        personService.updateUser(updatedPerson, id);
    }

}