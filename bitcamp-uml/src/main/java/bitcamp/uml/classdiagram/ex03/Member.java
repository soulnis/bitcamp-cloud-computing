package bitcamp.uml.classdiagram.ex03;
//Composition(합성)
//=> 한 객체가 다른 객체를 포함하는 관계
//=> Container와 Containee의  라이플사이클을 함꼐한다.
// => 즉 Container가 소멸될 때 containee도 함계  소멸된다.
public class Member {

    // 합성관계를
    Address juso;
    
    
    public Member(){
        juso = new Address("111222", "기본주소", "상세주소");
    }
}
