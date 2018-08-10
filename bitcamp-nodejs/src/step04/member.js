// /memeber/* 멤버요청을 처리할 라우터 만들기

const express = require('express')
const bodParser = require('body-parser')
const router = express.Router();

//어느 라우터가 실행됫는지 실행함 일종의 로그남기는 함수
router.use((req, res, next)=>{
  console.log('member.js 실행');
  console.log('Time: ', Date.now());
  next();
});


// '/member/list' 요청이 들어왔을 떄 호출될 매서드 등록
router.get('/list',(req,res)=>{
    res.writeHead(200,{ 'Content-Type' : 'text/plain;charset=UTF-8'});
    res.write('멤버목록입니다.');
    res.end();
});

router.get('/view',(req,res)=>{
    res.writeHead(200,{ 'Content-Type' : 'text/plain;charset=UTF-8'});
    res.write('멤버상세정보입니다.');
    res.end();
});

module.exports = router;