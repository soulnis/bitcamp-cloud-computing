// 주제: express 사용하기 - 서버실행하기

// => express 모듈로딩
const express = require('express');
// POST 요청을 데이터를 처리할 모듈 로딩
const bodParser = require('body-parser');

// POST 요청 데이터를 분석할 객체를 준비한다.
const postParameterParser = bodParser.urlencoded({extended:false})

// => 웹서버  객체 준비
const app = express();

// POST 요청 파라미터 분석기를 바로 Express 웹서버 객체에 등록한다.
app.use(postParameterParser);

// => URL에 대해 함수를 연결한다.

app.get('/test01',(req,res)=>{
    res.writeHead(200,{ 'Content-Type' : 'text/plain;charset=UTF-8'});
    res.write(`name =${req.query.name}`);
    res.write(`age=${req.query.age}`);
    res.end();
});

app.post('/test02',(req,res)=>{
    res.writeHead(200,{ 'Content-Type' : 'text/plain;charset=UTF-8'});
    res.write(`name =${req.body.name}`);
    res.write(`age=${req.body.age}`);
    res.end();
});
// => 서버 실행
app.listen(8000,()=>{
    console.log('서버실행중');
})
