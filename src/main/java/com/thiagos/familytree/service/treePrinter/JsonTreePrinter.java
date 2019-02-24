package com.thiagos.familytree.service.treePrinter;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.thiagos.familytree.model.helper.Node;

import javax.swing.tree.TreeNode;

/**
 * Prints a tree in a JSON format
 */
public class JsonTreePrinter implements TreePrinter {

    private ObjectMapper mapper;
    public JsonTreePrinter() {
        this.mapper = new ObjectMapper();
    }

    public String printTree(Node root) {
        JsonNode node = mapper.valueToTree(root);
        return node.toString();
    }

    //public JsonNode convertTreeToJsonNode(Node root) {
    //}
}
