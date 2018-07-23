package bitcamp.pms.servlet.board;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bitcamp.pms.Dao.BoardDAO;

@SuppressWarnings("serial")
@WebServlet("/board/delete")
public class BoardDeleteServlet extends HttpServlet {
@Override
protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
   
    
    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();
    
    out.println("<!DOCTYPE html>");
    out.println("<html>");
    out.println("<head>");
    out.println("<meta charset='UTF-8'>");
    out.println("<meta http-equiv='Refresh' content='1;url=list'>");
    out.println("<title>게시물 삭제</title>");
    out.println("</head>");
    out.println("<body>");
    out.println("<h1>게시물 삭제 결과</h1>");
    
    try {
        int no = Integer.parseInt(request.getParameter("no"));
        BoardDAO boardDao = (BoardDAO) this.getServletContext().getAttribute("boardDao");
            if (boardDao.delete(no) == 0) {
                out.println("<p>해당 게시물이 없습니다.</p>");
            } else {
                out.println("<p>삭제하였습니다.</p>");
            }
    } catch (Exception e) {
        out.println("<p>삭제 실패!</p>");
        e.printStackTrace(out);
    }
    out.println("</body>");
    out.println("</html>");
    }

 
 }

