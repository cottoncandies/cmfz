package com.baizhi.service;

import com.baizhi.entity.User;

import java.util.List;

public interface UserService {
    List<User> queryAll();

    void save(User user);

    List<Object> queryDistribution(String gender);
}
