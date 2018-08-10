"use strict"

var human = 'X',
    computer = 'O',
    count = 0;

var cells = $('.cell');
var isCompleted = false;

$('.cell-box').on('complete',(e,result)=>{
    //setTImeout은 비동기 방식 일을시키고 5초후에 콜백함수 실행해줘 란 의미
    setTimeout(()=>{
        if (result == 1) alert("인간 승");
        else if(result == -1) alert("컴퓨터 승");
    }, 500);
});


$('.cell').on("click",(e) => {
    if(isCompleted) return;
    //자바스크립트용 태그 속성값을 알아낸때는 getAttribute()를 쓴다.
    //jqeury는 attr()함수
    console.log($(e.target).attr('data-pos'));
    $(e.target).text(human);
    
    var result = computeGame();
    if(result != 0){
        $('#cell-box').trigger('complete',[result]);
        isComplete = true;
    }
    
    // 마지막 표시를 했으면 더이상 컴퓨터가 작업하지않는다.
    if(++count >= 5) {
        if(result == 0) alert("비겼다");
        return;
    }
    
    // 타이머 가동하여 1초후에 컴퓨터가 표시하게 한다.
    setTimeout(()=>{
        while(true){
            console.log(i);
            var no = Math.floor(Math.random() * 9);
            if(!isCellChecked(no)){
                checkCell(no, 'computer');
                return;
            }
        }
        var result = computeGame();
     
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

function computeGame(){
    for (var row=0; row < 9; row +=3){
        var countHuman = 0;
        var countComputer = 0;
        for(var i= row; i < (row+3); i++){
            if(cells[i].innerHTML == human) countHuman++;
            else if (cells[i].innerHTML == computer) countComputer++;
            
        }
        if(countHuman == 3) 
            return 1;
        else if(countComputer == 3)
            return -1;
    }
    console.log(countHuman,countComputer);
    for (var col=0; col < 3; col++){
        var countHuman = 0;
        var countComputer = 0;
        for(var i= col; i <= (col + 6); i += 3){
            console.log(i);
            if(cells[i].innerHTML == human) countHuman++;
            else if (cells[i].innerHTML == computer) countComputer++;
        }
        if(countHuman == 3) 
            return 1;
        else if(countComputer == 3)
            return -1;
    }
    console.log(countHuman,countComputer);
    
    var countHuman = 0;
    var countComputer = 0;
    
    for (var i=0; i < 8; i +=4){
            if(cells[i].innerHTML == human) countHuman++;
            else if (cells[i].innerHTML == computer) countComputer++;
        }
    if(countHuman == 3) return 1;
    else if(countComputer == 3)
    return -1;
    console.log(countHuman,countComputer);       
            
    var countHuman = 0;
    var countComputer = 0;
    for (var i=2; i <= 6; i += 2){
        console.log(i);
            if(cell[i].innerHTML == human) countHuman++;
            else if (cell[i].innerHTML == computer) countComputer++;
        }
            if(countHuman == 3) 
                return -1;
        else if(countComputer == 3)
                return -1;
     console.log(countHuman,countComputer);     
            return 0;
                            
};