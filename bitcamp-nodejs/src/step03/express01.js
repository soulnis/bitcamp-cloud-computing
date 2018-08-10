// 주제: 요청핸들러를 관리하는 모듈

module.exports = {
    reqMap :{},
    add(url,handler){ //handler == callback
        this.reqMap[url] = handler; // 요청으로 들어온 url이름으로 핸들러를 저장
    },
    getHandler(url){  // url을 불러드림
        return this.reqMap[url];
    }
};


