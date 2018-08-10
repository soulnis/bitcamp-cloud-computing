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
     //if(err){throw err;}
       
     console.log("연결성공!!");
 });
 // query(sql, 실행후 호출될 함수)
 // sql을 바로 실행하는 것이 아니라 실행을 예약한다.
 // dbms와 연결되면 이렇게 예약한 sql문을 실행할 것이다.
 // => 문제는 연결 오류에 상관없이 예약한다는것이다.
 // connect()에 등록한 함수에서 예외를 던지지 않으면 
 // 이 sql문을 실행한다.
 con.query('select * from pms2_member',function(err,results){
     
     if(err) throw err;
     //results 파리미터에 결과가 들어 있다.
     console.log('결과를 가져왔습니다.');
     console.log('=======================');
     for(var row of results){
         //row 객체에서 값을 꺼낼 때는 sql에서 지정한 컬러명 또는 별명을 사용한다.
         console.log(row.email,row.mid,row.pwd);
     }
     
 });
 
 con.end(function(err){
     if(err) throw err;
     console.log("연결을 끊었습니다.")
 }); // 지금 당장 연결을 끊으라는것이 아니라 더 이상 sql작업을 실행하지 않는다면 예약을 끊는것.
 
 console.log('select 실행');
 
 