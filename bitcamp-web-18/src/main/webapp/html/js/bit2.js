bit = function(value){
    var e = {};
    if(value instanceof HTMLElement){
        e = value;
    } else if (value.startsWith('<')){
        e = document.createElement(value.substr(1,value.length - 2));
    } else {
    e = document.querySelector(value);
    }
    
    e.html = function(value){
        e.innerHTML = value;
        return e;
    } 
    
    e.append = function(value){
        e.appendChild(value);
        return e;
    }
    e.appendTo = function(parent){
        parent.appendChild(e);
        return e;
    }
    e.attr = function(name,value){
        if(value == undefined){
           return e.getAttribute(name);
        }else{
            e.setAttribute(name,value);
        }
        return e;
    }
    e.removeattr= function(name){
        e.removeAttribute(name);
    }
        return e;
}


function parseQuery(url){
    var paramMap ={};
   
    var qs = url.split('?'); // [0]은 url 부분 [1]은 qeury string부분이 들어감
    if(qs.length > 1){ // qs.length가 1 이하이면 ?문이후 문이 없다는것 
        var params = qs[1].split('&'); // &으로 자르기
        for(var param of params){
            var kv = param.split('='); // =으로 자르면 앞은 [0]은 키값 [1]은 벨류값이 됨
            paramMap[kv[0]] =kv[1];
        }
    }
    return paramMap;

}

bit.ajax = function(url,settings){
    
    //settings에 지정하지않았을경우 default값
    if(settings == undefined)
        settings = {};
    if(settings.dataType == undefined)
        settings.dataType = 'text';
    if(settings.method == undefined)
        settings.method = 'GET';
    // 1) 객체생성
    var xhr = new XMLHttpRequest();
    // 2) 서버와의 요청후 콜백함수
    xhr.onreadystatechange = function(){
    
        if(xhr.readyState < 4) return;
        if(xhr.status !== 200){
            if(settings.error)
                error();
            return;
        }
        //서버의 응답결과가 정상인지 오류인지 확인하는 방법
        //status 코드 값을 확인한다.
        if(xhr.status == 200){
        //서버로부터 받아온 데이터
        console.log(xhr.responseText);
        
        let data = xhr.responseText;
        if (settings.success){
            if(settings.dataType == 'json'){
                data = JSON.parse(xhr.responseText); //JSON은 자바스크립트 객체로 변환해줌
            }
            //들어온 데이터를 settings의 등록해둔 콜백함수실행과 파라미터로 서버로부터받아온 데이터를 넣어줌
            settings.success(data);
            
            }
        // done() 함수에 의해 두 번째 success함수가 등록되어 있다면 그함수를 호출한다.
        if(done){ 
            //등록해두었던 콜백함수 실행 &파라미터로 서버로부터 받아온 data를 넣어줌 
            done(data);
        }
        }
    };
  
    //settings에 서버로 보낼 data로 보낼꺼라면 가 있다면 url에 포함해야한다.
    //url? 쿼리가아닌 data객체 넣어서 GET방식을 실행할경우 사용함
    if(settings.data){
        var qs ='';
        for(var propName in settings.data){
            qs += `&${propName}=${settings.data[propName]}`;
        }
        //url에 ?가 없다면 -1이 나오고 ?를넣어줌
        if(url.indexOf('?') == -1)
            url += '?';
        url += qs;
    }
    console.log(url);
    //데이터 설정
    xhr.open(settings.method,url,true);
    //요청
    xhr.send();
    
    // XMLHttpRequest 객체를 리턴하기전에 함수를 추가한다.
    
    let done;
    //done을추가하고 설정해주고 취소시 객체xhr.done()으로 success가 아닌 done(콜백함수)를 설
    //정 하게함듬
    // done으로 콜백함수실행
    xhr.done = function(func){
       done = func;
    };
    //done을 사용하기위해서 객체를 리턴함
    return xhr;
};
//getJSON으로 get을통해 JSON통신은 더 쉽게 사용하기위해서 사용
bit.getJSON = function(url,p2,p3){
    //url이 아닌 data객체로 넣을경우를 대비해서 data객체생성
    let data = {};
    //success은 성공시 콜백함수를 저장해둠
    let success = null;
    //2번쨰 들어오는 파라미터가 함수가 아니라면
    
    //파라미터가 2개이상인경우에만 여기를 돌게함
    //url에 쿼리스트링을 넣으면 2개고 쿼리스트링을 객체로 보낼경우 파라미터가 3개이다.
    if (arguments.length > 1){
        if (typeof p2 == "function") success = p2;
        else data = p2;
        if (typeof p3 == "function") success = p3;
    }
    
    return bit.ajax(url,{
        dataType: 'json',
        data: data,
        success: success
    });
}

$ = bit;