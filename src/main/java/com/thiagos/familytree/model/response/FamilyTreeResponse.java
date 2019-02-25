package com.thiagos.familytree.model.response;

import com.thiagos.familytree.util.FamilyNode;

public class FamilyTreeResponse extends GenericResponse {

    private FamilyNode familyNode;

    public FamilyNode getFamilyNode() {
        return familyNode;
    }

    public void setFamilyNode(FamilyNode familyNode) {
        this.familyNode = familyNode;
    }
}
