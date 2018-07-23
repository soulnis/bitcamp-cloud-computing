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
@WebServlet("/member/update")
public class MemberUpdateServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
         Member member = new Member();
         member.setId(request.getParameter("id"));
         member.setEmail(request.getParameter("email"));
         response.setContentType("text/html;charset=UTF-8");
        
        try {
            MemberDAO memberDao = (MemberDAO) this.getServletContext().getAttribute("memberDao");
            memberDao.update(member);
            request.setAttribute("view", "redirect:list");
        } catch (Exception e) {
            request.setAttribute("error", e);
        }
      
    }
   
}

