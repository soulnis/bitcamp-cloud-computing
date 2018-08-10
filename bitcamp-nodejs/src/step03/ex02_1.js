// 주제:클라이언트에게 출력하기


const http = require('http')

const server = http.createServer((req,res) => {

    console.log('클라이언트가 요청받음');

    res.settype
    res.end("안녕!");
});

server.listen(8000,()=>{
    console.log('서버 시작!!!');
})


