package com.baizhi.service;

import com.baizhi.dao.MenuDAO;
import com.baizhi.entity.Menu;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional
public class MenuServiceImpl implements MenuService {
    @Resource
    private MenuDAO menuDAO;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<Menu> queryMenus() {
        return menuDAO.queryMenus();
    }
}
