package com.thiagos.familytree.service.treePrinter;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.thiagos.familytree.util.FamilyNode;

/**
 * Prints a tree in a JSON format
 */
public class JsonTreePrinter implements TreePrinter {

    private ObjectMapper mapper;
    public JsonTreePrinter() {
        this.mapper = new ObjectMapper();
    }

    /**
     * Nothing much to do, Spring will already deserialize the FamilyNode root to a JSON to send the API response
     * @param root
     * @return
     */
    public FamilyNode printTree(FamilyNode root) {
        return root;
    }
}
