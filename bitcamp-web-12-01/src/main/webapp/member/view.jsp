<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset='UTF-8'>
<title>멤버 보기</title>
</head>
<body>
<h1>멤버 보기</h1>
<c:choose>
<c:when test="${member == null }">
<h1>해당회원은 존재 하지 않습니다.</h1>
</c:when>
<c:otherwise>
<form action='update' method='post'>
<table border='1'>
<tr><th>아이디</th><td>
    <input type='text' name='id' value='${member.id}' readonly></td></tr>
<tr><th>이메일</th>
    <td><input type='email' name='email' value='${member.email}'></td></tr>
<tr><th>암호</th>
    <td><input type='password' name='password'></td></tr>

</table>
<p>
<a href='list'>목록</a>
<button>변경</button>
<a href='delete?id=${member.id}'>삭제</a>
</p>
</form>
</c:otherwise>
</c:choose>
</body>
</html>
