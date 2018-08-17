'use strict'
var serverApiAddr = "http://localhost:8080/bitcamp-assignment-04/";


$(()=>{
   $('footer').load(`${serverApiAddr}/footer.html`); //절대경로를 함으로써 깨지지 않기 위해서 제일중효하다.
});

