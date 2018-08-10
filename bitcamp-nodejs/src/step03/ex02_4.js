// 주제:클라이언트에게 출력하기 - 멀티라인 처리

const http = require('http')

const server = http.createServer((req,res) => {

    console.log('클라이언트가 요청받음');
    
    // 출력하는 콘텐트의 타입을 설정해야 한글이 꺠지지 않는다.
    // 응답 헤더로 'Content-Type'을 지정하라.
    
    res.writeHead(200,{
        'Content-Type' : 'text/html;charset=UTF-8'
    })
    //멀티라인은 맨끝에다가 역슬래쉬(\)를 해주면 연결된다.
    res.write('<html>\n\
<head>\n\
<title>Node.js</title>\n\
</head>\n\
<body>\n\
<h1>안녕</h1>\n\
</body>\n\
</html>\n');
    res.end(); // end()에서 꼭 출력할 필요없다~
});

server.listen(8000,()=>{
    console.log('서버 시작!!!');
})


