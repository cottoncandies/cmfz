package com.baizhi.service;

import com.baizhi.dao.AdminDAO;
import com.baizhi.entity.Admin;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
@Transactional
public class AdminServiceImpl implements AdminService {

    @Resource
    private AdminDAO adminDAO;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public Admin login(String name, String password) {
        try {
            Admin  admin= adminDAO.queryByName(name);
            if(admin==null)throw new RuntimeException("用户名不存在");
            if(!admin.getPassword().equals(password)) throw new RuntimeException("密码错误");
            return  admin;
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }
}
