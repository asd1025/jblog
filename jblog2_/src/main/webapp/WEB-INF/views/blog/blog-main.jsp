<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!doctype html>
<html>

<head>
  <script
	src="${pageContext.servletContext.contextPath}/assets/js/jquery/jquery-1.9.0.js"></script>
<script>
 
   	function showPost(no,category_no){
		 console.log(no+" "+category_no);
		  $.ajax({
				 url:"${pageContext.request.contextPath}/${blogVo.id}/post?no="+no+"&category_no="+category_no,
				 type:"get",
				 dataType:"json",
				 data:"",
				 success:function(response){
 					if(response.result!="success"){
						console.error(response.message);
						return;
					}
					if(response.data!=null){
					 var firstPost=response.data.firstPost;
					 $('#postTitle').text(firstPost.title);
					 $('#postContent').text(firstPost.content);
						return;
					}
				 },
				error:function(xhr,error){ 
					console.log(error);
				 }		
	});
	}
 

</script> 

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>JBlog</title>
<Link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/jblog.css">
</head>
<body>
	<div id="container">
		<div id="header">
			<h1>
			<c:choose>
			<c:when test="${blogVo.title eq null}"> ${blogVo.id}님의 블로그 </c:when>
			<c:otherwise>${blogVo.title} </c:otherwise>
			</c:choose>
			</h1>
			
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
			<div id="content">
				<div class="blog-content">
					<c:choose>
						<c:when test="${firstVo eq null}"> <h4>작성된 글이 없습니다</h4></c:when>
						<c:otherwise>  <h4 id="postTitle">${firstVo.title}</h4>	</c:otherwise>
					</c:choose>
					<p id="postContent">
						${firstVo.content}
					</p>
				</div>
				
				
				<!--  Post List -->
				<ul class="blog-list">
 					<c:forEach items="${postVo}" var='vo' >
						<li><a href="javascirpt:void(0);" onclick="showPost(${vo.no},${vo.category_no });" return false;   >${vo.title}</a>
							<span> <fmt:parseDate var="dateString" value="${vo.reg_date}" pattern="yyyy-MM-dd" />
							 <fmt:formatDate  pattern="yyyy/MM/dd" value="${dateString}" /></span>
						</li>
					 </c:forEach>
				 </ul>
				 
				 	
			</div>
		</div>

		<div id="extra">
			<div class="blog-logo">
			<c:choose>
			<c:when test="${blogVo.logo eq null}"><td><img src="${pageContext.request.contextPath}/assets/img/images/spring-logo.jpg"></td>    </c:when>
			<c:otherwise><td><img src="${pageContext.request.contextPath}${blogVo.logo}"></td>     </c:otherwise>
			</c:choose>
			</div>
		</div>

		<div id="navigation">
			<h2>카테고리</h2>
			<ul>
				<c:forEach items="${categoryVo}" var='vo'>
					<li><a
						href="${pageContext.request.contextPath}/${blogVo.id}/${vo.no}">${vo.name}</a></li>
				</c:forEach>
			</ul>
		</div>

		<div id="footer">
			<p>
				<strong>Spring 이야기</strong> is powered by JBlog (c)2019
			</p>
		</div>
	</div>

</body>

</html>