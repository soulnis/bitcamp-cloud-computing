// 주제: 요청정보 다루기

const http = require('http')

const server = http.createServer((req,res) => {

    console.log('클라이언트가 요청받음');

    // 클라이언트가 요청한 url을 보기
    //=> http:///localhost:8000/aaa/bbb/ccc
    console.log(req.url); // => /aaa/bbb/ccc 를 출력
    
    res.writeHead(200,{
        'Content-Type' : 'text/plain;charset=UTF-8'
    })
    //멀티라인은 맨끝에다가 역슬래쉬(\)를 해주면 연결된다.
    res.write('테스트');
    res.end(); // end()에서 꼭 출력할 필요없다~
});

server.listen(8000,()=>{
    console.log('서버 시작!!!');
})


