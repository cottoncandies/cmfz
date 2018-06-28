package com.baizhi;

import com.baizhi.dao.*;
import com.baizhi.entity.*;
import com.baizhi.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class AppTest {
    @Resource
    private MenuDAO menuDAO;

    @Resource
    private AdminDAO adminDAO;

    @Resource
    private TitlePicDAO titlePicDAO;

    @Resource
    private ChapterDAO chapterDAO;

    @Resource
    private AlbumDAO albumDAO;

    @Resource
    private UserService userService;


    /**
     * 查询所有菜单
     */
    @Test
    public void testApp() {
        List<Menu> menus = menuDAO.queryMenus();
        for (Menu menu : menus) {
            System.out.println(menu);
        }
    }

    /**
     * 登录验证
     */
    @Test
    public void test() {
        System.out.println(adminDAO.queryByName("root"));
    }


    /**
     * 修改轮播图信息
     */
    @Test
    public void test1() {
        TitlePic titlePic = new TitlePic();
        titlePic.setId("1");
        titlePic.setStatus("y");
        titlePicDAO.modify(titlePic);
    }

    /**
     * 测试Long类型的章节大小添加到数据库为double的size字段成功与否
     */
    @Test
    public void test2() {
        Chapter chapter = new Chapter();
        chapter.setId("2");
        chapterDAO.save(chapter);
    }

    /**
     * 查询所有专辑
     */
    @Test
    public void test3() {
        List<Album> albums = albumDAO.queryAll();
        for (Album album : albums) {
            System.out.println(album);
        }
    }

    /**
     * 查询所有注册用户
     */
    @Test
    public void test4() {
        List<User> users = userService.queryAll();
        for (User user : users) {
            System.out.println(user);
        }
    }
}
