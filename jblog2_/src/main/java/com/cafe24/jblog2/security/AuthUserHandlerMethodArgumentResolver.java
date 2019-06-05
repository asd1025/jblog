package com.cafe24.jblog2.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebArgumentResolver;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.cafe24.jblog2.vo.UsersVo;


public class AuthUserHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver {
	// 요청에 대한 모든 파라미터에 대한 검사 

	@Override
	public Object resolveArgument(MethodParameter parameter, 
			ModelAndViewContainer mavContainer,
			NativeWebRequest webRequest, 
			WebDataBinderFactory binderFactory) throws Exception {
		if(supportsParameter(parameter)==false) {
			return WebArgumentResolver.UNRESOLVED; 
		}
		
		HttpServletRequest request= webRequest.getNativeRequest(HttpServletRequest.class);
		HttpSession session= request.getSession();
		if(session==null) {
			return null;
		}
		return session.getAttribute("authUser");
	}
	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		AuthUser authUser= parameter.getParameterAnnotation(AuthUser.class);
		// @AuthUser가 안붙어 있음.
		if(authUser==null) {
			return false;
		}
		
		if(parameter.getParameterType().equals(UsersVo.class)==false){
			return false;
		}
		
		return true;
	}

}
