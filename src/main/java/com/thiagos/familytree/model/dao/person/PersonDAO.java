package com.thiagos.familytree.model.dao.person;

import com.thiagos.familytree.model.dto.Person;
import com.thiagos.familytree.model.exception.DataException;
import org.springframework.dao.DataIntegrityViolationException;
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

    public void saveAll(List<Person> persons) {
        try {
            repository.saveAll(persons);
        } catch (DataIntegrityViolationException e) {
            throw new DataException("Duplicate PersonId attempted, please retry with valid personId(s)");
        }
    }
}
