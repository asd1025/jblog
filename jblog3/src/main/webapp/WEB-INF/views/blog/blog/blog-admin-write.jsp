<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JBlog</title>
<Link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/jblog.css">
 <script
	src="${pageContext.servletContext.contextPath}/assets/js/jquery/jquery-1.9.0.js"></script>
<script>
$(function(){
	getBlogInfo();
});
 
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
					<li><a href="${pageContext.servletContext.contextPath}/${authUser.id}/admin/category">카테고리</a></li>
					<li class="selected">글작성</li>
				</ul>
				<form action="${pageContext.servletContext.contextPath}/${authUser.id}/admin/write" method="post">
			      	<table class="admin-cat-write">
			      		<tr>
			      			<td class="t">제목</td>
			      			<td>
			      				<input type="text" size="60" name="title">
				      			<select name="category_no">
				      			<c:forEach items="${list}" var="vo">
				      				<option value='${vo.no}'>${vo.name}</option>
 				      			</c:forEach>
				      			</select>
				      		</td>
			      		</tr>
			      		<tr>
			      			<td class="t">내용</td>
			      			<td><textarea name="content"></textarea></td>
			      		</tr>
			      		<tr>
			      			<td>&nbsp;</td>
			      			<td class="s"><input type="submit" value="포스트하기"></td>
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