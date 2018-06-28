package com.baizhi.entity;

import java.io.Serializable;
import java.util.List;

public class Menu implements Serializable {
    private String id;
    private String name;
    private String iconCls;
    private String href;
    private List<Menu> childrenMenu;

    @Override
    public String toString() {
        return "Menu{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", iconCls='" + iconCls + '\'' +
                ", href='" + href + '\'' +
                ", childrenMenu=" + childrenMenu +
                '}';
    }

    public Menu() {
    }

    public Menu(String id, String name, String iconCls, String href, List<Menu> childrenMenu) {

        this.id = id;
        this.name = name;
        this.iconCls = iconCls;
        this.href = href;
        this.childrenMenu = childrenMenu;
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

    public String getIconCls() {
        return iconCls;
    }

    public void setIconCls(String iconCls) {
        this.iconCls = iconCls;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public List<Menu> getChildrenMenu() {
        return childrenMenu;
    }

    public void setChildrenMenu(List<Menu> childrenMenu) {
        this.childrenMenu = childrenMenu;
    }
}
