"use strict"

var human = 'X',
    computer = 'O',
    count = 0,
    cells = $('.cell');

$('.cell').on("click",(e) => {
    //자바스크립트용 태그 속성값을 알아낸때는 getAttribute()를 쓴다.
    //jqeury는 attr()함수
    console.log($(e.target).attr('data-pos'));
    $(e.target).text(human);
    
    if(++count >= 5) return;
    
    // 타이머 가동하여 1초후에 컴퓨터가 표시하게 한다.
    setTimeout(()=>{
        while(true){
            console.log(111);
            var no = Math.floor(Math.random() * 9);
            if(!isCellChecked(no)){
                checkCell(no, 'computer');
                return;
            }
        }
    },1000);
    
});

$('#new-game').on("click",(e) => {
    histore.go();
});

function isCellChecked(no){
    return cells[no].innerHTML != "" ? true : false;
}
function checkCell(no, gamer){
    cells[no].innerHTML =(gamer == 'computer')? computer : human;
}