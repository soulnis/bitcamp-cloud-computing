// 주제: 템플릿 엔진 사용 - 외부 템플릿 파일 사용

const express = require('express')
const memberdao = require('./memberdao')
const handlebar = require('handlebars')
const fs = require('fs')

//외부 파일의 경로를 다룰 때 사용할 모듈
const path = require('path');

// 외부 템플릿 파일의 경로 설정하기
var templatePath = path.join(__dirname,'ex06_4_template.html');

//비동기 방식으로 템플릿 파일의 내용을 읽어 들인다
//=> 서버를 시작한 후 당장 템플릿 함수를 사용할 게 아니기 때문에
// 어느정도 시간적인 여유가 있다.
//=> 따라서 파일을 읽어 들일 때 비동기 방식으로 읽어서 
// 템플릿 함수를 만들더라도 문제가 없을 것이다.
fs.readFile(templatePath,(err,data)=>{
    // data는 버퍼라 스트링으로 바꾸기위해 .toString 해줘야한다
    var templateFn= handlebar.compile(data.toString());
});
const app = express()



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

