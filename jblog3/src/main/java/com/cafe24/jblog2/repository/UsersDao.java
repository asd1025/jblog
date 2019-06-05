package com.cafe24.jblog2.repository;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cafe24.jblog2.vo.UsersVo;

@Repository
public class UsersDao {
	
	@Autowired
	private DataSource dataSource;
	@Autowired
	private SqlSession sqlSession;


	public UsersVo getUser(UsersVo vo) {
		return sqlSession.selectOne("users.get",vo);
	}

	public void insert(UsersVo vo) {
		sqlSession.insert("users.insert",vo);
	}

	public boolean existId(UsersVo vo) {
		
		return (sqlSession.selectOne("users.existId",vo)==null)?false:true;
	}

}
