// 주제: 템플릿 엔진 사용
// 템플릿 엔진 모듈을 로딩한다
const handlebars = require('handlebars')

// 템플릿를 정의한다
var templateSrc = '{{name}}님안녕하세요!';
// 템플릿 소스에 데이터를 삽입하여 최종 결과를 만들어 낼 함수를 준비한다.
var templateFn= handlebars.compile(templateSrc);

var resultStr = templateFn({name:'홍길동'})
console.log(resultStr);

