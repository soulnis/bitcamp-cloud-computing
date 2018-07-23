<%@page import="java.io.PrintWriter"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>에러 페이지입니다.</h1>
<pre>
<%
Throwable error =(Throwable) request.getAttribute("error");
error.printStackTrace(new PrintWriter(out));
%>


</pre>
</body>
</html>