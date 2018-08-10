// 주제: 요청정보 다루기 - 요청 파라미터값 꺼내기

const http = require('http')
// URL 분석에 사용할 모듈 설정
const url = require('url')


const server = http.createServer((req,res) => {

    console.log('클라이언트가 요청받음');

    // query 객체에서 파라미터 명을 사용하여 값을 꺼내고 싶다면,
    // 두 번쨰 파라미터의 값을 true로 설정하라
    var urlInfo = url.parse(req.url,true);
    
    res.writeHead(200,{
        'Content-Type' : 'text/plain;charset=UTF-8'
    })
    
    // 파라미터 값을 꺼낼 때는 그냥
    // "query.파라미터명" 으로 지정한다.
    res.write(`name=${urlInfo.query.name} \n`);
    res.write(`age=${urlInfo.query.age}\n`);
    res.end(); // end()에서 꼭 출력할 필요없다~
});

server.listen(8000,()=>{
    console.log('서버 시작!!!');
})


