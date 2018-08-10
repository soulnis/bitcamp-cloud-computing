// 주제: 여러개 요청 처리 하기- 회원목록/등록/변경/삭제 하기를 한파일에 다넣기
// [실행 URL]
// =>http://localhost:8000/member/delete?id=user130

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


const server = http.createServer((req,res) => {
    var urlInfo = url.parse(req.url,true);
    
    if(urlInfo.pathname === '/favicon.ico'){
        res.end();
        return;
    }
    res.writeHead(200,{
        'Content-Type' : 'text/plain;charset=UTF-8'
    });
      
    switch (urlInfo.pathname) {
       case '/member/list':
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
            break;
            
       case '/member/add':
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
           break;
       case '/member/update':
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
           break;
       case '/member/delete':
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
           break;
       default:
        res.end('해당없습니다.');
        break;
       } 
    });


server.listen(8000,()=>{
    console.log('서버 시작!!!');
})


