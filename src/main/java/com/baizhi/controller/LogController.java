package com.baizhi.controller;

import com.baizhi.entity.Log;
import com.baizhi.service.LogService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/log")
public class LogController {

	@Resource
	private LogService logService;

	@ResponseBody
	@RequestMapping("/queryAll")
	public Map<String,Object> queryAll(Integer page,Integer rows){
		Map<String,Object>result=new HashMap<String,Object>();
		List<Log> logs = logService.queryAll(page, rows);
		Long count = logService.queryCount();
		result.put("total", count);
		result.put("rows", logs);
		return result;
	}
}
