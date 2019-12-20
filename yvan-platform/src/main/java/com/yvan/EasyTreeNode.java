package com.yvan;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

/**
 * easyui 用的 treenode
 * Created by luoyifan on 2017/4/16.
 */
public class EasyTreeNode {

    public static final String STATE_OPEN = "open";
    public static final String STATE_CLOSE = "closed";

    private String id;
    private String text;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String state; //= STATE_CLOSE;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Boolean checked;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String attributes;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<EasyTreeNode> children;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Boolean getChecked() {
        return checked;
    }

    public void setChecked(Boolean checked) {
        this.checked = checked;
    }

    public String getAttributes() {
        return attributes;
    }

    public void setAttributes(String attributes) {
        this.attributes = attributes;
    }

    public List<EasyTreeNode> getChildren() {
        return children;
    }

    public void setChildren(List<EasyTreeNode> children) {
        this.children = children;
    }
}
