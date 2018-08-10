"use strict"

var human = 'X',
    computer = 'O',
    count = 0;

var cells = $('.cell');
var isCompleted = false,
    isWorking = false;

$('#cell-box').on('complete',(e,result)=>{
    
    isWorking =true;
    //setTImeout은 비동기 방식 일을시키고 5초후에 콜백함수 실행해줘 란 의미
    setTimeout(()=>{
        if (result == 1) alert("인간 승");
        else if(result == -1) alert("컴퓨터 승");
    }, 500);
});

$('.cell').click((e)=>{
    if (isCompleted || isWorking) return;
    if(e.target.innerHTML != '') return;
    if(isCompleted) return;
    
    console.log($(e.target).attr('data-pos'));
    $(e.target).text(human).addClass('cell-x');
    
    count ++;
    
    var result = computeGame();
    if(result != 0){
       $('#cell-box').trigger('complete',[result]);
       isCompleted = true;
    }
   
    if(isCompleted) return;
    isWorking = true;
    // 타이머를 가동하여 1초 후에 컴퓨터가 표시하게 한다.
    setTimeout(()=>{
        while(true){
            var no = Math.floor(Math.random() * 9);
            //console.log(no+1, $(`.cell:nth-child(${no+1})`).text());
            // 매 이벤트마다 태그를 찾기 때문에 비효율 그래서 글로벌 변수에 지정해놓고 반복문을 돌리는 걸 추천
            if(!isCellChecked(no)){
               checkCell(no, 'computer')
               break;
            }
        }
        
        var result = computeGame();
        if(result != 0){
           $('#cell-box').trigger('complete',[result]);
           isCompleted = true;
        }else if(count == 5){
            alert("비겼다!")
        }
        isWorking = false;
    },1000);
});

$('#new-game').click((e)=>{
    cells.text('').removeClass('cell-x').removeClass('cell-o');
    count = 0;
    isCompleted = false;
    isWorking = false;
});

function isCellChecked(no){
    return cells[no].innerHTML != "" ? true : false;
}
function checkCell(no, gamer){
    $(cells[no]).text((gamer == 'computer') ? computer : human)
                .addClass((gamer == 'computer') ? 'cell-o':'.cell-x'); 
}
//결과 체크함수
function computeGame() {
    console.log("-----------------");
    for (var row = 0; row < 9; row += 3) {
        var countHuman = 0;
        var countComputer = 0;
        for (var i = row; i < (row + 3); i++) {
            if (cells[i].innerHTML == human) countHuman++;
            else if (cells[i].innerHTML == computer) countComputer++;
        }
        if (countHuman == 3) return 1;
        else if (countComputer == 3) return -1;
        console.log(countHuman, countComputer);
    }
    
    for (var col = 0; col < 3; col++) {
        var countHuman = 0;
        var countComputer = 0;
        for (var i = col; i <= (col + 6); i += 3) {
            if (cells[i].innerHTML == human) countHuman++;
            else if (cells[i].innerHTML == computer) countComputer++;
        }
        if (countHuman == 3) return 1;
        else if (countComputer == 3) return -1;
        console.log(countHuman, countComputer);
    }
    
    var countHuman = 0;
    var countComputer = 0;
    for (var i = 0; i <= 8; i += 4) {
        if (cells[i].innerHTML == human) countHuman++;
        else if (cells[i].innerHTML == computer) countComputer++;
    }
    if (countHuman == 3) return 1;
    else if (countComputer == 3) return -1;
    console.log(countHuman, countComputer);
    
    var countHuman = 0;
    var countComputer = 0;
    for (var i = 2; i <= 6; i += 2) {
        if (cells[i].innerHTML == human) countHuman++;
        else if (cells[i].innerHTML == computer) countComputer++;
    }
    if (countHuman == 3) return 1;
    else if (countComputer == 3) return -1;
    console.log(countHuman, countComputer);
    
    return 0;
}
