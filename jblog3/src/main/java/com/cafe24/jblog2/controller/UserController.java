package com.cafe24.jblog2.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cafe24.jblog2.dto.JSONResult;
import com.cafe24.jblog2.service.UsersService;
import com.cafe24.jblog2.vo.UsersVo;


@RequestMapping("/user")
@Controller
public class UserController {
	@Autowired
	private UsersService usersService;
	
	@RequestMapping(value="/join",method = RequestMethod.GET)
	public String joinForm(@ModelAttribute  UsersVo usersVo) {
		return "user/join";
	}
	@RequestMapping("/joinSuccess")
	public String joinSuccess() {
		return "user/joinsuccess";
	}
	
	@ResponseBody
	@RequestMapping("/checkId")
	public JSONResult checkEmail
	(@ModelAttribute @Valid UsersVo usersVo,BindingResult result,Model model
			) {
 		if( result.hasErrors() ) {
			List<ObjectError> list = result.getAllErrors();
			for( ObjectError error : list ) {
				System.out.println(error);
			}
			model.addAllAttributes(result.getModel());	
 		}
		
	Boolean exist=	usersService.existId(usersVo);
	return   JSONResult.success(exist);
	}
	
	@RequestMapping(value="/join",method = RequestMethod.POST)
	public String join(@ModelAttribute @Valid UsersVo usersVo,BindingResult result
			,Model model ) {
 		if( result.hasErrors() ) {
			List<ObjectError> list = result.getAllErrors();
			for( ObjectError error : list ) {
				System.out.println(error);
			}
			model.addAllAttributes(result.getModel());	
			return "user/join";
		}
		
 		usersService.join(usersVo);
	   return "redirect:/user/joinSuccess";
	}
	
	@RequestMapping(value = {"/login"})
	public String login() { 
		return "user/login";
	}
}
