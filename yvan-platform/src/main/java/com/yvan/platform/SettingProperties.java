package com.yvan.platform;

import java.util.List;

/**
 * setting 设置
 * Created by luoyifan on 2017/8/8.
 */
public class SettingProperties {
    private String url;
    private List<String> imports;
    private String json;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<String> getImports() {
        return imports;
    }

    public void setImports(List<String> imports) {
        this.imports = imports;
    }

    public String getJson() {
        return json;
    }

    public void setJson(String json) {
        this.json = json;
    }
}
