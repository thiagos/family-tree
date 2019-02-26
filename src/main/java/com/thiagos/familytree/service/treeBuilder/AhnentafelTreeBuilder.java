package com.thiagos.familytree.service.treeBuilder;

import com.thiagos.familytree.model.dao.person.PersonDAO;
import com.thiagos.familytree.model.dao.parent.ParentDAO;
import com.thiagos.familytree.model.dto.Parent;
import com.thiagos.familytree.model.dto.Person;
import com.thiagos.familytree.util.FamilyNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * An Anhentafel Family tree starts at the current person and adds just their ancestors
 */
@Component
public class AhnentafelTreeBuilder implements TreeBuilder {

    private PersonDAO personDAO;
    private ParentDAO parentDAO;

    @Autowired
    public AhnentafelTreeBuilder(PersonDAO personDAO, ParentDAO parentDAO) {
        this.personDAO = personDAO;
        this.parentDAO = parentDAO;
    }

    public FamilyNode buildTree(Person person) {

        // root is the current person
        FamilyNode root = new FamilyNode(person.getName(), person.getPersonId(), person.getGender());

        // find parents from DB and add recursively
        Parent parents = parentDAO.findByPersonId(person.getPersonId());

        if (parents != null) {
            Person father = personDAO.findByPersonId(parents.getFatherId());
            root.setFather(buildTree(father));
            Person mother = personDAO.findByPersonId(parents.getMotherId());
            root.setMother(buildTree(mother));
        }

        return root;
    }

}
