package com.baizhi.dao;

import com.baizhi.entity.Admin;

public interface AdminDAO {
    Admin queryByName(String name);
}
