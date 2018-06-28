package com.baizhi.entity;

import java.io.Serializable;

public class Chapter implements Serializable {
    private String id;
    private String name;
    private String size;
    private String url;
    private String time;
    private String parentId;

    @Override
    public String toString() {
        return "Chapter{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", size='" + size + '\'' +
                ", url='" + url + '\'' +
                ", time='" + time + '\'' +
                ", parentId='" + parentId + '\'' +
                '}';
    }

    public Chapter() {
    }

    public Chapter(String id, String name, String size, String url, String time, String parentId) {

        this.id = id;
        this.name = name;
        this.size = size;
        this.url = url;
        this.time = time;
        this.parentId = parentId;
    }

    public String getId() {

        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }
}
