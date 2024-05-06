package ru.itmentor.spring.boot_security.demo.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.itmentor.spring.boot_security.demo.models.Person;

import javax.validation.constraints.NotNull;
import java.util.List;
@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
    Person findByName(String name);

    List<Person> findAllUsers();


    void deleteById(@NotNull Long userId);

    <S extends Person> S save(S person);


}
