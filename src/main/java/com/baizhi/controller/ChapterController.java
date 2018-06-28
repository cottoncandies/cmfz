package com.baizhi.controller;

import com.baizhi.entity.Chapter;
import com.baizhi.service.ChapterService;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/chapter")
public class ChapterController {
    @Resource
    private ChapterService chapterService;

    @RequestMapping("/save")
    public Map<String, Object> saveChapter(MultipartFile audio, String id, HttpServletRequest request) {
        Map<String, Object> map = new HashMap<String, Object>();
        Chapter chapter = new Chapter();
        chapter.setParentId(id);

        //获取文件存储的位置
        String projectPath = request.getSession().getServletContext().getRealPath("/");
        String audioPath = projectPath + "audio";
        File uploadAudio = new File(audioPath);
        if (!uploadAudio.exists()) {
            uploadAudio.mkdir();
        }
        //重命名
        String originalFilename = audio.getOriginalFilename();

        String extension = FilenameUtils.getExtension(originalFilename);

        String newName = UUID.randomUUID().toString() + "." + extension;

        try {
            //上传到对应文件夹
            audio.transferTo(new File(audioPath, newName));

            //获取时长 大小 原始文件名  url  专辑的id 保存到数据库
            chapter.setName(originalFilename);
            String size = "" + (audio.getSize()) / (1024.0 * 1024.0) + "MB";
            chapter.setSize(size);
            chapter.setUrl(newName);
            chapter.setParentId(id);
            chapterService.save(chapter);
            map.put("success", true);
            map.put("message", "上传音频成功");
        } catch (Exception e) {
            map.put("success", false);
            map.put("message", "上传音频失败");
        }
        return map;
    }

    @RequestMapping("/download") //b04e9c3f-2bf1-4ecd-992c-a37ee04d1c1f.mp3
    public void down(String url, String oldName, HttpServletRequest request, HttpServletResponse response) throws IOException {
        //1.找到对应的下载文件
        String filePath = request.getSession().getServletContext().getRealPath("audio") + "/" + url;
        File file = new File(filePath);
        try {
            oldName = new String(oldName.getBytes("UTF-8"), "ISO-8859-1");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        //2.设置响应头  响应类型
        response.setContentType("audio/mpeg");
        response.setHeader("Content-Disposition", "attachment;fileName=" + oldName);

        //3.响应流响应出去
        ServletOutputStream outputStream = response.getOutputStream();
        outputStream.write(FileUtils.readFileToByteArray(file));

    }
}

