package bitcamp.pms.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
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
       //들어온 게 GET인지 POST인지 체크하는것
       if(request.getMethod().equals("GET")) {
          return "/member/form.jsp"; 
       }
     
        Member member = new Member();
        member.setId(request.getParameter("id"));
        member.setEmail(request.getParameter("email"));
        member.setPassword(request.getParameter("password"));
        
    
        memberDao.add(member);
        return "redirect:list";
    
    }
}

