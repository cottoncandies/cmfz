package com.baizhi.dao;

import com.baizhi.entity.Chapter;

import java.util.List;

public interface ChapterDAO {

    void save(Chapter chapter);

    List<Chapter> queryChapterByParentId(String parentId);

}
