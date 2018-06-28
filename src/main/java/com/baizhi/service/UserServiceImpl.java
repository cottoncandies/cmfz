package com.baizhi.service;

import com.alibaba.fastjson.JSONObject;
import com.baizhi.dao.UserDAO;
import com.baizhi.entity.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Resource
    private UserDAO userDAO;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<User> queryAll() {
        return userDAO.queryAll();
    }

    @Override
    public void save(User user) {
        user.setId(UUID.randomUUID().toString().replace("-",""));
        userDAO.save(user);
    }

    @Override
    public List<Object> queryDistribution(String gender) {
        List<Object> list = new ArrayList<Object>();
        List<User> users = userDAO.queryDistribution(gender);
        for (User user : users) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("name",user.getProvince());
            jsonObject.put("value",user.getId());
            list.add(jsonObject);
        }
        return list ;
    }
}
