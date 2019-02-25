package com.thiagos.familytree.service.treeBuilder;

import com.thiagos.familytree.model.dto.Person;
import com.thiagos.familytree.util.FamilyNode;

public interface TreeBuilder {

    public FamilyNode buildTree(Person person);
}
