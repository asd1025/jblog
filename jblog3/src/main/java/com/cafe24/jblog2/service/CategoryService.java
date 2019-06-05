package com.cafe24.jblog2.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cafe24.jblog2.repository.CategoryDao;
import com.cafe24.jblog2.repository.PostDao;
import com.cafe24.jblog2.vo.CategoryVo;

@Service
public class CategoryService {
	
	@Autowired
	private CategoryDao categoryDao;
	
	
	public List<CategoryVo> getCategoryList(String blogId) {
		return categoryDao.getCategoryList(blogId);
	}
	public void insertCategory(CategoryVo vo) {
		  categoryDao.insertCategory(vo);
	}
	public void deleteCategory(int no) {
		categoryDao.deleteCategory(no);
	}

	 
}
