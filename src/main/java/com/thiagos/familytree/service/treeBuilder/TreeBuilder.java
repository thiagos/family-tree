package com.thiagos.familytree.service.treeBuilder;

import com.thiagos.familytree.model.dto.Person;
import com.thiagos.familytree.model.helper.Node;

public interface TreeBuilder {

    public Node buildTree(Person person);
}
