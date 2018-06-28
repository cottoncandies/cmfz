package com.baizhi.service;

import com.alibaba.fastjson.JSONObject;
import com.baizhi.dao.AlbumDAO;
import com.baizhi.entity.Album;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.List;
@Service
@Transactional
public class AlbumServiceImpl implements AlbumService {

    @Resource
    private AlbumDAO albumDAO;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<Album> queryAll() {
        return albumDAO.queryAll();
    }

    @Override
    public JSONObject queryAlbumById(String id) {

        Album album = albumDAO.queryAlbumById(id);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("thumbnail",album.getImg());
        jsonObject.put("title",album.getName());
        jsonObject.put("score",album.getScore());
        jsonObject.put("author",album.getAuthor());
        jsonObject.put("broadcast",album.getBroadCast());
        jsonObject.put("set_count",album.getCount());
        jsonObject.put("brief",album.getBrief());
        jsonObject.put("create_date",new SimpleDateFormat("yyyy-MM-dd").format(album.getCreateDate()));
        return jsonObject;
    }
}
