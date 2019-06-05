package com.cafe24.jblog2.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Repository;

import com.cafe24.jblog2.vo.PostVo;
import com.cafe24.jblog2.vo.UsersVo;

@Repository
public class PostDao {
	
	@Autowired
	private DataSource dataSource;
	@Autowired
	private SqlSession sqlSession;


	/*
	 * public void insert(PostVo vo) { sqlSession.insert("",vo); }
	 */
	
//	public PostVo getPostByPostNo(int no) {
//		return  sqlSession.selectOne("post.getPostByPostNo",no);
//	}
	/** 
	 *   최신글들을 보여주는 리스트  (전체 )
	 * */
	private Map<String, Object> map;
	
	public List<PostVo> getRecentlyAllList(String blogId) {
		  return  sqlSession.selectList("post.getRecentlyAllList",blogId);
	}
	
	/**
	 *  해당 글이 속한  카테고리의 글들을 보여주는 리스트
	 * */
	public List<PostVo> getAllListByCategory(String blog_id,int category_no) {
		map=new HashMap<String, Object>();
		  map.put("blog_id", blog_id);
		  map.put("category_no", category_no );
		  return  sqlSession.selectList("post.getAllListByCategory",map);
	}
	/**
	 *  해당 카테고리에 있는 존재하는 포스트라면 보여주기
	 * */
	public PostVo getPostByNo(String blog_id,int category_no ,int no) {
		map=new HashMap<String, Object>();
		  map.put("blog_id", blog_id);
		  map.put("no", no );
		  map.put("category_no", category_no );
		return sqlSession.selectOne("post.getPostByNo",map);
	}

	public void deleteAllPostByCategoryNo(int no) {
			sqlSession.delete("post.deleteAllPostByCategoryNo",no);
	}

	public void writePost(PostVo postVo) {
		sqlSession.insert("post.writePost",postVo);
		
	}

	 
}
