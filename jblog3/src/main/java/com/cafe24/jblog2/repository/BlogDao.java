package com.cafe24.jblog2.repository;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cafe24.jblog2.vo.BlogVo;
import com.cafe24.jblog2.vo.UsersVo;

@Repository
public class BlogDao {
	
	@Autowired
	private DataSource dataSource;
	@Autowired
	private SqlSession sqlSession;


	public void insert(String id) {
		sqlSession.insert("blog.insert",id);
	}

	public BlogVo getBlog(String id) {
		BlogVo vo=sqlSession.selectOne("blog.getById",id);
		return sqlSession.selectOne("blog.getById",id);
	}

	public void updateBlog(BlogVo blogVo) {
		sqlSession.update("blog.update",blogVo);
	}

}
