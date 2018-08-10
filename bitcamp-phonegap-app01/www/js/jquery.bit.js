$.parseQuery = function(url){
    var paramMap ={};
   
    var qs = url.split('?'); // [0]은 url 부분 [1]은 qeury string부분이 들어감
    if(qs.length > 1){ // qs.length가 1 이하이면 ?문이후 문이 없다는것 
        var params = qs[1].split('&'); // &으로 자르기
        for(var param of params){
            var kv = param.split('='); // =으로 자르면 앞은 [0]은 키값 [1]은 벨류값이 됨
            paramMap[kv[0]] =kv[1];
        }
    }
    return paramMap;

}