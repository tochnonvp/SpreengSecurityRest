package ru.itmentor.spring.boot_security.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itmentor.spring.boot_security.demo.models.Person;
import ru.itmentor.spring.boot_security.demo.repositories.PeopleRepository;
import ru.itmentor.spring.boot_security.demo.repositories.PersonRepository;

import java.util.Optional;

@Service
public class PersonService {

//private final PersonRepository personRepository;
private final PeopleRepository peopleRepositoryRepository;


    public PersonService(PersonRepository personRepository, PeopleRepository peopleRepositoryRepository) {
        this.peopleRepositoryRepository = peopleRepositoryRepository;

//        this.personRepository = personRepository;
    }


    public Person getUserByUsername(String username) {
        return peopleRepositoryRepository.findByName(username);
    }
}