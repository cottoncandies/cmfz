package com.baizhi.service;

import com.baizhi.entity.TitlePic;

import java.util.List;

public interface TitlePicService {
    List<TitlePic> queryAll();

    List<TitlePic> queryByPage(Integer page,Integer rows);

    Long queryCount();

    TitlePic queryOne(String id);

    void save(TitlePic titlePic);

    void remove(String id);

    void modify(TitlePic titlePic);
}
