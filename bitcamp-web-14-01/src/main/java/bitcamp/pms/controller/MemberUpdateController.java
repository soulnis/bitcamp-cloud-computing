package bitcamp.pms.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import bitcamp.pms.Dao.MemberDAO;
import bitcamp.pms.domain.Member;

@Controller
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
    @RequestMapping("/member/update")
    public String update(HttpServletRequest request, HttpServletResponse response) throws Exception {
     
         Member member = new Member();
         member.setId(request.getParameter("id"));
         member.setEmail(request.getParameter("email"));
        
           if(memberDao.update(member)==0) {
               return "member/updatefail";
           }else {
               return "redirect:list";
           }
    }
}

