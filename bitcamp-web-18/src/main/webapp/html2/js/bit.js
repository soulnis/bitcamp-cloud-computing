"use strict"
//자주 사용하는 함수를 라이브러리화 시키자
let bit = function(value){
   var el = [];
   
   //오리지날 자바스크립트태그 라면 라이브러리에 조미료쳐줌
   
   //자바스크립트태그에 제이쿼리 함수추가하기
   if(value instanceof HTMLElement){
       el[0] = value;
       
   //value 값이 시작되는 명이 <일경우 태그생성한다는 것이다.
   } else if(value.startsWith('<')){
       el[0] = document.createElement(value.substr(1,value.length - 2));
           } else {
               var list = document.querySelectorAll(value);
               //selector로 찾은 태그들을 빈 애열로 옮긴다.
               for (var i = 0; i < list.length; i++){
                   el[i] = list[i];
               }
   }
   
   //e값이 없으면 즉시 리턴한다 배열이 0인경우 
   if(el.length == 0){
       return [];
   }
    
    // el배열에 함수를 추가함으로써 해당배열에 들어간 객체는 아래의 함수들을 사용함으로써
    // 일반 자바스크립트보다 훨씬 간편하게 HTML을 제어할수있다.
    el.html = function(vaule){
        //들어온 파라미터 갯수에 따라 getter,setter를 나눈다.
        //getter는 0개 , setter는 1개일때
        if(arguments.lnegth == 0){
            return el[0].innerHTML;
        }
        for (var e of el){
            e.innerHTML = vaule;
        }
        return el;
    };
    
    el.append = function(child){
        for(var e of el)
            e.appendChild(child);
        return el;
    };
    
    el.appendTO = function(parent){
        for(var e of el)
            parent[parent.length - 1].appendChild(e);
        return el;
    };
    
    el.attr = function(name,value){
        //getter 파라미터가 1개
        if(arguments.length < 2){
            return el[0].getAttribute(name);
        }
        for( var e of el)
            //setter 파라미터가 2개
            e.setAttribute(name,value);
        return el;
    };
    
    el.removeAttr =function(name){
        for(var e of el)
            e.removeAttribute(name);
        return el
    };
    
    // listener === handler
    el.on = function(name,p2,p3){
        // 파라미터 갯수로 메서드를 다르게 실행함
        // p2에 태그가 들어오면 부모태그에 미래에 생길 태그에 
        var selector = null;
        var handler =null;
        
        //아규먼트객체가 3개면 2번쨰는 HTMLElement고 3번째는 콜백함수 핸들러함수
        if (arguments.length == 2) handler = p2;
        if (arguments.length == 3){
            selector = p2;
            handler = p3;
        }
        
        for(var e of el){
            // selector가 지정되어 있지 않으면
            // 해당 태그에 대해 이벤트가 발생했을 때 핸들러를 호출한다.
            if(!selector){
                e.addEventListener(name,handler);
            } else {
                // selector가 지정되어 있으면
                // 핸들러를 호출하기전에 selector에 해당하는 것인지 검사 한다.
                // 함수가 먼저 호출되게 한다.
                e.addEventListener(name,function(event){
                    //현재 태그의 자식태그중에서 selector 조건에 해당되는 자식 태그들을 찾는다.
                    var selectorTargets = e.querySelectorAll(selector);
                    // 그 자식 태그들 중에 이 이벤트가 발생된 태그를 찾는다.
                    for (var target of selectorTargets){
                        // 만약 이벤트가 발생된 태그와 일치하는 자식태그가 있다면,
                        // 그제서야 핸들러를 호출해준다.
                        
                        if (event.target == target){
                            //클릭한 타켓태그랑 자식태그중 맞는것을 찾아서
                            //등록해둔 함수를 호출한다.
                            handler(event);
                            break;
                        }
                    }
                });
            }
        }
        
    };  
    
    el.click = function(handler){
        return el.on('click',handler);
    };
    
    el.css = function(name,value){
        //파라미터가 한 개면 css를 가져온다. get            
        if( arguments.length == 1){
            return el[0].style[name];
        }
        console.log(111);
        for (var e of el){
            e.style[name] = value;
        }
        console.log(1112);
        return el;
    }
    
    el.val = function(value){
        if(arguments.length == 0){
            return el[0].value;
        }
        for(var e of el){
            e.value = value;
        }
        return el;
    }
    
    return el;

    
    return el;
};

bit.parseQuery = function (url){
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
};

// [,settings]로 보이면 생략가능하다  settings는 객체다
bit.ajax = function(url, settings){
    
    //settings에 지정하지않았을경우 default값
    if(settings == undefined)
        settings = {};
    if(settings.dataType == undefined)
        settings.dataType = 'text';
    if(settings.method == undefined)
        settings.method = 'GET';

    var xhr = new XMLHttpRequest();
    
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
    //settings에 서버로 보낼 data가 있다면 url에 포함해야한다.
    var qs ='';
    if(settings.data){
        for(var propName in settings.data){
            qs += `&${propName}=${settings.data[propName]}`;
        }
    }
    
    if(settings.method == 'GET'){
        if(url.indexOf('?') == -1)
            url += '?';
        url += qs;
        console.log(url);
        xhr.open(settings.method,url,true);
        xhr.send();
    } else {
        xhr.open(settings.method,url,true);
        xhr.setRequestHeader('Content-Type','application/x-www-form-urlencoded');
        console.log(qs);
        xhr.send(qs);
    }
    
    
    // XMLHttpRequest 객체를 리턴하기전에 함수를 추가한다.
    let done;
    xhr.done = function(func){
       done = func;
    };
    return xhr;
}


bit.getJSON = function(url,p2,p3){
    let data = {};
    let success = null;
    //2번쨰 들어오는 파라미터가 함수가 아니라면
    
    //파라미터가 2개이상인경우에만 여기를 돌게함
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

bit.post = function(url,p2,p3,p4){

    let data = {};
    let success = null;
    let dataType = 'text'; //defalut는 text타입
   
    
    //파라미터가 2개 일 때
    if (arguments.length == 2){
        if(typeof p3 == "function"){
            data = p2;
            success = p3;
        } else if (typeof p2 == "function"){
            success = p2;
            dataType = p3;
        } else {
            data = p2;
            dataType = p3;
        }
    } else if(arguments.length > 2){
        data = p2;
        success = p3;
        dataType = p4;
    }
    
    return bit.ajax(url,{
        method : 'POST',   //post이니까 디폴트가 post
        dataType: dataType, // dataType은 리턴 타입의 객체
        data: data,         // 서버로 넘길 데이터
        success: success    //성공시 실행할 콜백함수.
    });
}


// 달러라는 변수에 bit객체 주소를 넣어준다. 자바스크립트는 변수명을 $도 사용가능
let $ = bit;