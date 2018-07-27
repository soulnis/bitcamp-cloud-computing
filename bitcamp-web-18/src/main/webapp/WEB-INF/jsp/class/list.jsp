<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset='UTF-8'>
<title>강의 목록</title>
</head>
<body>
<h1>강의 목록</h1>
<p><a href='form'>새 강의</a></p>
<table border='1'>
<tr>
    <th>번호</th><th>강의명</th><th>기간</th><th>강의실</th>
</tr>
<c:forEach items="${list}" var="classroom">
<tr>
    <td>${classroom.bno}</td>
    <td><a href='view/${classroom.bno}'>${classroom.title}</a></td>
    <td>${classroom.startTime}</td>
    <td>${classroom.endTime}</td>
</tr>
</c:forEach>
</table>
</body>
</html>
