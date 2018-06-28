package com.baizhi.controller;

import com.baizhi.entity.TitlePic;
import com.baizhi.service.TitlePicService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/pic")
public class TitlePicController {

    @Resource
    private TitlePicService titlePicService;

    @RequestMapping("/queryAll")
    public Map<String,Object> queryAll(Integer page,Integer rows){
        Map<String,Object> map = new HashMap<String, Object>();
        List<TitlePic> titlePics = titlePicService.queryByPage(page, rows);
        Long total = titlePicService.queryCount();
        map.put("total",total);
        map.put("rows",titlePics);
        return map;
    }

    @RequestMapping("/save")
    @ResponseBody
    public Map<String,Object> save(MultipartFile file, HttpServletRequest request,TitlePic titlePic){
        Map<String,Object> map = new HashMap<String, Object>();
        try{
            String realPath = request.getSession().getServletContext().getRealPath("/titlepic");

            //上传文件名字
            String originalFilename = file.getOriginalFilename();

            file.transferTo(new File(realPath,originalFilename));

            titlePic.setName(originalFilename);
            titlePic.setPath("http://localhost:8989/cmfz/titlepic/"+originalFilename);
            titlePicService.save(titlePic);

            map.put("success",true);
            map.put("message","添加成功");
        }catch (Exception e){
            map.put("success",false);
            map.put("message","添加失败");
        }
        return map;
    }

    @RequestMapping("/remove")
    @ResponseBody
    public Map<String,Object> remove(String id){
        Map<String,Object> map = new HashMap<String, Object>();
        try{
            titlePicService.remove(id);
            map.put("success",true);
            map.put("message","删除成功");
        }catch (Exception e){
            map.put("success",false);
            map.put("message","删除失败");
        }
        return map;
    }

    @RequestMapping("/modify")
    @ResponseBody
    public Map<String,Object> modify(TitlePic titlePic){
        Map<String,Object> map = new HashMap<String, Object>();
        try{
            titlePicService.modify(titlePic);
            map.put("success",true);
            map.put("message","修改成功");
        }catch (Exception e){
            map.put("success",false);
            map.put("message","修改失败");
        }
        return map;
    }
}
