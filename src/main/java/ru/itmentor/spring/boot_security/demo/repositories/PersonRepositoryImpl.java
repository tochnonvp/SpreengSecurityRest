package ru.itmentor.spring.boot_security.demo.repositories;

import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.itmentor.spring.boot_security.demo.models.Person;

import javax.persistence.EntityManager;
import java.util.List;


    @Repository
    public class PersonRepositoryImpl implements PersonRepository{

        private final EntityManager entityManager;

        @Autowired
        public PersonRepositoryImpl(EntityManager entityManager) {
            this.entityManager = entityManager;
        }

        public List<Person> findAllUsers() {
            return entityManager.createQuery("FROM Person ", Person.class).getResultList();
        }
    }

