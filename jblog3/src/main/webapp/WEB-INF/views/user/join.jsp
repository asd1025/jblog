<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JBlog</title>
<Link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/jblog.css">
<script
	src="${pageContext.servletContext.contextPath}/assets/js/jquery/jquery-1.9.0.js"></script>
<script>
function doJoin(){
	if(!$('#img-checkemail').is(':visible')){
	 		alert('ID 중복체크를 하세요! ')
 		return false;
 	}
	//console.log(obj);
 	return true;
}

$(function(){
	 
	// 아이디 값이 변화되었나? 
	$("#check_id").on("change paste keyup", function() {
		$('#btn-checkemail').show();
		$('#img-checkemail').hide();
		});
	
	 $('#btn-checkemail').click(function(){
		 var id=$('#check_id').val();
		 if(id==''){
			 return;
		 }
		 
		 /* Ajax 통신 */
		 $.ajax({
			 url:"${pageContext.servletContext.contextPath}/user/checkId?id="+id,
			 type:"get",
			 dataType:"json",
			 data:"",
			 success:function(response){
				if(response.result!="success"){
					console.error(response.message);
					return;
				}
				if(response.data==true){
					alert('이미 존재하는 이메일입니다.\n 다른 이메일을 사용해주세요');
					$('#check_id').focus();
					$('#check_id').val('');
					return;
				}
				$('#btn-checkemail').hide();
				$('#img-checkemail').show();
				
			 },
			error:function(xhr,error){
				console.log("error:"+error);
			 }			 
		 });
 		 
	 });
});

</script>
</head>
<body>
	<div class="center-content">
		<a href="${pageContext.servletContext.contextPath}/"><h1 class="logo">JBlog</h1></a>
	<ul class="menu">
		<c:choose>
		<c:when test="${empty authUser}">
			<li><a href="${pageContext.servletContext.contextPath}/user/login">로그인</a></li>
			<li><a href="${pageContext.servletContext.contextPath}/user/join">회원가입</a></li>
		</c:when>
		<c:otherwise>
			<li><a href="${pageContext.servletContext.contextPath}/user/logout">로그아웃</a></li>
			<li><a href="${pageContext.servletContext.contextPath}/user/blog">내블로그</a></li>
		</c:otherwise>
		</c:choose>
		</ul>
		<form:form modelAttribute="usersVo" onsubmit="return doJoin();" class="join-form" id="join-form" method="post" action="${pageContext.request.contextPath}/user/join">
			<label class="block-label" for="name">이름</label>
 								<form:input path="name"/>
 								<p style="font-weight:bold; color:#f00; text-align:left; padding:0; margin:0 ">
							<form:errors path="name"/>	
						</p>
 			
			<label class="block-label" for="blog-id">아이디</label>
			<form:input path="id" id="check_id"/>
				<p style="font-weight:bold; color:#f00; text-align:left; padding:0; margin:0 ">
							<form:errors path="id"/>	
				</p>
  			<input id="btn-checkemail" type="button" value="id 중복체크">
 			<img id="img-checkemail" style="display: none;" src="../assets/img/images/check.png">
 

			<label class="block-label" for="password">패스워드</label>
 								<form:input path="password"  type="password" id="password" />
						<p style="font-weight:bold; color:#f00; text-align:left; padding:0; margin:0 ">
				<form:errors path="password"/>	
			</p>

			<fieldset>
				<legend>약관동의</legend>
				  <input required="required"id="agree-prov" type="checkbox" name="agreeProv" value="y" />
 				<label class="l-float">서비스 약관에 동의합니다.</label>
			</fieldset>

			<input type="submit" value="가입하기">

		</form:form>
	</div>
</body>
</html>
