package com.thiagos.familytree.model.request;

import com.thiagos.familytree.model.dto.Person;

import java.util.List;

public class AddPersonsRequest {
    private List<Person> persons;

    public List<Person> getPersons() {
        return persons;
    }

    public void setPersons(List<Person> persons) {
        this.persons = persons;
    }
}
