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

@SuppressWarnings("serial")
@WebServlet("/board/delete")
public class BoardDeleteServlet extends HttpServlet {
@Override
protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
   
    
    response.setContentType("text/html;charset=UTF-8");
    
    
    try {
        int no = Integer.parseInt(request.getParameter("no"));
        BoardDAO boardDao = (BoardDAO) this.getServletContext().getAttribute("boardDao");
            boardDao.delete(no);
            response.sendRedirect("list");
    } catch (Exception e) {
      request.setAttribute("error", e);
      RequestDispatcher rd = request.getRequestDispatcher("/error.jsp");
      rd.forward(request, response);
    }
    }
 }

