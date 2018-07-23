<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset='UTF-8'>
<title>멤버 목록</title>
</head>
<body>
<h1>멤버 목록22</h1>
<p><a href='form'>새회원</a></p>
<table border='1'>
<tr>
    <th>아이디</th><th>이메일</th>
</tr>

<c:forEach items="${list}" var="member">
<tr>
    <td><a href='view/${member.id}'>${member.id}</a></td><td>${member.email}</td>
</tr>
</c:forEach>
</table>
<div>

<c:if test="${page > 1}">
<a href="list?page=${page - 1}&size=${size}">[이전]</a>
</c:if>
<!-- 현재페이지가 1이거나 그 이하면 다음페이지 클릭이 안되게 만듬  -->
<!--  ""사이에 공백을 넣으면 오류남 조심해!! -->
<c:if test="${page <= 1}"> 
[이전]
</c:if>
<c:if test="${page < totalPage }">
<a href="list?page=${page + 1}&size=${size}">[다음]</a>
</c:if>
<c:if test="${page >= totalPage}">
다음
</c:if>

</div>
</body>
</html>
