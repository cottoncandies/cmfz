package com.baizhi.dao;

import com.baizhi.entity.TitlePic;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TitlePicDAO {
    List<TitlePic> queryAll();

    List<TitlePic> queryByPage(@Param("start") Integer start, @Param("rows") Integer rows);

    Long queryCount();

    TitlePic queryOne(String id);

    void save(TitlePic titlePic);

    void remove(String id);

    void modify(TitlePic titlePic);
}
