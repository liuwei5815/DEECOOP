package com.yvan;

import java.util.ArrayList;

public class TreeNode<T> {
    private T node;
    private ArrayList<TreeNode<T>> childs = new ArrayList<>();

    public T getNode() {
        return node;
    }

    public void setNode(T node) {
        this.node = node;
    }

    public ArrayList<TreeNode<T>> getChilds() {
        return childs;
    }

    public void setChilds(ArrayList<TreeNode<T>> childs) {
        this.childs = childs;
    }
}
