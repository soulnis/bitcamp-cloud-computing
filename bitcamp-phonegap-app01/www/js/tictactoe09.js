"use strict"

const human = 1,
      computer = 10,
      cellBox = $('#cell-box'),
      cells = $('.cell'),
      cellData = [0,0,0,0,0,0,0,0,0]; //배열주소는 바뀌지않는거지그안에 값들을 변해도 상관없다. //model
      
var count = 0,
    isCompleted = false,
    isWorking = false;

// 'complete'라는 임의 이벤트를 만들고 트리거를 통해서 
// 이벤트를 발생시킴 콜백함수의 두번쨰 파라미터는 트리거에서 넘어온 배열을 담고있다.
cellBox.on('complete',(e,result)=>{
    
    isWorking =true;
    //setTImeout은 비동기 방식 일을시키고 5초후에 콜백함수 실행해줘 란 의미
    setTimeout(()=>{
        if (result == 3) alert("인간 승2");
        else if(result == 30) alert("컴퓨터 승2");
        else {alert("비겼다2");}
    }, 500);
});

$('.cell').click((e)=>{
    var cell = $(e.target);
    var no = parseInt(cell.attr('data-no'));
    
    if (isCompleted || isWorking) return;
    if (cellData[no] > 0) return;
    if (isCompleted) return;
    
    cell.addClass('cell-x');
    cellData[no] = human;
    count++;
    
    computeGame();
   
    if(isCompleted) return;
    isWorking = true;
    // 타이머를 가동하여 1초 후에 컴퓨터가 표시하게 한다.
    setTimeout(()=>{
        while(true){
            var no = Math.floor(Math.random() * 9);
            //console.log(no+1, $(`.cell:nth-child(${no+1})`).text());
            // 매 이벤트마다 태그를 찾기 때문에 비효율 그래서 글로벌 변수에 지정해놓고 반복문을 돌리는 걸 추천
            if(!isCellChecked(no)){
               checkCell(no, 'computer');
               break;
            }
        }
        computeGame();
        isWorking = false;
    },1000);
});

$('#new-game').click((e)=>{
    cells.removeClass('cell-x').removeClass('cell-o');
    count = 0;
    isCompleted = false;
    isWorking = false;
    for(var i in cellData){
        cellData[i] = 0;
    }
});

function isCellChecked(no){
    return cellData[no]> 0;
}
function checkCell(no, gamer){
    $(cells[no]).addClass((gamer == 'computer') ? 'cell-o':'.cell-x');
    cellData[no] = gamer == 'computer' ? computer : human; //true 면 컴퓨터 false면 휴면값을 담음 
}
//결과 체크함수
function computeGame() {
    
    var sum = 0;
    for (var row = 0; row < 9; row += 3) {
        sum = 0;
        for (var i = row; i < (row + 3); i++) {
            sum += cellData[i];
        }
        if (isGameOver(sum)) return;
    }
    
    for (var col = 0; col < 3; col++) {
        sum = 0;
        for (var i = col; i <= (col + 6); i += 3) {
            sum += cellData[i];
        }
        if (isGameOver(sum)) return;
    }
    
    sum = 0;
    for (var i = 0; i <= 8; i += 4) {
        sum += cellData[i];
    }
    if (isGameOver(sum)) return;
    
    sum = 0;
    for (var i = 2; i <= 6; i += 2) {
        sum += cellData[i];
    }
    isGameOver(sum);
    
    return;
}



function isGameOver(result){
    if(result == 3 || result == 30 || count == 5){
        //cellBox태그에 이벤트를 트리거로 강제로 이벤트발생 두번째 파라미터는 배열로 값 을 넘김
        cellBox.trigger('complete',[result]);
        isCompleted = true;
        return true;
    } else {
        return false;
    }
}
