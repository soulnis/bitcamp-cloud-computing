"use strict"

var gamer = 'X';
var computer = 'O';

$('.cell').on("click",(e) => {
    //자바스크립트용 태그 속성값을 알아낸때는 getAttribute()를 쓴다.
    //jqeury는 attr()함수
    console.log($(e.target).attr('data-pos'));
    $(e.target).text(gamer);
    
});

$('#new-game').on("click",(e) => {
    histore.go();
});