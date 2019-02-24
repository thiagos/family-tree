package com.thiagos.familytree.model.dao.person;

import com.thiagos.familytree.model.dto.Person;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PersonDAO {
    private PersonRepository repository;

    public PersonDAO(PersonRepository repository) {
        this.repository = repository;
    }

    public Person findByPersonId(Long personId) { return repository.findByPersonId(personId); }

    public void save(Person person) { repository.save(person); }

    public void saveAll(List<Person> persons) { repository.saveAll(persons); }
}
