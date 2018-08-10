// 주제: express 사용하기 - 요청핸들러를 모듈로 분리하기

const express = require('express')
// POST 요청을 데이터를 처리할 모듈 로딩
const bodParser = require('body-parser')
const consolidate = require('consolidate')
const app = express();

app.engine('html',consolidate.handlebars);

app.set('view engine','html'); //위에 핸들바를 사용하는것

const path = require('path'); 
app.set('views', path.join(__dirname,'templates'));

const postParameterParser = bodParser.urlencoded({extended:false})



//정적 HTML 파일 처리
app.use(express.static('public')); //정적 html 파일이 public에 있따.

// POST 요청 파라미터 분석기를 바로 Express 웹서버 객체에 등록한다.
app.use(postParameterParser);

// /member/* URL을 처리할 라우터와 '/team/*' URL을 처리할 라우터를 로딩한다.
var memberRouter = require('./member')
var teamRouter = require('./team')

// => 라우터를 express의 웹서버에 등록한다.
app.use('/member',memberRouter);
app.use('/team',teamRouter);


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
