<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset='UTF-8'>
<title>게시물 보기</title>
</head>
<body>
<h1>게시물 보기</h1>
<form action='../update' method='post'>
<table border='1'>
<c:choose>
<c:when test="${board==null}">
<h1>해당 게시글은없습니다.</h1>
</c:when>
<c:otherwise>
<tr><th>번호</th><td>
    <input type='text' name='bno' value='${board.bno}' readonly></td></tr>
<tr><th>제목</th>
    <td><input type='text' name='title' value='${board.title}'></td></tr>
<tr><th>내용</th>
    <td><textarea name='content' rows='10' cols='60'>${board.content}</textarea></td></tr>
<tr><th>등록일</th><td>2018-07-06</td></tr>
</c:otherwise>
</c:choose>
</table>
<p>
<a href='list'>목록</a>
<button>변경</button>
<a href='delete?no=${board.bno}'>삭제</a>
</p>
</form>
</body>
</html>
