// 주제: express 사용하기 - 템플릿 엔진 지정하기

// => express 모듈로딩
const express = require('express')
// POST 요청을 데이터를 처리할 모듈 로딩
const bodParser = require('body-parser')
// 통합 템플릿 엔진 관리자 모듈 로딩
// => 템플릿 엔진이 아니라 템플릿 엔진을 중간에서 관리해주는 역활을 수행한다.
const consolidate = require('consolidate')
const app = express();

//Express에 템플릿 엔진을 등록한다.
//=> consolidate를 통해 handlebars를 지정하면 
// 이 템플릿 관리자는 Node 모듈에서 handlebars를 찾아 리턴한다.
//=> Express에 여러 개의 엔진을 등록할 수 있다.
//=> 다음은 ".html" 템플릿 파일을 처리할 엔진을 등록한다.
// 예) template01.html 또느 ejs면 'ejs' .ejs파일로 등록한다
app.engine('html',consolidate.handlebars);

//템플릿 파일의 확장자를 생략했을 때
//기본으로 사용할 템플릿 파일의 확장자를 설정한다.
app.set('view engine','html'); //위에 핸들바를 사용하는것

//템플릿 파일이 있는 디렉토리 경로를 지정한다.
//=> 디렉토리가 한 개일 때는 문자열로 지정한다.
//=> 디렉토리가 여러 개일 때는 배열에 담아 지정한다.
const path = require('path'); 
app.set('views', path.join(__dirname,'templates'));

const postParameterParser = bodParser.urlencoded({extended:false})



//정적 HTML 파일 처리
app.use(express.static('public')); //정적 html 파일이 public에 있따.

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

app.get('/test03',(req,res)=>{
    res.render('template01',req.query);
});

// => 서버 실행
app.listen(8000,()=>{
    console.log('서버실행중');
})
