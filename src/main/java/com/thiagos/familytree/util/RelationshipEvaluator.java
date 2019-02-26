package com.thiagos.familytree.util;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class RelationshipEvaluator {


    private Map<Relationship, Map<Gender, String>> genderMap;

    public RelationshipEvaluator() {
        genderMap = buildGenderMap();
    }

    /**
     * Identify the relationship of each node to the "tree owner", based on the parentNode's relation to it
     * For instance: knowing the parentNode is the "tree owner"'s father, the parentNode's father will be "grandfather
     * @param parentNode
     * @return nothing, just sets relationToMain value into each FamilyNode instance associated with
     * parentNode (children, father, mother, spouse)
     */
    public void getRelationships(FamilyNode parentNode) {
        // TO DO
    }




    private boolean isAncestor(String relationToMain) {
        return relationToMain.endsWith(Relationship.FATHER.getValue()) || relationToMain.endsWith(Relationship.MOTHER.getValue());
    }

    private boolean isDescendant(String relationToMain) {
        return relationToMain.endsWith(Relationship.SON.getValue()) || relationToMain.endsWith(Relationship.DAUGHTER.getValue());
    }

    /**
     * helper method to not worry about gender when setting relationships
     * @return
     */
    private Map<Relationship, Map<Gender, String>> buildGenderMap() {
        Map<Gender, String> childMap = new HashMap<>();
        childMap.put(Gender.FEMALE, Relationship.DAUGHTER.getValue());
        childMap.put(Gender.MALE, Relationship.SON.getValue());

        Map<Gender, String> parentMap = new HashMap<>();
        parentMap.put(Gender.FEMALE, Relationship.MOTHER.getValue());
        parentMap.put(Gender.MALE, Relationship.FATHER.getValue());

        Map<Relationship, Map<Gender, String>> genderMap = new HashMap<>();
        genderMap.put(Relationship.CHILD, childMap);
        genderMap.put(Relationship.PARENT, parentMap);

        return genderMap;
    }

    /**
     * Method that returns the relationship (child, grandmother, great-grandfather, spouse, etc)
     * based on parameters below
     * @param level the "distance" in generations between the tree root and this node
     * @param ancestry ancestor or descendant
     * @return a string for the relationship
     */
    public static String getRelation(Integer level, Gender gender, Ancestry ancestry) {

        if (level == 0)
            return "self";

        String relation = "";
        if (ancestry.equals(Ancestry.ANCESTOR)) {
            relation = "father";
            if (gender.equals(Gender.FEMALE))
                relation = "mother";
        } else {
            relation = "son";
            if (gender.equals(Gender.FEMALE))
                relation = "daughter";
        }

        if (level > 1)
            relation = "grand" + relation;

        for (int i = 0; i < level - 2; i++)
            relation = "great-" + relation;

        return relation;
    }
}
