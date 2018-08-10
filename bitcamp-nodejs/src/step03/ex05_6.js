// 주제: express 사용하기

const mysql = require('mysql')
const express = require('express')
const app = express();
const app2 = express(); //두번째 서버를 생성 포트는 8001번으로
var pool = mysql.createPool({
    connectionLimit : 10, // 커넥션을 최대 10개까지만 생성 10개를 다쓰면 반납받을때까지 기다려야함
    host : '52.79.239.97', // 호스트가 localhost면 생략 가능
    // port : '3306',   // 포트 번호가 3306이면 생략가능
    database : 'studydb',
    user : 'study',
    password : '1111'
});

// get핸들러에처리하기
app.get('/member/list',(req,res)=>{
  
    
    var pageNo = parseInt(req.query.pageNo) || 1;
    var pageSize = parseInt(req.query.pageSize) || 3;
    var startIndex = (pageNo -1)*pageSize; //시작인덱스 db는 0부터 시작하기떄문에 -1해줌

    res.writeHead(200,{ 'Content-Type' : 'text/plain;charset=UTF-8'});
    // 커넥션 풀 객체를 이용해 바로 질의를 실행한다.
    pool.query('select * from pms2_member limit ?,?'
        ,[startIndex,pageSize]
        , function(err,results){
     if(err){
         res.end('db조회중 예외발생');
         return;
     }
     console.log('결과를 가져왔습니다.');
     
     for(var row of results){
         res.write(`${row.email},${row.mid}\n`);
     }
     res.write(`${pageNo} \n ${pageSize}\n ${startIndex}`); 
     res.end(); // end()에서 꼭 출력할 필요없다~
        });
});

app.get('/member/add',(req,res)=>{
    
    res.writeHead(200,{ 'Content-Type' : 'text/plain;charset=UTF-8'});
    var id = req.query.id;
    var email = req.query.email;
    var password = req.query.password;

    // 커넥션 풀 객체를 이용해 바로 질의를 실행한다.
    pool.query(`insert into pms2_member(email,mid,pwd)
                values (?,?,password(?))`
                ,[email,id,password]
                , function(err,results){
                    if(err){
                        res.end('db조회중 예외발생'+err);
                        return;
                    }
                    res.write(`등록성공`); 
                    res.end(); // end()에서 꼭 출력할 필요없다~
                });
});

app.get('/member/update',(req,res)=>{
    var id = req.query.id,
    email = req.query.email,
    password = req.query.password;
    
    res.writeHead(200,{ 'Content-Type' : 'text/plain;charset=UTF-8'});
    // 커넥션 풀 객체를 이용해 바로 질의를 실행한다.
    pool.query('update pms2_member set email=? where mid=?'
                ,[email,id]
                , function(err,results){
                     if(err){
                         res.end('db조회중 예외발생');
                         return;
                     }
                     console.log('결과를 가져왔습니다.');
                     res.write(`등록성공했습니다.`); 
                     res.end(); // end()에서 꼭 출력할 필요없다~
                });
});

app.get('/member/delete',(req,res)=>{
   
    var id = req.query.id;
    res.writeHead(200,{ 'Content-Type' : 'text/plain;charset=UTF-8'});
    // 커넥션 풀 객체를 이용해 바로 질의를 실행한다.
    pool.query('delete from pms2_member where mid=?'
                ,[id]
                , function(err,result){
                     if(err){
                         res.end('db조회중 예외발생'+err);
                         return;
                     }
                 
                 res.write(`삭제성공`); 
                 res.end(); // end()에서 꼭 출력할 필요없다~
                });
});

app.get('/hello',(req,res)=>{
   res.write(`${urlInfo.query.name}님 반갑습니다.`);
   res.end();
});

app.listen(8000,()=>{
    console.log("리쓴되었을때 실행될 함수~");
});

