// 주제: 코드를 모듈로 분리 - 요청 핸들러를 호출하는 코드 분리

const mysql = require('mysql')
const express = require('./express02')
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

// url로 핸들러등록해두면 익스프레스가 요청을 함
app.add('/member/list',(urlInfo,req,res)=>{
    var pageNo = parseInt(urlInfo.query.pageNo) || 1;
    var pageSize = parseInt(urlInfo.query.pageSize) || 3;
    var startIndex = (pageNo -1)*pageSize; //시작인덱스 db는 0부터 시작하기떄문에 -1해줌

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

app.add('/member/add',(urlInfo,req,res)=>{
    var id = urlInfo.query.id;
    var email = urlInfo.query.email;
    var password = urlInfo.query.password;

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

app.add('/member/update',(urlInfo,req,res)=>{
    var id = urlInfo.query.id,
    email = urlInfo.query.email,
    password = urlInfo.query.password;

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

app.add('/member/delete',(urlInfo,req,res)=>{
    var id = urlInfo.query.id;

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

app.add('/hello',(urlInfo,req,res)=>{
   res.write(`${urlInfo.query.name}님 반갑습니다.`);
   res.end();
});

app.listen(8000,()=>{
    console.log("리쓴되었을때 실행될 함수~");
});

app2.add('/member/list',(urlInfo,req,res)=>{
    var pageNo = parseInt(urlInfo.query.pageNo) || 1;
    var pageSize = parseInt(urlInfo.query.pageSize) || 3;
    var startIndex = (pageNo -1)*pageSize; //시작인덱스 db는 0부터 시작하기떄문에 -1해줌

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

app2.add('/member/add',(urlInfo,req,res)=>{
    var id = urlInfo.query.id;
    var email = urlInfo.query.email;
    var password = urlInfo.query.password;

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

app2.add('/member/update',(urlInfo,req,res)=>{
    var id = urlInfo.query.id,
    email = urlInfo.query.email,
    password = urlInfo.query.password;

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

app2.add('/member/delete',(urlInfo,req,res)=>{
    var id = urlInfo.query.id;

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

app2.add('/hello',(urlInfo,req,res)=>{
   res.write(`${urlInfo.query.name}님 반갑습니다.`);
   res.end();
});

app2.listen(8001,()=>{
    console.log("리쓴되었을때 실행될 함수~");
});

