'use strict'
var serverApiAddr = "http://localhost:8080/bitcamp-assignment-04";


$(()=>{
    $('header').load(`${serverApiAddr}/html/header.html`); 
    $('footer').load(`${serverApiAddr}/html/footer2.html`); 
});

