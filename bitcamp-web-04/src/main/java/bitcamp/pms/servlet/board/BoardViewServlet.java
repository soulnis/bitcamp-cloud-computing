package bitcamp.pms.servlet.board;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bitcamp.pms.Dao.BoardDAO;
import bitcamp.pms.domain.Board;
@SuppressWarnings("serial")
@WebServlet("/board/view")
public class BoardViewServlet extends HttpServlet {
 @Override
protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
     //request.setCharacterEncoding("UTF-8");
     
     response.setContentType("text/html;charset=UTF-8");
     try {
         int no = Integer.parseInt(request.getParameter("no"));
         BoardDAO boardDao = (BoardDAO) getServletContext().getAttribute("boardDao");
         Board board=boardDao.boardView(no);
         request.setAttribute("board", board);
         RequestDispatcher rd = request.getRequestDispatcher("/board/view.jsp");
         rd.include(request, response);
     
     } catch (Exception e) {
        request.setAttribute("error", e);
        RequestDispatcher rd = request.getRequestDispatcher("/error.jsp");
        rd.forward(request, response);
     }
  
 }
 
 
}

