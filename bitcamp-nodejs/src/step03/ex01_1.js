// 주제: http 서버 만들기 - 서버 실행

// 1) 모듈 로딩
const http = require('http')

// 2) HTTP 서버 객체 생성
// => 파라미터는 요청이 들어왔을때 호출될 함수
const server = http.createServer((req,res) => {

    console.log('클라이언트가 요청받음');
    console.log('request객체',req);
    console.log('response',res);
    // 웹 브라우저의 요청을 받았지만 응답을 하지않았기 때문에
    // 웹 브라우저는 계쏙 응답을 기다리는 상태에 있을 것이다.
});

// 3) HTTP 서버실행
// => listen(포트번호[,서버시작 된후 호출될 함수]); [,]은 선택사항 필수가아님
server.listen(8000,()=>{
    console.log('서버 시작!!!');
})

// 위를 두개한번에 묶인거다
//server.listen(8000);
//server.on("listening",()=>{})

