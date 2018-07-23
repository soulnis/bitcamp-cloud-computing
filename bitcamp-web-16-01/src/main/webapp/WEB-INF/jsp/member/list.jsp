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
    <td><a href='view/${ member.getId()}'>${ member.getId()}</a></td><td>${ member.getEmail()}</td>
</tr>
</c:forEach>
</table>

<c:if test="${page > 1}">
<a href="list?page=${page -1 }&size=${size}">[이전]</a> 
</c:if>
<c:if test="${page <= 1}">
[이전]
</c:if>
<c:if test="${page < totalPage }">
<a href="list?page=${page +1 }&size=${size}">[다음]</a>
</c:if>
<c:if test="${page >= totalPage}">
[다음]
</c:if>


</body>
</html>
