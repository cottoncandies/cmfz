package com.baizhi.service;

import com.alibaba.fastjson.JSONObject;
import com.baizhi.dao.ChapterDAO;
import com.baizhi.entity.Chapter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class ChapterServiceImpl implements ChapterService {

    @Resource
    private ChapterDAO chapterDAO;

    @Override
    public void save(Chapter chapter) {
        chapter.setId(UUID.randomUUID().toString().replace("-", ""));
        chapterDAO.save(chapter);
    }

    @Override
    public List<Object> queryChapterByParentId(String parentId) {
        List<Object> list = new ArrayList<Object>();
        List<Chapter> chapters = chapterDAO.queryChapterByParentId(parentId);
        for (Chapter chapter : chapters) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("title", chapter.getName());
            jsonObject.put("download_url", chapter.getUrl());
            jsonObject.put("size", chapter.getSize());
            jsonObject.put("duration", chapter.getTime());
            list.add(jsonObject);
        }
        return list;
    }
}
