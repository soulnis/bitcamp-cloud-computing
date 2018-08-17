'use strict'
$('#addBtn').click(()=>{
    console.log($('#fSaveEmail').prop('checked'));
    console.log($('#fSaveEmail').is(':checked'));
        
    
    var checked = false;
    $.post(`${serverApiAddr}json/auth/singIn`,{
        'email' : $('#fEmail').val(),
        'password' : $('#fPassword').val(),
        'saveEmail' : $('#fSaveEmail').prop('checked')
    },(result)=>{
        console.log(result);
    },'json')
    .fail(()=>{
        alert("회원등록중 오류 발생");
    });
    
});


