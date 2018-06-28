package com.baizhi.service;

import com.alibaba.fastjson.JSONObject;
import com.baizhi.entity.Album;

import java.util.List;

public interface AlbumService {

    List<Album> queryAll();

    JSONObject queryAlbumById(String id);

}
