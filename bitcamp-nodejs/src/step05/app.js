// 주제: 회원관리, 팀관리, 게시판, 강좌관리 웹 애플리케이션 만들기

const express = require('express')
// POST 요청을 데이터를 처리할 모듈 로딩
const bodParser = require('body-parser')
const consolidate = require('consolidate')
const path = require('path'); 
const app = express();

// imag,html,css,js등의 파일등을 클라이언트 요청에 따라 직접 보내준다. 라우터를 통해서가아니라
app.use(express.static(path.join(__dirname,'public')));

app.engine('html',consolidate.handlebars);

app.set('view engine','html'); //위에 핸들바를 사용하는것

app.set('views', path.join(__dirname,'template'));

const postParameterParser = bodParser.urlencoded({extended:false})

//정적 HTML 파일 처리
app.use(express.static('public')); //정적 html 파일이 public에 있따.

// POST 요청 파라미터 분석기를 바로 Express 웹서버 객체에 등록한다.
app.use(postParameterParser);

// => 라우터를 express의 웹서버에 등록한다. 디스패쳐서블릿이라 생각하면된다.
app.use('/member',require('./member'));
app.use('/board',require('./board'));
// 메인인덱스
app.get('/hello',(req,res)=>{
    res.writeHead(200,{ 'Content-Type' : 'text/plain;charset=UTF-8'});
    res.write('Hello');
    res.end();
});


// => 서버 실행
app.listen(8000,()=>{
    console.log('서버실행중');
})
