package com.baizhi.controller;

import com.alibaba.fastjson.JSONObject;
import com.baizhi.entity.Album;
import com.baizhi.service.AlbumService;
import com.baizhi.service.ChapterService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/album")
public class AlbumController {

    @Resource
    private AlbumService albumService;

    @Resource
    private ChapterService chapterService;

    @RequestMapping("/queryAll")
    public List<Album> queryAll() {
        return albumService.queryAll();
    }


    @RequestMapping("/queryAlbumById")
    //接口文档
    //id:专辑id       uid:用户id
    public JSONObject queryAlbumById(String id, String uid) {
        JSONObject jsonObject = new JSONObject();
        if (uid != "" && uid != null) {
            JSONObject albumjsonObject = albumService.queryAlbumById(id);
            List<Object> chapters = chapterService.queryChapterByParentId(id);
            jsonObject.put("detailDisplay",albumjsonObject);
            jsonObject.put("list",chapters);
            return jsonObject;
        }
        return null;
    }
}
