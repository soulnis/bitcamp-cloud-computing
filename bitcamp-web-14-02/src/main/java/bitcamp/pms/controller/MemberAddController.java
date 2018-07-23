package bitcamp.pms.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import bitcamp.pms.Dao.MemberDAO;
import bitcamp.pms.domain.Member;

@Controller
public class MemberAddController {
   
    MemberDAO memberDao;
    
    public MemberAddController(MemberDAO memberDao) {
        super();
        this.memberDao = memberDao;
    }
    
    
    public MemberAddController() {}
    @Autowired
    public void setMemberDao(MemberDAO memberDao) {
        this.memberDao = memberDao;
    }


    @RequestMapping("/member/add")
    public String add(HttpServletRequest request, HttpServletResponse response) throws Exception {
       //들어온 게 GET인지 POST인지 체크하는것
       if(request.getMethod().equals("GET")) {
          return "member/form"; 
       }
     
        Member member = new Member();
        member.setId(request.getParameter("id"));
        member.setEmail(request.getParameter("email"));
        member.setPassword(request.getParameter("password"));
        
    
        memberDao.insert(member);
        return "redirect:list";
    
    }
}

