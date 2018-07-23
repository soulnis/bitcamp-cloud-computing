package bitcamp.pms.servlet.board;

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
        
        try {
           BoardDAO boardDao = (BoardDAO) getServletContext().getAttribute("boardDao");
           boardDao.update(board);
           response.sendRedirect("list");
        
        } catch (Exception e) {
          request.setAttribute("error", e);
          RequestDispatcher rd = request.getRequestDispatcher("/error.jsp");
          rd.forward(request, response);
        }
    }
}
  

