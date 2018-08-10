// 주제: DAO 모듈 만들기
var pool;

exports.setConnectionPool = (connetionPool)=>{
    pool = connetionPool;
};

exports.list = function(pageNo = 1,pageSize = 3,handler){
    var startIndex = (pageNo -1)*pageSize; //시작인덱스 db는 0부터 시작하기떄문에 -1해줌
    console.log(startIndex);
    // 커넥션 풀 객체를 이용해 바로 질의를 실행한다.
    pool.query('select * from pms2_member limit ?,?'
        ,[startIndex,pageSize]
        , function(err,results){
            handler(err,results); // return을 못하니까 콜백함수로 대신 호출해준다.
          });
    
};
exports.view = function(id,handler){
    pool.query('select * from pms2_member where mid =?'
            ,[id]
            ,function(err,result){
                handler(err,result);
            });
};

exports.add = function(data,handler){
    pool.query(`insert into pms2_member(email,mid,pwd)
            values (?,?,password(?))`
            ,[data.email,data.id,data.password]
            , function(err,result){
                handler(err,result);
            });
};

exports.update = function(data,handler){
    pool.query('update pms2_member set email=? where mid=?'
            ,[data.email,data.id]
            , function(err,result){
                handler(err,result);
            });
}
exports.remove = function(data, handler){
    pool.query('delete from pms2_member where mid=?'
            ,[data.id]
            , function(err,result){
              handler(err,result);
            });
}