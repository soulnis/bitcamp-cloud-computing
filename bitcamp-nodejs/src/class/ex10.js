// 주제: 데이버베이스 프로그래밍 -  in-parameter사용하기 
const mysql = require('mysql');

var con = mysql.createConnection({
   host : '52.79.239.97', // 호스트가 localhost면 생략 가능
   // port : '3306',   // 포트 번호가 3306이면 생략가능
   database : 'studydb',
   user : 'study',
   password : '1111'
});

 con.connect(function(err){
     if(err){throw err;}
       
     console.log('연결성공!!');
 });
 
 var mid = "user002 or 1=1 or ''='";
     
 // in-parameter 사용하기
 // => 인 파라미터 방식에서는 사용자가 입력한 값으로 SQL을 만드는 것이 아니기 때문에
 //    SQL 조작이 불가능 하다.
 con.query(`delete from pms2_member
            where mid=?`
         , [mid]  //?(인 파라미터) 개수만큼 배열에 값을 담아 놓으면 된다.
         , function(err,result){
     
     if(err) throw err;
     //results 파리미터에 결과가 들어 있다.
     console.log('삭제성공');
     console.log('=======================');
     console.log(result.affectedRows); // 인서트한 갯수
     
 });
 
 con.end(function(err){
     if(err) throw err;
     console.log("연결을 끊었습니다.")
 }); // 지금 당장 연결을 끊으라는것이 아니라 더 이상 sql작업을 실행하지 않는다면 예약을 끊는것.
 
 console.log('select 실행');
 
 
 //노드는 주소찾기,아이디중복검사,파일업로드같은경우에 쓰임