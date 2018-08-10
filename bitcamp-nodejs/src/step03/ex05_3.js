// 주제: 여러개 요청 처리 하기- 조건 문을 없애고 URL과 함수를 자동 연결하기
// => 요청 핸들러( 요청이 들어왔을 때 호출되는 함수)를 좀 더 관리하기 쉽게
//    등록을 자동화 한다.
// => 짜는건 어려워도 짜고나서 유지보수에 탁월하게 좋다 
const http = require('http')
const url = require('url')
const mysql = require('mysql');

var pool = mysql.createPool({
    connectionLimit : 10, // 커넥션을 최대 10개까지만 생성 10개를 다쓰면 반납받을때까지 기다려야함
    host : '52.79.239.97', // 호스트가 localhost면 생략 가능
    // port : '3306',   // 포트 번호가 3306이면 생략가능
    database : 'studydb',
    user : 'study',
    password : '1111'
 });

const express = {
    reqMap :{},
    add(url,handler){ //handler == callback
        this.reqMap[url] = handler; // 요청으로 들어온 url이름으로 핸들러를 저장
    },
    getHandler(url){  // url을 불러드림
        return this.reqMap[url];
    }
}

express.add('/member/list',(urlInfo,req,res)=>{
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
express.add('/member/add',(urlInfo,req,res)=>{
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
express.add('/member/update',(urlInfo,req,res)=>{
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
express.add('/member/delete',(urlInfo,req,res)=>{
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

express.add('/hello',(urlInfo,req,res)=>{
   res.write(`${urlInfo.query.name}님 반갑습니다.`);
   res.end();
});

const server = http.createServer((req,res) => {
    // url 파싱
    var urlInfo = url.parse(req.url,true);
    
    if(urlInfo.pathname === '/favicon.ico'){
        res.end();
        return;
    }
    res.writeHead(200,{
        'Content-Type' : 'text/plain;charset=UTF-8'
    });
    
    var handler = express.getHandler(urlInfo.pathname);
      
    if(handler){
       try{
           handler(urlInfo,req,res);
       }catch(e){
           res.end('실행중 오류발생');
       }
        } else {
            res.end('해당 URL을 지원하지 않습니다');
            return;
        }
})

server.listen(8000,()=>{
    console.log('서버 시작!!!');
})

