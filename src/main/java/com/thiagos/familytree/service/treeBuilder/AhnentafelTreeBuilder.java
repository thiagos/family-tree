package com.thiagos.familytree.service.treeBuilder;

import com.thiagos.familytree.model.dao.person.PersonDAO;
import com.thiagos.familytree.model.dao.relationship.RelationshipDAO;
import com.thiagos.familytree.model.dto.Person;
import com.thiagos.familytree.model.dto.Relationship;
import com.thiagos.familytree.util.FamilyNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * An Anhentafel Family tree starts at the current person and adds just their ancestors
 */
@Component
public class AhnentafelTreeBuilder implements TreeBuilder {

    private PersonDAO personDAO;
    private RelationshipDAO relationshipDAO;

    @Autowired
    public AhnentafelTreeBuilder(PersonDAO personDAO, RelationshipDAO relationshipDAO) {
        this.personDAO = personDAO;
        this.relationshipDAO = relationshipDAO;
    }

    public FamilyNode buildTree(Person person) {

        // root is the current person
        FamilyNode root = new FamilyNode(person.getName(), person.getPersonId());

        // find parents from DB and add recursively
        Relationship parents = relationshipDAO.findByPersonId(person.getPersonId());

        if (parents != null) {
            Person father = personDAO.findByPersonId(parents.getFatherId());
            root.setFather(buildTree(father));
            Person mother = personDAO.findByPersonId(parents.getMotherId());
            root.setMother(buildTree(mother));
        }

        return root;
    }

}
