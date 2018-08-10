package bitcamp.pms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bitcamp.pms.dao.Memberdao;
import bitcamp.pms.domain.Member;

@Service
public class MemberService {

   @Autowired Memberdao memberdao;
   
   public void insert(Member member) throws Exception{
       memberdao.insert(member);
   }
   public int login(Member member) throws Exception{
       
       return memberdao.login(member);
   }
   
   public void remove(Member member) throws Exception{
       memberdao.remove(member);
   }
   
   public Member user(Member member) throws Exception{
       
       return memberdao.user(member);
   }
}
