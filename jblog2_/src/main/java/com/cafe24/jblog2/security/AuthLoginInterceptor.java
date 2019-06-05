package com.cafe24.jblog2.security;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.cafe24.jblog2.service.UsersService;
import com.cafe24.jblog2.vo.UsersVo;

public class AuthLoginInterceptor extends HandlerInterceptorAdapter {
	@Autowired
	private UsersService usersService;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String id= request.getParameter("id");
		String password= request.getParameter("password");
		
		UsersVo usersVo= new UsersVo();
		usersVo.setId(id);
		usersVo.setPassword(password);
		
		UsersVo authUser= usersService.getUser(usersVo);
		if (authUser == null) {
   			response.sendRedirect(request.getContextPath() );
			return false;
		}
		
		//session 처리
		HttpSession session=request.getSession(true);
		session.setAttribute("authUser", authUser);
		response.sendRedirect(request.getContextPath() );
		
		return false;
	}
	
	
}
