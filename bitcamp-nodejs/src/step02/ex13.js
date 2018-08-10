// 주제: 데이버베이스 프로그래밍 - insert 실행 후 auto_increment PK 컬럼 값 알아내기


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
       
     console.log('연결성공!!');
 });
 
 var title = '제목임돠2',
     content = '내용입돠2',
     pwd = '1111';
 
 
 con.query(`insert into pms2_board(titl,cont,cdt)
          values (?,?,now())`
         , [title,content]
         , function(err,result){
     
     if(err) throw err;
     //results 파리미터에 결과가 들어 있다.
     console.log('결과를 가져왔습니다.');
     console.log('=======================');
     console.log(result.affectedRows); // 인서트한 갯수
     console.log(result.insertId); // 자동 생성된 bno값 반환
     
 });
 
 con.end(function(err){
     if(err) throw err;
     console.log("연결을 끊었습니다.")
 }); // 지금 당장 연결을 끊으라는것이 아니라 더 이상 sql작업을 실행하지 않는다면 예약을 끊는것.
 
 console.log('select 실행');
 
 