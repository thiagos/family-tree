package com.thiagos.familytree.util;

public enum Relationship {
    PARENT("parent"),
    CHILD("child"),
    SPOUSE("spouse"),
    FATHER("father"),
    MOTHER("mother"),
    SON("son"),
    DAUGHTER("daughter");

    private String value;
    Relationship(String stringValue) {
        this.value = stringValue;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
