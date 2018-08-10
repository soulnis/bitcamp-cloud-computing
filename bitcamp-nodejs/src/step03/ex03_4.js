// 주제: 요청정보 다루기 - url에 따라서 작업을 분리하기

const http = require('http')
// URL 분석에 사용할 모듈 설정
const url = require('url')


const server = http.createServer((req,res) => {
    var urlInfo = url.parse(req.url,true);
    
    // pathname은 ? 앞에 내용 aaa/bbb/ccc이렇게
    if(urlInfo.pathname === '/favicon.ico'){
        res.end();
        return;
    }
    console.log('클라이언트가 요청받음');

    res.writeHead(200,{
        'Content-Type' : 'text/plain;charset=UTF-8'
    })

    if(urlInfo.pathname === '/board/list'){
        res.write(`게시물 목록`);
    } else if(urlInfo.pathname === '/board/add') {
        res.write('게시물 등록');
    } else {
        res.write('해당 URL을 지원하지 않습니다.')
    }
    res.end(); // end()에서 꼭 출력할 필요없다~
});

server.listen(8000,()=>{
    console.log('서버 시작!!!');
})


