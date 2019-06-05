package com.cafe24.jblog2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cafe24.jblog2.repository.BlogDao;
import com.cafe24.jblog2.repository.CategoryDao;
import com.cafe24.jblog2.repository.UsersDao;
import com.cafe24.jblog2.vo.CategoryVo;
import com.cafe24.jblog2.vo.UsersVo;


@Service
public class UsersService {
	@Autowired
	private UsersDao usersDao;
	@Autowired
	private BlogDao blogDao;
	@Autowired
	private CategoryDao categoryDao;
	
	 
	public boolean existId(UsersVo usersVo) {
		//if("assets".equals(usersVo.getId())) return true;
		//if("admin".equals(usersVo.getId())) return true;
		//if("users".equals(usersVo.getId())) return true;
		return usersDao.existId(usersVo);
	}

	public void join(UsersVo vo) {
		  usersDao.insert(vo);
		  blogDao.insert(vo.getId());
		  categoryDao.insert(vo.getId());
	}

	public UsersVo getUser(UsersVo usersVo) {
		return usersDao.getUser(usersVo);
	}
	

}
