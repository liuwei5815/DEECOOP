package com.yvan;

import java.util.ArrayList;

public class Tree<T> {

    private ArrayList<TreeNode<T>> roots = new ArrayList<>();

    public ArrayList<TreeNode<T>> getRoots() {
        return roots;
    }

    public void setRoots(ArrayList<TreeNode<T>> roots) {
        this.roots = roots;
    }
}
