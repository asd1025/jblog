package com.cafe24.jblog2.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.cafe24.jblog2.dto.JSONResult;
import com.cafe24.jblog2.security.Auth;
import com.cafe24.jblog2.security.Auth.Role;
import com.cafe24.jblog2.service.BlogService;
import com.cafe24.jblog2.service.CategoryService;
import com.cafe24.jblog2.service.PostService;
import com.cafe24.jblog2.vo.BlogVo;
import com.cafe24.jblog2.vo.CategoryVo;
import com.cafe24.jblog2.vo.PostVo;

 @Controller
@RequestMapping("/{id:^(?!assets).*$}/admin")
public class AdminController {
	@Autowired
	private BlogService blogService;
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private PostService postService;
	
	// 글쓰기 
	@Auth(role=Role.ADMIN)
		@RequestMapping(value="/write",method=RequestMethod.POST)
		public String writePost (@PathVariable(value="id") String id,@ModelAttribute PostVo postVo, Model model) {
			 System.out.println(postVo+" .... WRITE POST");
			 postService.writePost(postVo);
			return  "redirect:/"+id;
		}
		// 글쓰기  폼으로 
	@Auth(role=Role.ADMIN)
		@RequestMapping("/write")
		public String write (@PathVariable(value="id") String id,Model model) {
			System.out.println("write| /admin/write GET");
			List<CategoryVo> list=categoryService.getCategoryList(id);
			model.addAttribute("list",list);
			return  "blog/blog-admin-write";
		}
		
		// 카테고리 삭제  / 그 카테고리 내 글들도 모두 삭제
		@ResponseBody
		@Auth(role=Role.ADMIN)
		@RequestMapping("/deleteCategory")
		public JSONResult deleteCategory (String no) {
			categoryService.deleteCategory(Integer.parseInt(no));
			postService.deleteAllPostByCategoryNo(Integer.parseInt(no));
			System.out.println("deleteCategoty| /admin/deleteCategory ");
			return   JSONResult.success("success");
		}
		// 카테고리 추가시 
		@ResponseBody
		@Auth(role=Role.ADMIN)
		@RequestMapping(value= {"/insertCategoty"  },method = RequestMethod.POST)
		public JSONResult insertCategoty(@PathVariable(value="id") String id, @ModelAttribute CategoryVo vo) {
			System.out.println("insertCategoty| /admin/insertCategoty");
			vo.setBlog_id(id);
			System.out.println(vo+"..insera");
			categoryService.insertCategory(vo);
			return   JSONResult.success("success");
		}
		
		// 블로그 정보 수정시
		@Auth(role=Role.ADMIN)
		@RequestMapping(value= {"/update"  },method = RequestMethod.POST)
		public String update(@PathVariable(value="id") String id, @ModelAttribute BlogVo blogVo,
				 @RequestParam(value="logo-file")  MultipartFile multipartFile, Model model
				) {
			System.out.println("update|  /admin/update");
			blogService.updateBlog(blogVo,multipartFile);
			model.addAttribute("blogVo",blogVo);
	 		return "redirect:/"+id;
		}
		
		// 블로그 아이디에 해당하는 모든 카테고리 가져오기 (AJAX)
		@ResponseBody
		@Auth(role=Role.ADMIN)
		@RequestMapping(value= {"/getCategoryList"  })
		public JSONResult getCategoryList(@PathVariable(value="id") String id) {
			System.out.println("getCategoryList|  /admin/getCategoryList");
			List<CategoryVo> list=categoryService.getCategoryList(id);
			return JSONResult.success(list);
		}
		
		// 카테고리 수정하는 뷰로 가기 
		@Auth(role=Role.ADMIN)
		@RequestMapping(value= {"/category"  })
		 public String adminBlogForm(@PathVariable(value="id") String id ,Model model
				 ) {
			System.out.println("adminBlogForm|  /admin/category");
			 return "blog/blog-admin-category";
		 }
		
		@Auth(role=Role.ADMIN)
		@RequestMapping(value= {"/basic"  })
		public String adminBlogBasic(@PathVariable(value="id") String id ,Model model
				) {
			System.out.println("adminBlogBasic|  /admin/basic");

			BlogVo blogVo=blogService.getBlog(id);
			model.addAttribute("blogVo",blogVo);
			return "blog/blog-admin-basic";
		}
		
		@Auth(role=Role.ADMIN)
		@RequestMapping(value= {"/basic"  },method = RequestMethod.POST)
		public String adminBlog(@PathVariable(value="id") String id ,Model model
				) {
			System.out.println("adminBlog|  /admin/basic");
			BlogVo blogVo=blogService.getBlog(id);
			model.addAttribute("blogVo",blogVo);
			return "blog/blog-admin-basic";
		}
		
}
