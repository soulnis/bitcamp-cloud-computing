// 주제: 데이버베이스 프로그래밍 - select 실행 II


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
       
     console.log("연결성공!!");
     
     // 예약 순서대로 실행함
     // 연결에 성공했을 떄만 SQL을 실행하라고 *예약* 할 수 있다.
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
     
});
 
 // 이예제에서는 연결 완료 후 END를 먼저 실행하라고 예약 했기 때문에
 // Connect()에 등록된 함수가 호출되어 sql문을 예약하더라고 end()보다 그 이후에 실행되기때문에
 // sql 실행 오류가 발생할 것 이다.
 // 해결책: end는 sql 실행 먼저 예약 한 다음에 end()를 예약하라!
 // => ex06.js를 보라
 con.end(function(err){
     if(err) throw err;
     console.log("연결을 끊었습니다.")
 }); // 지금 당장 연결을 끊으라는것이 아니라 더 이상 sql작업을 실행하지 않는다면 예약을 끊는것.
 
 console.log('select 실행');
 // cannot enqueue Qery after invoking quit 오류발생
 // cannot enqueue query 큐에 쿼리를 넣을수없다
 // after invoking quit 종료 예약 후 에는
 
 // stack - 선입후출 - 브라우저 앞으로 뒤로가기 ,
 // queue - 선입 선출 - 영화예매,프린터예약순위
 

 