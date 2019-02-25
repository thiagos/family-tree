package com.thiagos.familytree.service.treePrinter;

import com.thiagos.familytree.model.dto.Ancestry;
import com.thiagos.familytree.model.dto.Gender;
import com.thiagos.familytree.util.FamilyNode;


public class TextTreePrinter implements TreePrinter {


    public String printTree(FamilyNode root) {
        return printTreeInternal(root, 0, null, null);
    }

    private String printTreeInternal(FamilyNode node, Integer level, Gender gender, Ancestry ancestry) {

        if (node == null) return "";

        String textTree = getRelation(level, gender, ancestry) + node.getName() + "\n";

        // TODO add children
        //String childrenText = "";


        level++;
        return textTree +
                printTreeInternal(node.getFather(), level, Gender.MALE, Ancestry.ANCESTOR) +
                printTreeInternal(node.getMother(), level, Gender.FEMALE, Ancestry.ANCESTOR);
    }

    private String getRelation(Integer level, Gender gender, Ancestry ancestry) {

        if (level == 0)
            return "self: ";

        String relation = "";
        if (ancestry.equals(Ancestry.ANCESTOR)) {
            relation = "father: ";
            if (gender.equals(Gender.FEMALE))
                relation = "mother: ";
        } else {
            relation = "child: ";
        }

        if (level > 1)
            relation = "grand" + relation;

        for (int i = 0; i < level - 2; i++)
            relation = "great-" + relation;

        return relation;
    }
}
