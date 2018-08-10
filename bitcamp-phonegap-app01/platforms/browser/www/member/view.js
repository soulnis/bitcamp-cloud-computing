"use strict"

var data = null;
//객체에서 필요한것만 뽑아내서 변수에 담는방법 분해하는법 계체는 var {}, 배열은 var [];
var {id,page,size} = $.parseQuery(location.href);


if(id == undefined){// 입력 폼
    $('.viewform').css('display','none');
    
    //오리지날함수에 없는 것을 제이쿼리를 통과시켜서 함수를 쓸수있게한다.
    $(eId).removeAttr("readonly");
} else { // 상세보기 폼
    $('.newform').css('display','none');
    
    $.getJSON(serverApiAddr+`/json/member/view/${id}`
            ,function(result){ // 성공시 실행되는 콜백함수
                data = result; // json데이터로 받아옴
                if(data.member == null){
                    alert('아이다 유효하지 않습니다.');
                    location.href = 'list.html'
                    return;
                }
                $(eId).val(data.member.id);
                $(eEmail).val(data.member.email);
        
            });
};

$(eListBtn).click(function(){
    if(page){
        console.log(1);
        location.href = `list.html?page=${page}&size=${size}`;
    }else{
        console.log(2);
        location.href = 'list.html';
    }
});
//post ajax통신
$(eUpdateBtn).click(function(){
    $.post(serverApiAddr+'/json/member/update' // 1번째 파라미터 url
            ,{ id: $(eId).val(), //  2번째 파라미터 데이터
               email: $(eEmail).val() ,
               passwonrd: $(ePassword).val(),
             },
             function(data){ //세번째 성공시 콜백함수
                 console.log(data);
                 if(data.status == 'success'){
                 location.href = `list.html?page=${page}&size=${size}`;
                  } else {
                      alert('변경 오류입니다.');
                      console.log(data.error);
                  }
            },
            'json' // 4번째 파라미터 보낸는 데이터타입
    ); 
});

$(eDeleteBtn).click(function(){
    $.getJSON(serverApiAddr+`/json/member/delete?id=${eId.value}`
            ,function(data){
                        if(data.status == 'success'){
                            location.href = `list.html?page=${page}&size=${size}`;
                        } else {
                            alert('삭제 오류입니다.');
                            console.log(data.error);
                        }
    });
});

$(eAddBtn).click(function(){
    $.post(serverApiAddr+'/json/member/add',
            { id: $(eId).val(), //  2번째 파라미터 데이터
        email: $(eEmail).val() ,
        passwonrd: $(ePassword).val()},
        function(data){location.href = 'list.html';},
        'json');

});
