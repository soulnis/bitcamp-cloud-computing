# bitcamp-cloud-computing
비트캠프 클라우드 컴퓨팅 과정

## web-01 : 웹 프로젝트 폴더 준비 
-gradle을 이용하여 프로젝트 폴더를 만드는방법

## web-02 : 서블릿
-서블릿으로 웹 애플리케이션을 만들고 배포하는 방법

##bitcamp 밑에

##패키지 생성
bitcamp.pms.servlet 패키지생성


##회원관리 서블릿 만들기
- servlet-api 의존 라이브러리 추가
    - mvnrepository.com에서 sevlet -api 라이브러리 검색
    - bulid.gradle에 라이브러리 등록
    - 'gradle cleaneclipse 후에 gradle eclipse 실행하여 .classpath 갱신
    - 이클립스 프로젝트 refresh
-mysql.jdbc.

-bitcamp.pms.servlet.member 패키지 생성
-MemberListServlet, MemberViewServlet





## web-03 : JDBC 
- JDBC를 이용하여 데이터를 다루는 방법

## web-04 : DAO
- 데이터처리를 전문으로하는 DAO 객체를 도입

## web-05 : 서블릿 + DAO + JSP = MVC 아키텍쳐 
- JSP 기술을 사용하여 출력을 단순화하는 방법
- MVC 아키텍쳐의 개념

## web-06 : Front Controller 도입
- 서블릿들이 공통으로 수행하는 작업을 프론트 컨트롤러에게 맡긴다(위임)
- 나머지 서블릿들은 "Page Controller"로 부른다.

## web-07 : Page Controller를 POJO로 변환
-프론트 컨트롤러가 도입되면 페이지 컨트롤러는 일반 자바 객체로 만들어도 된다.
- 페이지 컨트롤러는 POJO로 만들어도 된다.

