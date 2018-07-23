package bitcamp.pms.servlet.team;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
//웹서비스다라고 서블렛에게 알려줌 뒤에("")주소맵핑됨
@WebServlet("/team/add")
public class TeamAddServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<meta charset='UTF-8'>");
        out.println("<meta http-equiv='Refresh' content='1;url=list'>");
        
        out.println("<title>팀 등록</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h1>팀 등록 결과</h1>");
        
        try {
            Class.forName("com.mysql.jdbc.Driver");
            try (
                Connection con = DriverManager.getConnection(
                        "jdbc:mysql://52.79.239.97:3306/studydb",
                        "study", "1111");
                PreparedStatement stmt = con.prepareStatement(
                    "insert into pms2_team(name,dscrt,max_qty,sdt,edt) values(?,?,?,?,?)");) {
                
                stmt.setString(1, request.getParameter("name"));
                stmt.setString(2, request.getParameter("description"));
                stmt.setInt(3, Integer.parseInt(request.getParameter("maxQty")));
                stmt.setDate(4, Date.valueOf(request.getParameter("startDate")), Calendar.getInstance(Locale.KOREAN));
                stmt.setDate(5, Date.valueOf(request.getParameter("endDate")), Calendar.getInstance(Locale.KOREAN));
                stmt.executeUpdate();
            }
        
            out.println("<p>등록 성공!</p>");
        } catch (Exception e) {
            out.println("<p>등록 실패!</p>");
            e.printStackTrace(out);
        }
        out.println("</body>");
        out.println("</html>");
    }
    
  }

