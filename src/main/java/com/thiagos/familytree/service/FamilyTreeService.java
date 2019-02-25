package com.thiagos.familytree.service;

import com.thiagos.familytree.model.dao.person.PersonDAO;
import com.thiagos.familytree.model.dao.relationship.RelationshipDAO;
import com.thiagos.familytree.model.dto.FamilyTreeType;
import com.thiagos.familytree.model.dto.Person;
import com.thiagos.familytree.model.dto.Relationship;
import com.thiagos.familytree.service.treeBuilder.DescendantsTreeBuilder;
import com.thiagos.familytree.service.treePrinter.TextTreePrinter;
import com.thiagos.familytree.util.FamilyNode;
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
    private TreeBuilder treeBuilder, ahnentafelTreeBuilder, descendantsTreeBuilder;
    private TreePrinter treePrinter, jsonTreePrinter, textTreePrinter;


    @Autowired
    public FamilyTreeService(PersonDAO personDAO, RelationshipDAO relationshipDAO) {
        this.personDAO = personDAO;
        this.relationshipDAO = relationshipDAO;

        // multiple tree builder implementations
        ahnentafelTreeBuilder = new AhnentafelTreeBuilder(personDAO, relationshipDAO);
        descendantsTreeBuilder = new DescendantsTreeBuilder(personDAO, relationshipDAO);

        // multiple tree printer implementations
        jsonTreePrinter = new JsonTreePrinter();
        textTreePrinter = new TextTreePrinter();

        treePrinter = jsonTreePrinter;
    }

    public void addPersons(List<Person> persons) {
        personDAO.saveAll(persons);
    }

    public void addRelationships(List<Relationship> relationships) {
        relationshipDAO.saveAll(relationships);
    }

    public FamilyNode getFamilyTree(Long personId) {
        Person person = personDAO.findByPersonId(personId);
        FamilyNode familyTreeNode = treeBuilder.buildTree(person);
        return (FamilyNode) treePrinter.printTree(familyTreeNode);
    }

    /**
     * Method replacing generic treeBuilder interface instance with concrete implementation
     * @param familyTreeType
     */
    public void setTreeBuilderByTreeType(FamilyTreeType familyTreeType) {
        if (familyTreeType.equals(FamilyTreeType.AHNENTAFEL))
            treeBuilder = ahnentafelTreeBuilder;
        else
            treeBuilder = descendantsTreeBuilder;
    }

    public String getTreePrint(FamilyNode familyNode) {
        return (String) textTreePrinter.printTree(familyNode);
    }
}
