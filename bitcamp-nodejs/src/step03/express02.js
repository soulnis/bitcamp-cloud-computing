// 주제: 요청핸들러를 관리 + 호출하는 모듈

const http = require('http')
const url = require('url')

//자신만의 각각 서버를 가질수있다.
//여러개의 서버를 가질수있다 함수로 객체를 리턴하면
module.exports = function(){
    
 return {
    requestHandlerMap :{},
    add(url,handler){ //handler == callback
        this.requestHandlerMap[url] = handler; // 요청으로 들어온 url이름으로 핸들러를 저장
    },
    getHandler(url){  // url을 불러드림
        return this.requestHandlerMap[url];
    },
    listen(port,callback){
        var mapper = this;
        const server = http.createServer((req,res) => {
            // url 파싱
            var urlInfo = url.parse(req.url,true);
            
            if(urlInfo.pathname === '/favicon.ico'){
                res.end();
                return;
            }
            res.writeHead(200,{
                'Content-Type' : 'text/plain;charset=UTF-8'
            });
            // this를 쓰지못하는이유는 매서드안에 매서드라서 listen을 가르킴 arrow함수라서 this는 
            // global을 가르킴
            var handler = mapper.getHandler(urlInfo.pathname);
            
            if(handler){
                try{
                    handler(urlInfo,req,res);
                }catch(e){
                    res.end('실행중 오류발생');
                }
            } else {
                res.end('해당 URL을 지원하지 않습니다');
                return;
            }
        })
        
        server.listen(port,callback);
    }
  };

}
