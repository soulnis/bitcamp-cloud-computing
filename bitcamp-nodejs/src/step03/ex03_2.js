// 주제: 요청정보 다루기 - URL 분석하기

const http = require('http')
// URL 분석에 사용할 모듈 설정
const url = require('url')


const server = http.createServer((req,res) => {

    console.log('클라이언트가 요청받음');

    // 클라이언트가 요청한 url을 보기
    //=> http:///localhost:8000/aaa/bbb/ccc
    console.log(req.url); // => /aaa/bbb/ccc 를 출력
    
   
    
    res.writeHead(200,{
        'Content-Type' : 'text/plain;charset=UTF-8'
    })
    
     // url 분석기를 이용하면 url를 분석 해 보자
    var urlInfo = url.parse(req.url);
    //http://localhost:8000/aaa/bbb/ccc?name=200&age=20
    res.write(req.url+'\n'); ///aaa/bbb/ccc?name=200&age=20
    res.write('href='+urlInfo.href+'\n'); // href=/aaa/bbb/ccc?name=200&age=20
    res.write('pathname='+urlInfo.pathname+'\n'); //pathname=/aaa/bbb/ccc
    res.write('search='+urlInfo.search+'\n'); // search=?name=200&age=20
    res.write('query='+urlInfo.query+'\n'); // query=name=200&age=20
    //멀티라인은 맨끝에다가 역슬래쉬(\)를 해주면 연결된다.
    res.end(); // end()에서 꼭 출력할 필요없다~
});

server.listen(8000,()=>{
    console.log('서버 시작!!!');
})


