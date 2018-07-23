package bitcamp.pms.servlet.team;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bitcamp.pms.Dao.TeamDAO;
import bitcamp.pms.domain.Team;

@SuppressWarnings("serial")
//웹서비스다라고 서블렛에게 알려줌 뒤에("")주소맵핑됨
@WebServlet("/team/list")
public class TeamListServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
       
        try {
         TeamDAO teamDao = (TeamDAO) getServletContext().getAttribute("teamDao");
         List<Team> list  = teamDao.teamList();
         request.setAttribute("list", list);
         RequestDispatcher rd = request.getRequestDispatcher("/team/list.jsp");
         rd.include(request, response);
            
        } catch (Exception e) {
          request.setAttribute("error", e);
          RequestDispatcher rd = request.getRequestDispatcher("/error.jsp");
          rd.forward(request, response);
        }
    
    }
}
  

