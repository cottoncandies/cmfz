package com.baizhi.service;


import com.baizhi.entity.Log;

import java.util.List;

public interface LogService {


	public List<Log> queryAll(Integer page, Integer rows);


	public Long queryCount();
}
