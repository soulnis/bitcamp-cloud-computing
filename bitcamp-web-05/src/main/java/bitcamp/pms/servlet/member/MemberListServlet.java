package bitcamp.pms.servlet.member;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.GenericServlet;
import javax.servlet.RequestDispatcher;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bitcamp.pms.Dao.MemberDAO;
import bitcamp.pms.domain.Member;

@SuppressWarnings("serial")
@WebServlet("/member/list")
public class MemberListServlet extends HttpServlet{


    @Override
    protected void doGet(
            HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
               Map<String,Object> param = new HashMap<>();
               if(request.getParameter("page") !=null && request.getParameter("size") != null) {
                  int page = Integer.parseInt(request.getParameter("page"));
                  int size = Integer.parseInt(request.getParameter("size"));
                  param.put("startIndex", (page-1)*size);
                  param.put("pageSize", size);
                  System.out.println(param.toString());
               }
        
        try {
                MemberDAO memberDao = (MemberDAO) this.getServletContext().getAttribute("memberDao");
                
                List<Member> list =memberDao.memberList(param);
                request.setAttribute("list", list);
                RequestDispatcher rd = request.getRequestDispatcher("/member/list.jsp");
                rd.include(request, response);
            
        } catch (Exception e) {
            request.setAttribute("error", e);
            RequestDispatcher rd = request.getRequestDispatcher("/error.jsp");
            rd.include(request, response);
        }
    }
}
    
