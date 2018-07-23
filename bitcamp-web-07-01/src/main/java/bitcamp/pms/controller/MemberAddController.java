package bitcamp.pms.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bitcamp.pms.Dao.MemberDAO;
import bitcamp.pms.domain.Member;

public class MemberAddController implements PageController{
    
    
    MemberDAO memberDao;
    
    
    public MemberAddController(MemberDAO memberDao) {
        super();
        this.memberDao = memberDao;
    }

    
    @Override
    public String service(HttpServletRequest request, HttpServletResponse response) throws Exception {
       if(request.getMethod().equals("GET"))
        return "/member/form.jsp";
       
       Member member = new Member();
       member.setId(request.getParameter("id"));
       member.setEmail(request.getParameter("email"));
       member.setPassword(request.getParameter("password"));
       
       memberDao.add(member);
       return "redirect:list";
    }
}


