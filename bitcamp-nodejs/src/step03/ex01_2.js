// 주제: http 서버 만들기 - 클라이언트에게 응답 완료하기

const http = require('http')

const server = http.createServer((req,res) => {

    console.log('클라이언트가 요청받음');
    console.log('request객체',req);
    console.log('response',res);
    
    // HTTP 응답을 완료해야 웹브라우저의 기다림이 멈춘다.
    res.end();
});

server.listen(8000,()=>{
    console.log('서버 시작!!!');
})


