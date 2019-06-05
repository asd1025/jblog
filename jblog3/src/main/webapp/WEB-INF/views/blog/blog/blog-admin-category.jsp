<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
 <script
	src="${pageContext.servletContext.contextPath}/assets/js/jquery/jquery-1.9.0.js"></script>
<script>
$(function(){
	getListCategory();
	getBlogInfo();
	
});
function deleteCategory(no){
	 $.ajax({
		 url:"${pageContext.request.contextPath}/${authUser.id}/admin/deleteCategory?no="+no,
		 type:"get",
		 dataType:"json",
		 data: ""
		 ,
		 success:function(response){
				if(response.result!="success"){
				console.error(response.message);
				return;
			}
			if(response.data!=null){
				getListCategory();
				return;
			}
		 },
		error:function(xhr,error){
			console.log(error);
		 }	
});
	return;
}

	function insertCategoty( ){
 			   $.ajax({
				 url:"${pageContext.request.contextPath}/${authUser.id}/admin/insertCategoty",
				 type:"post",
				 dataType:"json",
				 data: $('#categoryForm').serialize()
				 ,
				 success:function(response){
 					if(response.result!="success"){
						console.error(response.message);
						return;
					}
					if(response.data!=null){
						getListCategory();
						return;
					}
				 },
				error:function(xhr,error){
					console.log(error);
				 }		
				 
	}); 
	}
	function getListCategory(){
 		 $('#categorytable tr:not(:first)').remove();
		  $.ajax({
				 url:"${pageContext.request.contextPath}/${authUser.id}/admin/getCategoryList",
				 type:"get",
				 dataType:"json",
				 data:"",
				 success:function(response){
					if(response.result!="success"){
						console.error(response.message);
						return;
					}
					if(response.data!=null){
						var list=response.data;
						var count=list.length;
						console.log("LENGTH "+count);
						var my_tbody = $("#categorytable");
					    
					    $.each(list,function(idx,vo){
					    	console.log(vo);
 					          $('#categorytable > tbody :last').
					        append('<tr><td>'+ (count-idx)  +' </td><td>' +   vo.name  + '</td><td>'+vo.totalCount
					        		+'</td><td>'+vo.description+"</td><td><a href='javascirpt:void(0);'"+" onclick='deleteCategory(" +vo.no+");'"+" return false;>"+
					        		'<img src="${pageContext.request.contextPath}/assets/img/images/delete.jpg"></a>'
					        		+'</tr>');
  
					    });
 						return;
					}
				 },
				error:function(xhr,error){
					console.log(error);
				 }		
		  });
	}
	function getBlogInfo(){
 		 
		  $.ajax({
				 url:"${pageContext.request.contextPath}/${authUser.id}/getBlogInfo",
				 type:"get",
				 dataType:"json",
				 data:"",
				 success:function(response){
					if(response.result!="success"){
						console.error(response.message);
						return;
					}
					if(response.data!=null){
						var title=response.data.title;
						console.log(title);
						$('#header').append("<h1>"+title+"</h1>");
 						return;
					}
				 },
				error:function(xhr,error){
					console.log(error);
				 }		
		  });
	}

		  
	
 

</script> 
<title>JBlog</title>
<Link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/jblog.css">
</head>
<body>
	<div id="container">
		<div id="header">
 			<ul>
 			<c:choose>
			<c:when test="${not empty authUser}">
				<li><a href="${pageContext.servletContext.contextPath}/user/logout">로그아웃</a></li>
			<c:if test="${blogVo.id eq authUser.id}">
				<li><a href="${pageContext.servletContext.contextPath}/${authUser.id}/admin/basic">블로그 관리</a></li></c:if>
			<li> <b>${authUser.id}</b>님 반갑습니다! </li>
			</c:when>
			<c:otherwise>
				<li><a href="${pageContext.servletContext.contextPath}/user/login">로그인</a></li>
			</c:otherwise>
			</c:choose>
			</ul>
		</div>
		
		<div id="wrapper">
			<div id="content" class="full-screen">
				<ul class="admin-menu">
					<li><a href="${pageContext.servletContext.contextPath}/${authUser.id}/admin/basic">기본설정</a></li>
					<li class="selected">카테고리</li>
					<li><a href="${pageContext.servletContext.contextPath}/${authUser.id}/admin/write">글작성</a></li>
				</ul>
 		      	<table class="admin-cat" id="categorytable">
		      		<tr>
		      			<th>번호</th>
		      			<th>카테고리명</th>
		      			<th>포스트 수</th>
		      			<th>설명</th>
		      			<th>삭제</th>      			
		      		</tr>
				</table>
      	
      			<h4 class="n-c">새로운 카테고리 추가</h4>
      			<form id="categoryForm" name="categoryForm" method="post">
		      	<table id="admin-cat-add">
		      		<tr>
		      			<td class="t">카테고리명</td>
		      			<td><input type="text" name="name" ></td>
		      		</tr>
		      		<tr>
		      			<td class="t">설명</td>
		      			<td><input type="text" name="description"></td>
		      		</tr>
		      		<tr>
		      			<td class="s">&nbsp;</td>
		      			<td><input type="submit" value="카테고리 추가" onclick="insertCategoty();"></td>
		      		</tr>      		      		
		      	</table> 
      			</form>
			</div>
		</div>
		<div id="footer">
			<p>
				<strong>Spring 이야기</strong> is powered by JBlog (c)2019
			</p>
		</div>
	</div>
</body>
</html>