// 주제: http 서버 만들기 - single thread
// => Node.js는 싱글쓰레드 (single thread)로 동작
// => 즉 한 클라이언트의 요청을 처리할 때까지 다른 클라이언트는 기다려야 한다.
// => 그래서 node.js의  http서버는 시간이 오래 걸리는 대량 동시 요청을 처리하기에 적합하지 않다.
//    그런용도가 아니다
// => 작업시간이 짧은 단타성 요청을 처리하기에 적합하다.


const http = require('http')

const server = http.createServer((req,res) => {

    console.log('클라이언트가 요청받음');
    
    // 클라이언트 요청이 들어오면 10초후에 응답해보자.
    // => 테스트 방법 : 웹 브라엊탭을 두개를 띄운 다음에 
    //    이 서버에 접속해 보라!
    setTimeout(()=>{
        res.end("응답했쪙");
    },10000)
    // 두개 요청이 들어면 두번째 요청은 10초후에 실행되서 20초후에 실행됨
    // queue 구조로 선입선출 클라이언트 요청을 한개씩 처리해낸다.
});

server.listen(8000,()=>{
    console.log('서버 시작!!!');
})


