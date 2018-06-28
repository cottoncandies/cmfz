package com.baizhi.dao;

import com.baizhi.entity.Log;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface LogDAO {

	public void add(Log log);


	public List<Log>queryAll(@Param("page") Integer page, @Param("rows") Integer rows);


	public Long queryCount();
}
