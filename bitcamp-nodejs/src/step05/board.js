// 주제: DAO 분리

const mysql = require('mysql')
const express = require('express')
const boarddao = require('./boarddao')
const router = express.Router()

var pool = mysql.createPool({
    connectionLimit : 10, // 커넥션을 최대 10개까지만 생성 10개를 다쓰면 반납받을때까지 기다려야함
    host : '52.79.239.97', // 호스트가 localhost면 생략 가능
    // port : '3306',   // 포트 번호가 3306이면 생략가능
    database : 'studydb',
    user : 'study',
    password : '1111'
});

boarddao.setConnectionPool(pool);

router.get('/form',(req,res)=>{
    res.render('boardForm');
});

// get핸들러에처리하기
router.get('/list',(req,res)=>{
    var pageNo = parseInt(req.query.pageNo) || 1;
    var pageSize = parseInt(req.query.pageSize) || 3;
    boarddao.list(pageNo,pageSize,(err,results)=>{
        if(err){
            res.end('DB조회중 예외발생');
            return;
        }
        console.log(results);
        res.render('boardList',{ board : results}); 
    });
});

router.get('/view',(req,res)=>{
    boarddao.view(req.query.bno,(err,result)=>{
        if(err){
            res.end('DB조회중 예외발생');
            return;
        }
        res.render('boardView',{ board : result}); // end()에서 꼭 출력할 필요없다~
    });
});

router.post('/add',(req,res)=>{
    res.writeHead(200,{ 'Content-Type' : 'text/plain;charset=UTF-8'});
    boarddao.add(req.body,(err,result)=>{
        if(err){
            res.end('db조회중 예외발생'+err);
            return;
        }
        res.write(`등록성공`); 
        res.end(); // end()에서 꼭 출력할 필요없다~
    });
   
});

router.post('/update',(req,res)=>{
    res.writeHead(200,{ 'Content-Type' : 'text/plain;charset=UTF-8'});
    boarddao.update(req.body,(err,result) => {
             if(err){
                     res.end('DB조회중 예외발생');
                     return;
                     }
             console.log('결과를 가져왔습니다.');
             res.write(`등록성공했습니다.`); 
             res.end(); // end()에서 꼭 출력할 필요없다~
    })
});

router.get('/delete',(req,res)=>{
    res.writeHead(200,{ 'Content-Type' : 'text/plain;charset=UTF-8'});
    boarddao.remove(req.query,(err,result)=>{
        if(err){
            res.end('DB조회중 예외발생'+err);
            return;
        }
        res.write(`삭제성공`); 
        res.end(); // end()에서 꼭 출력할 필요없다~
    });
});

module.exports = router;

