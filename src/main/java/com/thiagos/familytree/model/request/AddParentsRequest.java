package com.thiagos.familytree.model.request;

import com.thiagos.familytree.model.dto.Parent;

import java.util.List;

public class AddParentsRequest {
    List<Parent> parents;

    public List<Parent> getParents() {
        return parents;
    }

    public void setParents(List<Parent> parents) {
        this.parents = parents;
    }
}
