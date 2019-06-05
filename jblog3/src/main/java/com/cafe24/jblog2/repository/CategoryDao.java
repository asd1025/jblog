package com.cafe24.jblog2.repository;

import java.util.List;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cafe24.jblog2.vo.CategoryVo;
import com.cafe24.jblog2.vo.UsersVo;

@Repository
public class CategoryDao {
	
	@Autowired
	private DataSource dataSource;
	@Autowired
	private SqlSession sqlSession;


	public UsersVo getUser(CategoryVo vo) {
		return sqlSession.selectOne("category.get",vo);
	}

	public void insert(String blog_id) {
		sqlSession.insert("category.insert",blog_id);
	}

	public List<CategoryVo> getCategoryList(String blogId) {
		return sqlSession.selectList("category.getList",blogId);
	}

	public void  insertCategory(CategoryVo vo) {
		sqlSession.insert("category.insertCategory",vo);
	}

	public void deleteCategory(int no) {
		sqlSession.delete("category.deleteCategory",no);
	}

}
