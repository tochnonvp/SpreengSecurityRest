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
        Person personToBeUpdate = entityManager.find(Person.class, userId);

        personToBeUpdate.setRole(updatedPerson.getRole());
        personToBeUpdate.setName(updatedPerson.getName());
        personToBeUpdate.setSurname(updatedPerson.getSurname());
        personToBeUpdate.setAge(updatedPerson.getAge());

//        entityManager.persist(personToBeUpdate);
//        personRepository.save(updatedPerson);
    }
}





