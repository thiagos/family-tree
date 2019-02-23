package com.thiagos.familytree.model.request;

import com.thiagos.familytree.model.dto.Relationship;

import java.util.List;

public class AddRelationshipsRequest {
    List<Relationship> relationships;

    public List<Relationship> getRelationships() {
        return relationships;
    }

    public void setRelationships(List<Relationship> relationships) {
        this.relationships = relationships;
    }
}
