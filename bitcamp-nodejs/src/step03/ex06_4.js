// 주제: 템플릿 엔진 사용 - 외부 템플릿 파일 사용

const express = require('express')
const memberdao = require('./memberdao')
const handlebar = require('handlebars')

//외부 파일의 경로를 다룰 때 사용할 모듈
const path = require('path');

// 외부 템플릿 파일의 경로 설정하기
var templatePath = path.join(__dirname,'ex06_4_template.html');

// 템플릿 파일을 읽어드릴 모듈을 로딩
const fs = require('fs')
console.log(fs);
// 동기식 방식 으로 템플릿 파일의 내용을 읽어 들인다. 
// => 동기 방식이기 때문에 파일을 다 읽은 후에 리턴한다. 블럭킹
// => 리턴 파일은 파일의 데이터이다.
var templateSrc =fs.readFileSync(templatePath);
console.log(templateSrc)
const app = express()

// 템플릿 소스에 데이터를 삽입하여 최종 결과를 만들어 낼 함수를 준비한다.
var templateFn= handlebar.compile(templateSrc.toString());


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

