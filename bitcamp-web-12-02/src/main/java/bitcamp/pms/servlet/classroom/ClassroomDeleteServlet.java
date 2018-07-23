package bitcamp.pms.servlet.classroom;

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

import bitcamp.pms.Dao.ClassroomDAO;
@SuppressWarnings("serial")
@WebServlet("/classroom/delete")
public class ClassroomDeleteServlet extends HttpServlet {
 @Override
protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
     int no = Integer.parseInt(request.getParameter("no"));
     response.setContentType("text/html;charset=UTF-8");
     try {
       ClassroomDAO classroomDao = (ClassroomDAO) getServletContext().getAttribute("classroomDao");
         classroomDao.delete(no);
         response.sendRedirect("list");
     } catch (Exception e) {
         request.setAttribute("error", e);
         RequestDispatcher rd = request.getRequestDispatcher("/error.jsp");
         rd.forward(request, response);
     }
 }
}
