package com.baizhi.dao;

import com.baizhi.entity.User;

import java.util.List;

public interface UserDAO {

    List<User> queryAll();

    void save(User user);

    List<User> queryDistribution(String gender);
}
