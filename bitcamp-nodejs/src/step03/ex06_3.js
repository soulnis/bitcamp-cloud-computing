// 주제: 템플릿 엔진 사용 - 서버와 웹 응답에 적용하기

const express = require('express')
const memberdao = require('./memberdao')
// 템플릿 엔진 모듈을 로딩한다
const handlebar = require('handlebars')


const app = express()


// 템플릿를 정의한다
var templateSrc =
'<html>\
<head>\
<title>테스트</title>\
</head>\
<body>\
<h1>환영합니다!</h1>\
<p>{{name}}님 안녕하세요</p>\
</body>\
</html>';
// 템플릿 소스에 데이터를 삽입하여 최종 결과를 만들어 낼 함수를 준비한다.
var templateFn= handlebar.compile(templateSrc);


app.get('/hello',(req,res)=>{
    // 템플릿 함수를 호출하여 소스로부터 결과를 얻는다.
    // => 소스에 삽입될 데이터를 파라미터로 넘긴다.
   res.writeHead(200,{ 'Content-Type' : 'text/html;charset=UTF-8'});
   var resultStr = templateFn(req.query);
   res.write(resultStr);
   res.end();
});

app.listen(8000,()=>{
    console.log("리쓴되었을때 실행될 함수~");
});

