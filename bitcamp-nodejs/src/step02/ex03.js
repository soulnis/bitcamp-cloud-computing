// 주제: 데이버베이스 프로그래밍 - select 실행


const mysql = require('mysql');

var con = mysql.createConnection({
   host : '52.79.239.97', // 호스트가 localhost면 생략 가능
   // port : '3306',   // 포트 번호가 3306이면 생략가능
   database : 'studydb',
   user : 'study',
   password : '1111'
});

 con.connect(function(err){
     if(err){
         throw err; 
     } 
     console.log("연결성공!!");
 });
 // query(sql, 실행후 호출될 함수)
 // =>SQL 실행 예약! 지금 당장 실행하는것이 아니다.
 con.query('select * from pms2_member',function(err,results){
     
     if(err) throw err;
     
     console.log('결과를 가져왔습니다.')
     
 });
 
 con.end(function(err){
     if(err) throw err;
     console.log("연결을 끊었습니다.")
 }); // 지금 당장 연결을 끊으라는것이 아니라 더 이상 sql작업을 실행하지 않는다면 예약을 끊는것.
 
 console.log('select 실행');
 
 