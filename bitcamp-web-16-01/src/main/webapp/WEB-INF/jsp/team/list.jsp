<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset='UTF-8'>
<title>팀 목록</title>
</head>
<body>
<h1>팀 목록</h1>
<p><a href='form.html'>새 팀</a></p>
<table border='1'>
<tr>
    <th>팀명</th><th>최대인원</th><th>기간</th>
</tr>
<c:forEach items="${list}" var="team">
<tr>
    <td><a href='view?name=${team.name }'>${team. }</a></td><td>3</td><td>2018-07-05~2018-07-27</td>
</tr>
</c:forEach>
</table>
</body>
</html>
