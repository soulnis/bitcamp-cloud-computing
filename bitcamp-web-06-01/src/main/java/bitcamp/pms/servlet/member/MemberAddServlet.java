package bitcamp.pms.servlet.member;

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

@SuppressWarnings("serial")
@WebServlet("/member/add")
public class MemberAddServlet extends HttpServlet{
    
    
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
        request.setAttribute("view", "/member/form.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       
    Member member = new Member();
    member.setId(request.getParameter("id"));
    member.setEmail(request.getParameter("email"));
    member.setPassword(request.getParameter("password"));

   
    
    try {
        MemberDAO memberDao = (MemberDAO) this.getServletContext().getAttribute("memberDao");
        memberDao.add(member);
        request.setAttribute("view", "redirect:list");
    } catch (Exception e) {
        request.setAttribute("error", e);
    }
  
}

}

