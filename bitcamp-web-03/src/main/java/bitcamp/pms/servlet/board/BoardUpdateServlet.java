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
import bitcamp.pms.domain.Board;

@SuppressWarnings("serial")
@WebServlet("/board/update")
public class BoardUpdateServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse  response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        
        Board board = new Board();
        board.setBno(Integer.parseInt(request.getParameter("no")));
        board.setContent(request.getParameter("content"));
        board.setTitle(request.getParameter("title"));
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<meta charset='UTF-8'>");
        out.println("<meta http-equiv='Refresh' content='1;url=list'>");
        out.println("<title>게시물 변경</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h1>게시물 변경 결과</h1>");
        
        try {
            BoardDAO boardDao = (BoardDAO) getServletContext().getAttribute("boardDao");
                if (boardDao.update(board) == 0) {
                    out.println("<p>해당 게시물이 존재하지 않습니다.</p>");
                } else {
                    out.println("<p>변경하였습니다.</p>");
                }
          
        } catch (Exception e) {
            out.println("<p>변경 실패!</p>");
            e.printStackTrace(out);
        }
        out.println("</body>");
        out.println("</html>");
    }
    
   
}
  

