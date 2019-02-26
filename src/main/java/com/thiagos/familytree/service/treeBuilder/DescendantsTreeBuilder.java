package com.thiagos.familytree.service.treeBuilder;


import com.thiagos.familytree.model.dao.person.PersonDAO;
import com.thiagos.familytree.model.dao.parent.ParentDAO;
import com.thiagos.familytree.util.Gender;
import com.thiagos.familytree.model.dto.Parent;
import com.thiagos.familytree.model.dto.Person;
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
    private ParentDAO parentDAO;

    @Autowired
    public DescendantsTreeBuilder(PersonDAO personDAO, ParentDAO parentDAO) {
        this.personDAO = personDAO;
        this.parentDAO = parentDAO;
    }

    public FamilyNode buildTree(Person person) {
        return buildTreeInternal(person);
    }

    private FamilyNode buildTreeInternal(Person person) {
        FamilyNode thisPerson = new FamilyNode(person.getName(), person.getPersonId(), person.getGender());
        List<Parent> children = getChildren(person);

        if (!children.isEmpty()) {
            // find the spouse from the first child with bigger id (supposedly most recent), if many
            thisPerson.setSpouse(getSpouse(person, children));
            for (Parent childRelation : children) {
                Person child = personDAO.findByPersonId(childRelation.getPersonId());
                thisPerson.addChild(buildTreeInternal(child));
            }
        }
        return thisPerson;
    }

    private List<Parent> getChildren(Person person) {

        List<Parent> children = new ArrayList<>();
        // based on gender, lookup by fatherId or motherId
        if (person.getGender().equals(Gender.FEMALE)) {
            children = parentDAO.findByMotherId(person.getPersonId());
        } else if (person.getGender().equals(Gender.MALE)) {
            children = parentDAO.findByFatherId(person.getPersonId());
        }
        return children;
    }

    private FamilyNode getSpouse(Person person, List<Parent> children) {

        // loop through children to find the most recent one (bigger id)
        Parent youngestChild = children.get(0);
        for (Parent child : children) {
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
        FamilyNode spouseNode = new FamilyNode(spouse.getName(), spouseId, spouse.getGender());

        // add all common children to spouse
        for (Parent childRelation : children) {
            if (person.getGender().equals(Gender.FEMALE) && childRelation.getFatherId() == spouseId) {
                Person child = personDAO.findByPersonId(childRelation.getPersonId());
                spouseNode.addChild(new FamilyNode(child.getName(), child.getPersonId(), child.getGender()));
            } else if (person.getGender().equals(Gender.MALE) && childRelation.getMotherId() == spouseId ){
                Person child = personDAO.findByPersonId(childRelation.getPersonId());
                spouseNode.addChild(new FamilyNode(child.getName(), child.getPersonId(), child.getGender()));
            }

        }
        return spouseNode;
    }
}
