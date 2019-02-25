package com.thiagos.familytree.service.treeBuilder;


import com.thiagos.familytree.model.dao.person.PersonDAO;
import com.thiagos.familytree.model.dao.relationship.RelationshipDAO;
import com.thiagos.familytree.model.dto.Gender;
import com.thiagos.familytree.model.dto.Person;
import com.thiagos.familytree.model.dto.Relationship;
import com.thiagos.familytree.util.FamilyNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * This tree starts with the person and shows his partner and descendants
 */
@Component
public class DescendantsTreeBuilder implements TreeBuilder {

    private PersonDAO personDAO;
    private RelationshipDAO relationshipDAO;

    @Autowired
    public DescendantsTreeBuilder(PersonDAO personDAO, RelationshipDAO relationshipDAO) {
        this.personDAO = personDAO;
        this.relationshipDAO = relationshipDAO;
    }

    public FamilyNode buildTree(Person person) {

        FamilyNode root = new FamilyNode("Descendants Tree", 0l);

        FamilyNode thisPerson = buildTreeInternal(person);

        root.addChild(thisPerson);

        return root;
    }

    private FamilyNode buildTreeInternal(Person person) {
        FamilyNode thisPerson = new FamilyNode(person.getName(), person.getPersonId());
        List<Relationship> children = getChildren(person);

        if (!children.isEmpty()) {
            // find the spouse from the first child with bigger id (supposedly most recent), if many
            thisPerson.setSpouse(getSpouse(person, children));
            for (Relationship childRelation : children) {
                Person child = personDAO.findByPersonId(childRelation.getPersonId());
                thisPerson.addChild(buildTreeInternal(child));
            }
        }
        return thisPerson;
    }

    private List<Relationship> getChildren(Person person) {

        List<Relationship> children = new ArrayList<>();
        // based on gender, lookup by fatherId or motherId
        if (person.getGender().equals(Gender.FEMALE)) {
            children = relationshipDAO.findByMotherId(person.getPersonId());
        } else if (person.getGender().equals(Gender.MALE)) {
            children = relationshipDAO.findByFatherId(person.getPersonId());
        }
        return children;
    }

    private FamilyNode getSpouse(Person person, List<Relationship> children) {

        // loop through children to find the most recent one (bigger id)
        Relationship youngestChild = children.get(0);
        for (Relationship child : children) {
            if (child.getPersonId() > youngestChild.getPersonId()) {
                youngestChild = child;
            }
        }

        // use spouseId based on this last child
        Long spouseId;
        if (person.getGender().equals(Gender.FEMALE)) {
            spouseId = youngestChild.getFatherId();
        } else {
            spouseId = youngestChild.getMotherId();
        }
        Person spouse = personDAO.findByPersonId(spouseId);
        FamilyNode spouseNode = new FamilyNode(spouse.getName(), spouseId);

        // add all common children to spouse
        for (Relationship childRelation : children) {
            if (person.getGender().equals(Gender.FEMALE) && childRelation.getFatherId() == spouseId) {
                Person child = personDAO.findByPersonId(childRelation.getPersonId());
                spouseNode.addChild(new FamilyNode(child.getName(), child.getPersonId()));
            } else if (person.getGender().equals(Gender.MALE) && childRelation.getMotherId() == spouseId ){
                Person child = personDAO.findByPersonId(childRelation.getPersonId());
                spouseNode.addChild(new FamilyNode(child.getName(), child.getPersonId()));
            }

        }
        return spouseNode;
    }
}
