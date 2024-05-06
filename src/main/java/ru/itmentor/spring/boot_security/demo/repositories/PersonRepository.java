package ru.itmentor.spring.boot_security.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itmentor.spring.boot_security.demo.models.Person;

import java.util.List;

public interface PersonRepository extends JpaRepository<Person, Long> {
    Person findByName(String name);
}
