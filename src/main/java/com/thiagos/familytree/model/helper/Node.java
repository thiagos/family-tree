package com.thiagos.familytree.model.helper;

import java.util.ArrayList;
import java.util.List;

/**
 * A basic Node class
 */
public class Node {
    private String data;
    private List<Node> children;

    public Node(String data) {
        this.data = data;
        this.children = new ArrayList<Node>();
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public List<Node> getChildren() {
        return children;
    }

    public void setChildren(List<Node> children) {
        this.children = children;
    }

    public void addChild(Node node) {
        children.add(node);
    }

}
