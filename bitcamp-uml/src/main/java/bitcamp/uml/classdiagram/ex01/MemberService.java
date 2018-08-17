package bitcamp.uml.classdiagram.ex01;

// 클래스 관계 - Association(연관)
// => 한 클래스가 다른 클래스를 지속적으로 사용하는 관계
// => 사용되는 객체를 "의존 객체(Dependancy)"라 부른다.
//
public class MemberService {
    // MemberService 객체가 사용하는 의존객체를 코드에서 표현하 때
    // 즉 MemberService 객체와 연관된 객체를 자바 코드로 표현 할때
    // 필드로 표현한다.
    MemberDao MemberDao;
    
    public MemberService(MemberDao memberDao) {
        this.MemberDao = memberDao;
    }
}
