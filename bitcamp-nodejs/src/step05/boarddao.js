// 주제: DAO 모듈 만들기
var pool;

exports.setConnectionPool = (connetionPool)=>{
    pool = connetionPool;
};

exports.list = function(pageNo = 1,pageSize = 3,handler){
    var startIndex = (pageNo -1)*pageSize; //시작인덱스 db는 0부터 시작하기떄문에 -1해줌
    console.log(startIndex);
    var stmt = `select bno,titl title,cdt as cnt 
                from pms2_board
                limit ?,?`;
    pool.query(stmt
        ,[startIndex,pageSize]
        , function(err,results){
            handler(err,results); // return을 못하니까 콜백함수로 대신 호출해준다.
          });
    
};

exports.view = function(bno,handler){
    console.log(bno);
    var stmt = `select bno,titl title,cont content,cdt 
                from pms2_board where bno=?`;
    pool.query(stmt
            ,[bno]
            ,function(err,result){
                handler(err,result);
            });
};

exports.add = function(data,handler){
    var stmt = `insert into pms2_board(titl,cont,cdt)
      values(?,?,now())`;
    pool.query(stmt
            ,[data.title,data.content]
            , function(err,result){
                handler(err,result);
            });
};

exports.update = function(data,handler){
    var stmt = `update pms2_board 
     set titl=?, cont=?, cdt=now() where bno=?`;
    pool.query(stmt
            ,[data.title,data.content,data.bno]
            , function(err,result){
                handler(err,result);
            });
}
exports.remove = function(data, handler){
    var stmt = `delete from pms2_board 
  where bno=?`;
    pool.query(stmt
            ,[data.no]
            , function(err,result){
              handler(err,result);
            });
}