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
</head>
<body>
	<div id="container">
		<div id="header">
		<c:choose>
			<c:when test="${blogVo.title eq null}"><h1> ${blogVo.id}님의 블로그 </h1></c:when>
			<c:otherwise><h1>${blogVo.title}</h1> </c:otherwise>
			</c:choose>
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
					<li class="selected">기본설정</li>
					<li><a href="${pageContext.servletContext.contextPath}/${authUser.id}/admin/category">카테고리</a></li>
					<li><a href="${pageContext.servletContext.contextPath}/${authUser.id}/admin/write">글작성</a></li>
				</ul>
				
				<form action="${pageContext.servletContext.contextPath}/${authUser.id}/admin/update" method="post" enctype="multipart/form-data">
	 		      	<input type="hidden"  name="id" value="${authUser.id}" />
	 		      	<table class="admin-config">
			      		<tr>
			      			<td class="t">블로그 제목</td>
			      			<td><input type="text" size="40" name="title"></td>
			      		</tr>
			      		<tr>
			      			<td class="t">로고이미지</td>
			      				<c:choose>
			<c:when test="${blogVo.logo eq null}"><td><img src="${pageContext.request.contextPath}/assets/img/images/spring-logo.jpg"></td>    </c:when>
			<c:otherwise><td><img src="${pageContext.request.contextPath}${blogVo.logo}"></td>     </c:otherwise>
			</c:choose>
			      			  			
			      		</tr>      		
			      		<tr>
			      			<td class="t">&nbsp;</td>
			      			<td><input type="file" name="logo-file"></td>      			
			      		</tr>           		
			      		<tr>
			      			<td class="t">&nbsp;</td>
			      			<td class="s"><input type="submit" value="기본설정 변경"></td>      			
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