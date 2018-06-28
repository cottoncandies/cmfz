package com.baizhi.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

public class TitlePic implements Serializable {
    private String id;
    private String name;
    private String desc;
    //图片上传和下载的路径
    private String path;
    private String status;
    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createDate;

    @Override
    public String toString() {
        return "TitlePic{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", desc='" + desc + '\'' +
                ", path='" + path + '\'' +
                ", status='" + status + '\'' +
                ", createDate=" + createDate +
                '}';
    }

    public TitlePic() {
    }

    public TitlePic(String id, String name, String desc, String path, String status, Date createDate) {
        this.id = id;
        this.name = name;
        this.desc = desc;
        this.path = path;
        this.status = status;
        this.createDate = createDate;
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

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}
