package com.thiagos.familytree.model.request;

import com.thiagos.familytree.model.dto.FamilyTreeType;

public class GetFamilyTreeRequest {
    private Long personId;
    private FamilyTreeType familyTreeType;


    public Long getPersonId() {
        return personId;
    }

    public void setPersonId(Long personId) {
        this.personId = personId;
    }

    public FamilyTreeType getFamilyTreeType() {
        return familyTreeType;
    }

    public void setFamilyTreeType(FamilyTreeType familyTreeType) {
        this.familyTreeType = familyTreeType;
    }
}
