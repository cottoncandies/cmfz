package com.baizhi.dao;

import com.baizhi.entity.Album;

import java.util.List;

public interface AlbumDAO {

    List<Album> queryAll();

    Album queryAlbumById(String id);

}
