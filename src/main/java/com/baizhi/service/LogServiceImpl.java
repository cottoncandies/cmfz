package com.baizhi.service;

import com.baizhi.dao.LogDAO;
import com.baizhi.entity.Log;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional
public class LogServiceImpl implements LogService {
	@Resource
	private LogDAO logDAO;

	@Override
	public Long queryCount() {
		return logDAO.queryCount();
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public List<Log> queryAll(Integer page, Integer rows) {
		return logDAO.queryAll((page-1)*rows,rows );
	}
}
