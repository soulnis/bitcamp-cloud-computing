package bitcamp.pms.servlet.member;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

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
        PrintWriter out = response.getWriter();
        
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<meta charset='UTF-8'>");
        out.println("<title>멤버 보기</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h1>멤버 보기</h1>");
        out.println("<form action='update' method='post'>");
        
        try {
            MemberDAO memberDao = (MemberDAO) this.getServletContext().getAttribute("memberDao");
          Member member = memberDao.view(id);
                    if (member == null) {
                        out.println("<p>유효하지 않은 멤버 아이디입니다.</p>");
                    } else { 
                        out.println("<table border='1'>");
                        out.println("<tr><th>아이디</th><td>");
                        out.printf("    <input type='text' name='id' value='%s' readonly></td></tr>\n", 
                                member.getId());
                        out.println("<tr><th>이메일</th>");
                        out.printf("    <td><input type='email' name='email' value='%s'></td></tr>\n",
                                member.getEmail());
                        out.println("<tr><th>암호</th>");
                        out.println("    <td><input type='password' name='password'></td></tr>\n");
                        out.println("</table>");
                        out.println("<p>");
                        out.println("<a href='list'>목록</a>");
                        out.println("<button>변경</button>");
                        out.printf("<a href='delete?id=%s'>삭제</a>\n", id);
                        out.println("</p>");
                        out.println("</form>");
                    }
               
               
        } catch (Exception e) {
            out.printf("<p>%s</p>\n", e.getMessage());
            e.printStackTrace(out);
        }
        out.println("</body>");
        out.println("</html>");
    }
   
  }

