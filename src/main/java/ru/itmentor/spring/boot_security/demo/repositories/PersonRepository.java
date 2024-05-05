package ru.itmentor.spring.boot_security.demo.repositories;

import ru.itmentor.spring.boot_security.demo.models.Person;

import java.util.List;

public interface PersonRepository {


    public interface UserRepository {
        List<Person> findAllUsers();
    }
}
