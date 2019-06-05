package com.cafe24.jblog2.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cafe24.jblog2.repository.PostDao;
import com.cafe24.jblog2.vo.PostVo;

@Service
public class PostService {
	
	@Autowired
	private PostDao postDao;

	
	/* 	public List<PostVo> getPostList(String id, Optional<Integer> pathNo2, Optional<Integer> pathNo3) {
		String blogId=id;
		int categoryNo=-1;
		int postNo=-1;
 		if(pathNo2.isPresent()) categoryNo= (pathNo2.get());
 		if(pathNo3.isPresent()) postNo= (pathNo3.get());
 		List<PostVo> list=null;
 		System.out.println("**************postService getPostList "+blogId+"   "+categoryNo+"  "+postNo);
 		//if(categoryNo!=-1) { 
 			// 카테고리 번호가 정해져 있음. 글번호가  없다면 해당  카테고리 내의 전체글이 나온다
 			list=postDao.getRecentPostListByCategory(blogId,categoryNo);
 			
 		//}else { // 카테고리번호가 없어  전체글로 보여주기 
 		//	list=postDao.getPostListAll(id);
 		//	System.out.println("**************postService getPostLis 카카테고리번호가 없는 경우 ( 포스트 번호는 상관 없다 ) ");
 			for (PostVo postVo : list) {
				System.out.println(postVo);
			}
 		//}
		return list;

	}

	public List<PostVo> getPostList(String id) {
		List<PostVo> list=postDao.getRecentPostListByCategory(id,-1);
		return list;
	}


	public PostVo getPostByNo(int no,String blog_id) {
		return postDao.getPostByNo(no,blog_id);
	}

*/

	public void deleteAllPostByCategoryNo(int no) {
		postDao.deleteAllPostByCategoryNo(no);
	}



	public void writePost(PostVo postVo) {
		postDao.writePost(postVo);

	}

	public List<PostVo> getRecentlyAllList(String id) {
		return postDao.getRecentlyAllList(id);
	}

	public List<PostVo> getAllListByCategory(String id, Optional<Integer> categoryNo) {
		return postDao.getAllListByCategory(id,categoryNo.get());
	}

	public  PostVo getPostByNo(String id,int categoryNO, int postNO) {
		return   postDao.getPostByNo(id,categoryNO,postNO);
	}



}
