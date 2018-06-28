package com.baizhi.controller;

import com.baizhi.entity.Admin;
import com.baizhi.service.AdminService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Resource
    private AdminService adminService;

    @RequestMapping("/login")
    @ResponseBody
    public Map<String, Object> login(String name, String password, String enCode, HttpSession session) {
        Map<String, Object> result = new HashMap<String, Object>();
        if (!session.getAttribute("code").toString().equalsIgnoreCase(enCode)) {
            result.put("success", false);
            result.put("message", "验证码错误");
            return result;
        }
        try {
            Admin admin = adminService.login(name, password);
            session.setAttribute("admin", admin);
            result.put("success", true);
        } catch (Exception e) {
            result.put("success", false);
            result.put("message", e.getMessage());
        }

        return result;
    }

    @RequestMapping("/exit")
    public String exit(HttpSession session){
        session.removeAttribute("admin");
        session.invalidate();
        return "redirect:/login.jsp";
    }
}
