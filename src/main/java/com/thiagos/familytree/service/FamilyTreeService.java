package com.thiagos.familytree.service;

import com.thiagos.familytree.model.dao.person.PersonDAO;
import com.thiagos.familytree.model.dao.relationship.RelationshipDAO;
import com.thiagos.familytree.model.dto.Person;
import com.thiagos.familytree.model.dto.Relationship;
import com.thiagos.familytree.model.helper.Node;
import com.thiagos.familytree.service.treeBuilder.AhnentafelTreeBuilder;
import com.thiagos.familytree.service.treeBuilder.TreeBuilder;
import com.thiagos.familytree.service.treePrinter.JsonTreePrinter;
import com.thiagos.familytree.service.treePrinter.TreePrinter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FamilyTreeService {

    private PersonDAO personDAO;
    private RelationshipDAO relationshipDAO;
    private TreeBuilder treeBuilder;
    private TreePrinter treePrinter;


    @Autowired
    public FamilyTreeService(PersonDAO personDAO, RelationshipDAO relationshipDAO) {
        this.personDAO = personDAO;
        this.relationshipDAO = relationshipDAO;
        treeBuilder = new AhnentafelTreeBuilder(personDAO, relationshipDAO);
        treePrinter = new JsonTreePrinter();
    }

    public void addPersons(List<Person> persons) {
        personDAO.saveAll(persons);
    }

    public void addRelationships(List<Relationship> relationships) {
        relationshipDAO.saveAll(relationships);
    }

    public String getFamilyTree(Long personId) {
        Person person = personDAO.findByPersonId(personId);
        Node familyTreeNode = treeBuilder.buildTree(person);
        return treePrinter.printTree(familyTreeNode);
    }
}
