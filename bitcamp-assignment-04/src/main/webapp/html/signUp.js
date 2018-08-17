'use strict'
$('#addBtn').click(()=>{
    $.post(`${serverApiAddr}json/member/singUp`,{
        'email' : $('#fEmail').val(),
        'name' : $('#fName').val(),
        'password' : $('#fPassword').val()
    },(result)=>{
        if(result.status === 'success'){
            location.href = 'signIn.html'
        } else {
            alert('회원가입을 실패 했습니다 다시 확인 해주세요.');
        }
    },'json')
    .fail(()=>{
        alert("회원등록중 오류 발생");
    });
    
    //$.post(주소,넘길데이터,콜백함수,받아온데이터타입).fail(콜백함수); 실패시 
});


console.log('===================>');