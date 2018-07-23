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
import bitcamp.pms.annotation.RequestMapping;
import bitcamp.pms.domain.Member;


public class MemberUpdateController {

    MemberDAO memberDao;

    public MemberUpdateController(MemberDAO memberDao) {
        super();
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

