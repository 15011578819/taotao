package com.taotao.rest.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by Administrator on 2017-9-6.
 */
public class CatNode {

    @JsonProperty("u")
    private String url;
    @JsonProperty("n")
    private String name;
    @JsonProperty("i")
    private String item;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }
}
