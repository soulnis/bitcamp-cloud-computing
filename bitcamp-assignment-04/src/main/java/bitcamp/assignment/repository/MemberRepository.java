package bitcamp.assignment.repository;

import java.util.Map;

import bitcamp.assignment.domain.Member;

public interface MemberRepository {

    public int insert(Member member);

    public Member findByEmailAnsPassword(Map params);

}
