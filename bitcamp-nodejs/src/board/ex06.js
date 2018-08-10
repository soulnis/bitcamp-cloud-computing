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
     con.query(`select bno,titl title,cdt as cnt 
                 from pms2_board`
             ,function(err,results){
         
         if(err) throw err;
         //results 파리미터에 결과가 들어 있다.
         console.log('결과를 가져왔습니다.');
         console.log('=======================');
         for(var row of results){
             //row 객체에서 값을 꺼낼 때는 sql에서 지정한 컬러명 또는 별명을 사용한다.
             console.log(row.bno,row.title,row.cnt);
         }
     });
     // end()도 sql실행 후 에 종료 하라고 예약 해야한다.
     con.end(function(err){
         if(err) throw err;
         console.log("연결을 끊었습니다.")
     }); // 지금 당장 연결을 끊으라는것이 아니라 더 이상 sql작업을 실행하지 않는다면 예약을 끊는것.
});
 
 
 console.log('select 실행');
 
 