package com.cafe24.jblog2.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.cafe24.jblog2.repository.BlogDao;
import com.cafe24.jblog2.vo.BlogVo;

@Service
public class BlogService {

	@Autowired
	private BlogDao blogDao;
	@Autowired
	private FileuploadService fileuploadService;
	
	public BlogVo getBlog(String id) {
		return blogDao.getBlog(id);
	}

	public void updateBlog(BlogVo blogVo, MultipartFile multipartFile) {
		String url= fileuploadService.restore(multipartFile);
		System.out.println("??? "+url);
		if(!("".equals(url))) {
		blogVo.setLogo(url);
		} 
		System.out.println("UPDATE "+blogVo);
		if(blogVo.getTitle()==null) blogVo.setTitle(blogVo.getId()+" 님의 블로그");
 		blogDao.updateBlog(blogVo);
	}

	 

	
}
