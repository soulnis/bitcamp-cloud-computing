// 주제: SQL 요청 처리하기 - 회원 변경하기
// [실행 URL]
// =>http://localhost:8000/member/update?id=user130&email=user@pass.com&password=1111
// [출력결과]
// 변경성공입니다.

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
    
    // pathname은 ? 앞에 내용 aaa/bbb/ccc이렇게
    if(urlInfo.pathname === '/favicon.ico'){
        res.end();
        return;
    }
    
    res.writeHead(200,{
        'Content-Type' : 'text/plain;charset=UTF-8'
    })

    if(urlInfo.pathname !== '/member/update'){
    res.end('해당 URL을 지원하지 않습니다.');
    return;
    } 
   
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


server.listen(8000,()=>{
    console.log('서버 시작!!!');
})


