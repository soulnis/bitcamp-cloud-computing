package bitcamp.pms.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import bitcamp.pms.Dao.MemberDAO;
import bitcamp.pms.annotation.RequestMapping;
import bitcamp.pms.domain.Member;

@Controller("/member/update")
public class MemberUpdateController {

    MemberDAO memberDao;

    public MemberUpdateController(MemberDAO memberDao) {
        super();
        this.memberDao = memberDao;
    }
    public MemberUpdateController() {}
    @Autowired
    public void setMemberDao(MemberDAO memberDao) {
        this.memberDao = memberDao;
    }
    @RequestMapping
    public String update(HttpServletRequest request, HttpServletResponse response) throws Exception {
     
         Member member = new Member();
         member.setId(request.getParameter("id"));
         member.setEmail(request.getParameter("email"));
        
           if(memberDao.update(member)==0) {
               return "member/updatefail.jsp";
           }else {
               return "redirect:list";
           }
    }
}

