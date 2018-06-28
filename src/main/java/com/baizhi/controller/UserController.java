package com.baizhi.controller;


import com.alibaba.fastjson.JSONObject;
import com.baizhi.entity.User;
import com.baizhi.service.UserService;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    @RequestMapping("/queryAll")
    public List<User> queryAll() {
        return userService.queryAll();
    }

    @RequestMapping("/distribution")
    public List<Object> queryDistribution(String gender) {
        List<Object> users = userService.queryDistribution(gender);
        return users;
    }

    @ResponseBody
    @RequestMapping("/import")
    public Map<String, Object> improtUser(MultipartFile UserDataFile) throws ParseException {

        Map<String, Object> map = new HashMap<String, Object>();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日");

        try {
            Workbook workbook = new HSSFWorkbook(UserDataFile.getInputStream());
            Sheet sheet = workbook.getSheet("user");
            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                String farmington = row.getCell(1).getStringCellValue();
                String nickname = row.getCell(2).getStringCellValue();
                String gender = row.getCell(3).getStringCellValue();
                String photo = row.getCell(4).getStringCellValue();
                String location = row.getCell(5).getStringCellValue();
                String province = row.getCell(6).getStringCellValue();
                String city = row.getCell(7).getStringCellValue();
                String description = row.getCell(8).getStringCellValue();
                String phone = row.getCell(9).getStringCellValue();
                String shangshi = row.getCell(10).getStringCellValue();
                String createDate = row.getCell(11).getStringCellValue();
                String password = row.getCell(12).getStringCellValue();
                User user = new User(null, nickname, password, farmington, gender, photo, location, province, city, description, phone, shangshi, simpleDateFormat.parse(createDate));
                //保存到数据库
                userService.save(user);
                map.put("success", true);
                map.put("message", "导入数据成功");
            }
        } catch (IOException e) {
            e.printStackTrace();
            map.put("success", false);
            map.put("message", "导入数据失败");
        }
        return map;
    }

    /*自定义用户数据导出*/
    @RequestMapping(value = "/customeExport", method = RequestMethod.POST)
    public void customeExport(String titles, String fields, HttpServletResponse response) {
        Workbook workbook = new HSSFWorkbook();
        Sheet sheet = workbook.createSheet("user");
        //创建标题行
        Row titleRow = sheet.createRow(0);
        //中文标题行处理
        String[] titleNames = titles.split(",");
        for (int i = 0; i < titleNames.length; i++) {
            titleRow.createCell(i).setCellValue(titleNames[i]);
        }
        //实体属性
        String[] fieldNames = fields.split(",");
        //数据库获取数据
        List<User> users = userService.queryAll();
        //数据行处理
        for (int i = 0; i < users.size(); i++) {
            Row dataRow = sheet.createRow(i + 1);
            User user = users.get(i);
            Class<? extends User> userClass = user.getClass();
            for (int j = 0; j < fieldNames.length; j++) {
                //  id   getId
                String methodName = "get" + fieldNames[j].substring(0, 1).toUpperCase() + fieldNames[j].substring(1);
                try {
                    Object value = userClass.getDeclaredMethod(methodName, null).invoke(user, null);
                    //日期格式处理
                    if (value instanceof Date) {
                        dataRow.createCell(j).setCellValue(new SimpleDateFormat("yyyy年MM月dd日").format(value));
                    } else {
                        dataRow.createCell(j).setCellValue(value.toString());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        }

        //直接导出到磁盘文件下
        //workbook.write(new FileOutputStream(new File("e:/user.xls")));

        //在浏览器下载文件，并选择磁盘位置保存
        try {
            String fileName = "导出用户数据文档.xls";
            String newName = new String(fileName.getBytes("UTF-8"), "ISO-8859-1");
            response.setHeader("Content-Disposition", "attachment;fileName=" + newName);
            response.setContentType("application/vnd.ms-excel");
            workbook.write(response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("activeUser")
    public JSONObject activeUser() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("intervals", new String[]{"7天", "15天"});
        jsonObject.put("counts", new int[]{5, 10});
        return jsonObject;
    }
}
