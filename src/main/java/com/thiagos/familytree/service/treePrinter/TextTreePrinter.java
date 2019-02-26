package com.thiagos.familytree.service.treePrinter;

import com.thiagos.familytree.util.Ancestry;
import com.thiagos.familytree.util.FamilyNode;
import com.thiagos.familytree.util.RelationshipEvaluator;

public class TextTreePrinter implements TreePrinter {

    public String printTree(FamilyNode root) {
        return printTreeInternal(root, 0, null);
    }

    private String printTreeInternal(FamilyNode node, Integer level, Ancestry ancestry) {

        if (node == null) return "";

        String selfDescription = RelationshipEvaluator.getRelation(level, node.getGender(), ancestry) + ": " + node.getName() + ", ";

        level++;

        // create a string recursively for all children, if any
        String childrenText = "";
        if ( node.getChildren() != null) {
            for (FamilyNode childNode : node.getChildren()) {
                childrenText += printTreeInternal(childNode, level, Ancestry.DESCENDANT);
            }
        }

        // add spouse, but no spouse's relatives for now
        String spouseText = "";
        if (node.getSpouse() != null) {
            spouseText = "with spouse: " + node.getSpouse().getName() + ", ";
        }

        // add the parents trees too
        return  selfDescription +
                spouseText +
                printTreeInternal(node.getFather(), level, Ancestry.ANCESTOR) +
                printTreeInternal(node.getMother(), level, Ancestry.ANCESTOR) +
                childrenText;
    }


}
