package com.thiagos.familytree.util;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.ArrayList;
import java.util.List;

/**
 * A basic FamilyNode class
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FamilyNode {
    private String name;
    private Long personId;
    private Gender gender;
    private List<FamilyNode> children;
    private FamilyNode father;
    private FamilyNode mother;
    private FamilyNode spouse;
    private String relationToMain;

    public FamilyNode(String name, Long personId, Gender gender) {
        this.name = name;
        this.personId = personId;
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getPersonId() {
        return personId;
    }

    public void setPersonId(Long personId) {
        this.personId = personId;
    }

    public List<FamilyNode> getChildren() {
        return children;
    }

    public void setChildren(List<FamilyNode> children) {
        this.children = children;
    }

    public void addChild(FamilyNode familyNode) {
        if (children == null) children = new ArrayList<>();
        this.children.add(familyNode);
    }

    public FamilyNode getFather() {
        return father;
    }

    public void setFather(FamilyNode father) {
        this.father = father;
    }

    public FamilyNode getMother() {
        return mother;
    }

    public void setMother(FamilyNode mother) {
        this.mother = mother;
    }

    public FamilyNode getSpouse() {
        return spouse;
    }

    public void setSpouse(FamilyNode spouse) {
        this.spouse = spouse;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getRelationToMain() {
        return relationToMain;
    }

    public void setRelationToMain(String relationToMain) {
        this.relationToMain = relationToMain;
    }
}
