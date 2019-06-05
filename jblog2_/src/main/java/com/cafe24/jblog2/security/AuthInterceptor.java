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

 
public class AuthInterceptor  extends HandlerInterceptorAdapter{
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
	 
		if(handler instanceof HandlerMethod == false) {
			return true;
		}

		HandlerMethod handlerMethod=(HandlerMethod) handler;
		System.out.println(handlerMethod.getMethod().toString());
		
		Auth auth= handlerMethod.getMethodAnnotation(Auth.class);
 		
		if(auth==null) {return true;}
  		HttpSession session= request.getSession();
		if(session==null) {// 인증이 안되어 있네
			response.sendRedirect(request.getContextPath()+"/user/login");
			return false;
		}
		
		UsersVo authUser= (UsersVo) session.getAttribute("authUser");
		if(authUser==null) { //인증이 안되어 있네
			response.sendRedirect(request.getContextPath()+"/user/login");
			return false;
		}
		System.out.println("AUTH    INTERCEPTOR ~~~~~~~~~~~~~~~~~~~~~~");

		// controller의 @pathVariable 을 가져오는 방법  
		Map<?,?> map= (Map<?, ?>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
	  	String id=(String) map.get("id");
 		String role=auth.role().toString();
 		
 		if(role.equals("ADMIN")) {
			if(!authUser.getId().equals(id)) {
				response.sendRedirect(request.getContextPath()+"/"+id);
				return false;
			}
		} 
		
		return true;
	}
	

}
