"use strict"
//$(ePrevBtn).draggable();
let tbody = $('#eListTable > tbody');
let data = null;

var {page,size} = $.parseQuery(location.href);

if(page !=undefined && size != undefined){
    loadList(page,size);
} else {
    loadList(1,3);
}
//오리지날객체에는 on이없기떄문에 $(여기)에넣어준다.
$(ePrevBtn).click(function(){
    loadList(data.page - 1,data.size);
});
$(eNextBtn).click(function(){
   loadList(data.page + 1,data.size);
});
function loadList(page,size){
    $.getJSON(`../../json/member/list`, {
      page: page,
      size: size
      },function(){console.log("로딩 성공!")}).done(function(result){
            data = result;
            tbody.html('');
            for(var item of data.list){
               $('<tr>').html(`<td><a href='#' data-id='${item.id}' class='viewLink'>${item.id}</a></td><td>${item.email}</td>`)
               .appendTo(tbody); 
            }
            $(ePageNo).html(data.page); 
            
            if(data.page <= 1)
                $(ePrevBtn).attr('disabled','');
            else
                $(ePrevBtn).removeAttr('disabled');
            
            if(data.page >= data.totalPage)
                $(eNextBtn).attr('disabled','');
            else
                $(eNextBtn).removeAttr('disabled');
      
      });
};

//비동기방식이라 서버에서 값이 오기전에 먼저 실행 되기 떄문에 .viewLink를 찾을 수 없다.
//이 방식은 실행 시점에 존재하는 태그에 대해서만 이벤트 핸들러를 등록 할 수 있다. 
/* $(".viewLink").click(function(event){
    alert("ok");
    event.preventDefault();
    //이벤트가 발생된 태그를 가르킴   event.currentTarget오리지날 태그객체
    var id = $(event.currentTarget).attr('data-id');
    location.href = `view.html?id=${id}&page=${data.page}&size=${data.size}`;
}); */


// 실행 시점에 존재하지 않더라도 이벤트 핸들러를 등록하는 방법은?
// => 앞으로 생성될 부모에 리스너를 등록하는 것.
tbody.on("click",'a.viewLink',function(event){
   
    event.preventDefault();
    // 이벤트가 발생된 태그를 가르킴   event.currentTarget오리지날 태그객체
    // 이경우는 currentTarget이 안되고 target을 해야한다.
    var id = $(event.target).attr('data-id');
    location.href = `view.html?id=${id}&page=${data.page}&size=${data.size}`;
});
