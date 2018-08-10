// 주제: 요청정보 다루기 - Quize : 계산기를 만들자
// 실행 URL
// => 100 + 200 :http://localhost:8000/calc?a=100&b=200&op=%2b
// => 100 - 200 :http://localhost:8000/calc?a=100&b=200&op=-
// => 100 * 200 :http://localhost:8000/calc?a=100&b=200&op=*
// => 100 / 200 :http://localhost:8000/calc?a=100&b=200&op=/
// [출력결과]
// 100 + 200 =300 이게 화면에 출력
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

//    if(urlInfo.pathname === '/calc'){
//        
//        var a = Number(urlInfo.query.a) ;
//        var b = parseInt(urlInfo.query.b) ;
//        var op = urlInfo.query.op ;
//        var sum = 0;
//        console.log(op);
//        if(op === '+'){
//            sum = a + b;
//            res.write(`${a}+${b}=${sum}`);
//        } else if(op === '-'){
//            sum = a - b;
//            res.write(`${a}-${b}=${sum}`);
//        } else if(op === '*'){
//            sum = a * b;
//            res.write(`${a}*${b}=${sum}`);
//        } else if(op === '/'){
//            sum = a / b;
//            res.write(`${a}/${b}=${sum}`);
//        } else{
//            res.write(`지원하지 않는 연산자입니다.`);
//        }
//    } else {
//        res.write('해당 URL을 지원하지 않습니다.')
//    }
//    res.end(); // end()에서 꼭 출력할 필요없다~
//});

if(urlInfo.pathname === '/calc'){
    
    var a = Number(urlInfo.query.a) ;
    var b = parseInt(urlInfo.query.b) ;
    var op = urlInfo.query.op ;
    var result = 0
    
    switch(op){
        case '+' : result = a+b; break;
        case '-' : result = a-b; break;
        case '*' : result = a*b; break;
        case '/' : result = a/b; break;
        default :
            res.write(`${op} 연산자를 지원하지 않습니다.`);
            res.end();
    }
} else {
    res.write('해당 URL을 지원하지 않습니다.')
}
    res.write(`${a}${op}${b} =${result}`); 
res.end(); // end()에서 꼭 출력할 필요없다~
});


server.listen(8000,()=>{
    console.log('서버 시작!!!');
})


