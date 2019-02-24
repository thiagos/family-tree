package com.thiagos.familytree.service.treeBuilder;

import com.thiagos.familytree.model.dao.person.PersonDAO;
import com.thiagos.familytree.model.dao.relationship.RelationshipDAO;
import com.thiagos.familytree.model.dto.Person;
import com.thiagos.familytree.model.dto.RelationType;
import com.thiagos.familytree.model.dto.Relationship;
import com.thiagos.familytree.model.helper.Node;
import org.springframework.beans.factory.annotation.Autowired;

import javax.swing.tree.TreeNode;
import java.util.List;


/**
 * An Anhentafel Family tree starts at the current person and adds just their ancestors
 */
public class AhnentafelTreeBuilder implements TreeBuilder {

    private Integer maxTreeSize;
    private PersonDAO personDAO;
    private RelationshipDAO relationshipDAO;

    @Autowired
    public AhnentafelTreeBuilder(PersonDAO personDAO, RelationshipDAO relationshipDAO) {
        this.personDAO = personDAO;
        this.relationshipDAO = relationshipDAO;
    }

    public Node buildTree(Person person) {
        Integer currentTreeSize = 0;

        // root is the current person
        Node root = new Node(person.getName());

        // find parents from DB and add recursively
        List<Relationship> parents = relationshipDAO.findByPersonIdAndRelationType(person.getPersonId(), RelationType.PARENT);

        for (Relationship parentRelationship: parents) {
            Person parent = personDAO.findByPersonId(parentRelationship.getRelativeId());
            root.addChild(buildTree(parent));
        }

        return root;
    }
}
