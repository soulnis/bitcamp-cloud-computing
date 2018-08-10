// 주제: 요청정보 다루기 - POST 요청 다루기

const http = require('http')
const url = require('url')
const querystring = require('querystring');


const server = http.createServer((req,res) => {
    console.log('클라이언트가 요청받음');
    var urlInfo = url.parse(req.url,true);
    
    if(urlInfo.pathname !== '/'){
        res.end();
        return;
    }
    res.writeHead(200,{
        'Content-Type' : 'text/plain;charset=UTF-8'
    })
    if(req.method !== 'POST'){
        res.end('GET 요청을 지원하지 않습니다.');
        return;
    }

    // POST 요청 데이터 처리
    // 1) 클라이언트가 보낸 데이터를 일부 읽을 떄 마다 발생하는 이벤트이다.
    //  주의!!
    //  클라이언트가 보낸 데이터를 모두 읽었을때 발생하는 이벤트가 아니다.
    //  즉 클라이언트가 보낸 데이터를 한번에 모두 읽는게 아니라 
    //  일부 읽을 때 마다 알려주는 방식으로 동작한다.
    // => 따라서 데이터를 읽었으면  'data'이벤트가 발생하고
    //    그이벤트에 등록된 함수가 있다면 호출해준다.
    // => 리스터(이벤트 핸들러)에 넘어오는 파라미터는 클라이언트가 보낸
    //    데이터의 일부이다.
   
    var data = '';
    // on('data'function)과 on('end',function)은 한 셋트이다.
    req.on('data',(chunk)=>{ //chunk는 일부데이터를 뜻한다.
        data += chunk;
    });
    //데이터를 모두 읽었을 때 응답을 완료해야한다.
    req.on('end',function(){
        // 읽은 데이터를 사용하기 좋게 key/value분리(parse)한다.
        var params = querystring.parse(data);
        res.write(`name =${params['name']}`);
        res.write(`age = ${params.age}`);
        res.end(); // end()에서 꼭 출력할 필요없다~
    })
    
    // 데이터에 호출 될 매서드를 등록한 후
    // 다음과 같이 바로 응답을 완료하면 안된다.
    // res.end();
});

server.listen(8000,()=>{
    console.log('서버 시작!!!');
})


