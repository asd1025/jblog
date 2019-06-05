package com.cafe24.jblog2.exception;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.cafe24.jblog2.dto.JSONResult;
import com.fasterxml.jackson.databind.ObjectMapper;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	//log create
	private static final Log LOGGER=LogFactory.getLog(GlobalExceptionHandler.class);
	
	
	@ExceptionHandler(PathException.class)
	public void handlerException(HttpServletRequest req
			,HttpServletResponse res,Exception e) throws IOException, ServletException {
		//1. 로깅
		e.printStackTrace();
		StringWriter errors=new StringWriter();
		e.printStackTrace(new PrintWriter(errors));
		LOGGER.error(errors.toString());
		
		String accept = req.getHeader("accept");
		
		if(accept.matches(".*application/json.*")) {
			//json으로 응답
			res.setStatus(HttpServletResponse.SC_OK);
			JSONResult jsonResult=JSONResult.fail(errors.toString());
		String result=	new ObjectMapper().writeValueAsString(jsonResult);
		System.out.println(result);
		OutputStream os= res.getOutputStream();
		os.write(result.getBytes("UTF-8"));
		os.flush();
		os.close();
			
		} else {
			//2. 안내페이지 가기 + 정상 종료 (response)
			req.setAttribute("uri", req.getRequestURI());
			req.setAttribute("exception", errors.toString());
			req.getRequestDispatcher("/WEB-INF/views/error/exception.jsp")
			.forward(req, res);
		}
	}
}
