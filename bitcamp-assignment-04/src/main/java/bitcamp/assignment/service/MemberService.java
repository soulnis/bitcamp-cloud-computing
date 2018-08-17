package bitcamp.assignment.service;

import bitcamp.assignment.domain.Member;

public interface MemberService {

    public int add(Member member);

    public Member getMember(String email,String password);

}
