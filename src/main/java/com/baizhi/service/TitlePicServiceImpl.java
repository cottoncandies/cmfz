package com.baizhi.service;

import com.baizhi.dao.TitlePicDAO;
import com.baizhi.entity.TitlePic;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class TitlePicServiceImpl implements TitlePicService {

    @Resource
    private TitlePicDAO titlePicDAO;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<TitlePic> queryAll() {
        return titlePicDAO.queryAll();
    }

    @Override
    public List<TitlePic> queryByPage(Integer page, Integer rows) {
        return titlePicDAO.queryByPage((page-1)*rows,rows);
    }

    @Override
    public Long queryCount() {
        return titlePicDAO.queryCount();
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS )
    public TitlePic queryOne(String id) {
        return titlePicDAO.queryOne(id);
    }

    @Override
    public void save(TitlePic titlePic) {
        titlePic.setId(UUID.randomUUID().toString());
        titlePic.setCreateDate(new Date());
        titlePicDAO.save(titlePic);
    }

    @Override
    public void remove(String id) {
        titlePicDAO.remove(id);
    }

    @Override
    public void modify(TitlePic titlePic) {
        titlePicDAO.modify(titlePic);
    }
}
