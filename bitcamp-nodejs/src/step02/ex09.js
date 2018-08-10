// 주제: 데이버베이스 프로그래밍 -  delete문 실행


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
 
 var email = 'user002@test.com',
     mid = 'user002',
     pwd = '1111';
 
 // sql문을 만들 때 값을 직접 넣어서 만드는 경우 
 // 해커의 공격에 노출 될 수 있다.
 // 아래 주석을 막은 mid 변수의 값 처럼 외부 사용자가 값을 입력한다면
 // 조건이 무조건 참이 되기 때문에 전체 데이터가 삭제되는 결과를 낫는다.
 // 그래서 sql문에 변수의 값을  직접 삽입하는 방법을 써서는 안된다.
 // var mid = "user002' or 1=1 or ''='";  
 
 // 해결책?
 // =>in-parameter 방법을 사용하라!!
 con.query(`delete from pms2_member
            where mid='${mid}'`
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
 
 