package com.baizhi.service;


import com.baizhi.entity.Chapter;

import java.util.List;

public interface ChapterService {

    void save(Chapter chapter);

    List<Object> queryChapterByParentId(String parentId);
}
