package bitcamp.pms.dao;

import bitcamp.pms.domain.Member;

public interface Memberdao {

    public void insert(Member member) throws Exception;
    public int login(Member member) throws Exception;
    public void remove(Member member) throws Exception;
    public Member user(Member member) throws Exception;
}
