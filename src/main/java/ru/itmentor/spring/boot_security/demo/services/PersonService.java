package ru.itmentor.spring.boot_security.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itmentor.spring.boot_security.demo.models.Person;
import ru.itmentor.spring.boot_security.demo.repositories.PersonRepository;

import javax.persistence.EntityManager;
import java.util.Optional;

@Service
public class PersonService {

    private final EntityManager entityManager;

    private final PersonRepository personRepository;

    public PersonService(EntityManager entityManager, PersonRepository personRepository) {
        this.entityManager = entityManager;
        this.personRepository = personRepository;
    }

    public Person getUserByUsername(String username) {
        return personRepository.findByName(username);
    }

    public void deleteUser(Long userId) {
        personRepository.deleteById(userId);
    }


    public void updateUser(Person updatedPerson, Long userId) {
        Optional<Person> optionalPerson = personRepository.findById(userId);
        if (optionalPerson.isPresent()) {
            Person personToBeUpdate = optionalPerson.get();
            personToBeUpdate.setRole(updatedPerson.getRole());
            personToBeUpdate.setName(updatedPerson.getName());
            personToBeUpdate.setSurname(updatedPerson.getSurname());
            personToBeUpdate.setAge(updatedPerson.getAge());
            personRepository.save(personToBeUpdate); // Обновление данных пользователя в базе данных
        } else {
            // Обработка ситуации, когда пользователь с указанным id не найден
        }
    }
}





