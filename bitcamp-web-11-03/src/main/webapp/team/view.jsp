<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset='UTF-8'>
<title>팀 보기</title>
</head>
<body>
<h1>팀 보기</h1>
<form action='update' method='post'>
<table border='1'>
<tr>
    <th>팀명</th><td><input type="text" name="name" value='123123' readonly></td>
</tr>
<tr>
    <th>설명</th><td><textarea name="description" 
        rows="6" cols="60">1232131</textarea></td>
</tr>
<tr>
    <th>최대인원</th><td><input type="number" name="maxQty" value='3'></td>
</tr>
<tr>
    <th>시작일</th><td><input type="date" name="startDate" value='2018-07-11'></td>
</tr>
<tr>
    <th>종료일</th><td><input type="date" name="endDate" value='2018-07-11'></td>
</tr>
</table>
<p>
<a href='list'>목록</a>
<button>변경</button>
<a href='delete?name=123123'>삭제</a>
<a href='../task/list?teamName=123123'>작업목록</a>
</p>
</form>
<h2>회원 목록</h2>
<form action='member/add' method='post'>
<input type='text' name='memberId' placeholder='회원아이디'>
<input type='hidden' name='teamName' value='123123'>
<button>추가</button>
</form>
<table border='1'>
<tr><th>아이디</th><th>이메일</th><th> </th></tr>
</table>
</body>
</html>
