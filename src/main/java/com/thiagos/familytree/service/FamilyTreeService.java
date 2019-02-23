package com.thiagos.familytree.service;

import com.thiagos.familytree.model.dao.person.PersonDAO;
import com.thiagos.familytree.model.dao.relationship.RelationshipDAO;
import com.thiagos.familytree.model.dto.Person;
import com.thiagos.familytree.model.dto.Relationship;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FamilyTreeService {

    private PersonDAO personDAO;
    private RelationshipDAO relationshipDAO;


    @Autowired
    public FamilyTreeService(PersonDAO personDAO, RelationshipDAO relationshipDAO) {
        this.personDAO = personDAO;
        this.relationshipDAO = relationshipDAO;
    }

    public void addPersons(List<Person> persons) {
        personDAO.saveAll(persons);
    }

    public void addRelationships(List<Relationship> relationships) {
        relationshipDAO.saveAll(relationships);
    }
}
