## web-15 요청 핸들러의 파라미터와 리턴 값 다루기
- 요청 핸들러의 파라미터 다루기
- 요청 핸들러의 리턴 다루기

## 요청 핸들러의 매트릭스 변수 활성화 시키기
- application-context.xml에서 다음과같이 설정 변경한다.
'''
 <mvc:annotation-driven enable-matrix-variables="true"/>
'''

##  각 페이지 컨트롤러에 대해 요청핸들러의 파라미터를 정리한다. ex)서블릿리퀘스트에서 일반 객체 나 기본타입으로 
##  각 페이지 컨트롤러에 대해 요청핸들러의 리턴값을 정리한다. ex) String에서 void로 
##  각 페이지 컨트롤러에 대해 요청핸들러의 애노테이션을  정리한다. ex)@Getmapping
##  CRUD 관련 메서드는 한 개의 컨트롤러에 묶어 관리한다.
MemberXxxxController 를 MemberController에 모두 합친다.

