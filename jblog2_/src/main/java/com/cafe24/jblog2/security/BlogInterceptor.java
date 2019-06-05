package com.cafe24.jblog2.security;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.cafe24.jblog2.service.BlogService;
import com.cafe24.jblog2.vo.BlogVo;
import com.cafe24.jblog2.vo.UsersVo;

 
public class BlogInterceptor  extends HandlerInterceptorAdapter{
	@Autowired
	BlogService blogService;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
	 
		if(handler instanceof HandlerMethod == false) {
			return true;
		}
		HandlerMethod handlerMethod=(HandlerMethod) handler;
		
		Auth auth= handlerMethod.getMethodAnnotation(Auth.class);
		// controller의 @pathVariable 을 가져오는 방법  
				Map<?,?> map= (Map<?, ?>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
			  	String id=(String) map.get("id");
  		// 존재하는 블로그 주소인가 체크 
		BlogVo blogVo= new BlogVo();
		blogVo.setId(id);
		BlogVo authBlog= blogService.getBlog(id);
		System.out.println("BLOG INTERCEPTOR ~~~~~~~~~~~~~~~~~~~~~~"+authBlog+"  id: "+id);
	     if (authBlog == null) {
		 	response.sendRedirect(request.getContextPath() );
		 return false;
		}
		return true;
	}
	

}
