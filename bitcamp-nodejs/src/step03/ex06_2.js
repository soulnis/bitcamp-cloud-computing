// 주제: 템플릿 엔진 사용 - 서버와 웹 응답에 적용하기

const express = require('express')
const memberdao = require('./memberdao')
// 템플릿 엔진 모듈을 로딩한다
const handlebar = require('handlebars')


const app = express()


// 템플릿를 정의한다
var templateSrc = '{{name}}님안녕하세요!';
// 템플릿 소스에 데이터를 삽입하여 최종 결과를 만들어 낼 함수를 준비한다.
var templateFn= handlebar.compile(templateSrc);


app.get('/hello',(req,res)=>{
    // 템플릿 함수를 호출하여 소스로부터 결과를 얻는다.
    // => 소스에 삽입될 데이터를 파라미터로 넘긴다.
   res.writeHead(200,{ 'Content-Type' : 'text/plain;charset=UTF-8'});
   var resultStr = templateFn({name:'홍길동'})
   res.write(resultStr);
   res.end();
});

app.listen(8000,()=>{
    console.log("리쓴되었을때 실행될 함수~");
});

