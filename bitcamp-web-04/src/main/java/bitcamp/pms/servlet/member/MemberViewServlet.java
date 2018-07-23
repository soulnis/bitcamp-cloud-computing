package bitcamp.pms.servlet.member;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bitcamp.pms.Dao.MemberDAO;
import bitcamp.pms.domain.Member;

@SuppressWarnings("serial")
//웹서비스다라고 서블렛에게 알려줌 뒤에("")주소맵핑됨
@WebServlet("/member/view")
public class MemberViewServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String id = request.getParameter("id");
        
        response.setContentType("text/html;charset=UTF-8");
        //PrintWriter객체는 출력 
     
        
        try {
            MemberDAO memberDao = (MemberDAO) this.getServletContext().getAttribute("memberDao");
            Member member = memberDao.view(id);
            request.setAttribute("member", member);
            RequestDispatcher rd = request.getRequestDispatcher("/member/view.jsp");
            rd.include(request, response);
               
        } catch (Exception e) {
         request.setAttribute("error", e);
         RequestDispatcher rd = request.getRequestDispatcher("/error.jsp");
         rd.forward(request, response);
        
        }
    }
   
  }

