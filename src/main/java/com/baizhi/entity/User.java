package com.baizhi.entity;

import com.baizhi.annotation.FieldName;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

public class User implements Serializable {
    @FieldName("编号")
    private String id;
    @FieldName("昵称")
    private String nickname;
    @FieldName("密码")
    private String password;
    @FieldName("法名")
    private String farmington;
    @FieldName("性别")
    private String gender;
    @FieldName("头像")
    private String photo;
    @FieldName("所在地")
    private String location;
    @FieldName("省份")
    private String province;
    @FieldName("城市")
    private String city;
    @FieldName("个人签名")
    private String description;
    @FieldName("电话")
    private String phone;
    @FieldName("上师")
    private String shangshi;
    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @FieldName("注册时间")
    private Date createDate;

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", nickname='" + nickname + '\'' +
                ", password='" + password + '\'' +
                ", farmington='" + farmington + '\'' +
                ", gender='" + gender + '\'' +
                ", photo='" + photo + '\'' +
                ", location='" + location + '\'' +
                ", province='" + province + '\'' +
                ", city='" + city + '\'' +
                ", description='" + description + '\'' +
                ", phone='" + phone + '\'' +
                ", shangshi='" + shangshi + '\'' +
                ", createDate=" + createDate +
                '}';
    }

    public User() {
    }

    public User(String id, String nickname, String password, String farmington, String gender, String photo, String location, String province, String city, String description, String phone, String shangshi, Date createDate) {

        this.id = id;
        this.nickname = nickname;
        this.password = password;
        this.farmington = farmington;
        this.gender = gender;
        this.photo = photo;
        this.location = location;
        this.province = province;
        this.city = city;
        this.description = description;
        this.phone = phone;
        this.shangshi = shangshi;
        this.createDate = createDate;
    }

    public String getId() {

        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFarmington() {
        return farmington;
    }

    public void setFarmington(String farmington) {
        this.farmington = farmington;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getShangshi() {
        return shangshi;
    }

    public void setShangshi(String shangshi) {
        this.shangshi = shangshi;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}
