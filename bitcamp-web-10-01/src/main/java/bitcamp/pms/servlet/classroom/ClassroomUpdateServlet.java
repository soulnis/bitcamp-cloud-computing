package bitcamp.pms.servlet.classroom;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Calendar;
import java.util.Locale;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bitcamp.pms.Dao.ClassroomDAO;
import bitcamp.pms.domain.Classroom;
@SuppressWarnings("serial")
@WebServlet("/classroom/update")
public class ClassroomUpdateServlet extends HttpServlet {
 @Override
protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
     
     request.setCharacterEncoding("UTF-8");
     response.setContentType("text/html;charset=UTF-8");
    
     Classroom classroom = new Classroom();
     classroom.setTitle(request.getParameter("title"));
     classroom.setStartTime(Date.valueOf(request.getParameter("startDate")));
     classroom.setEndTime(Date.valueOf(request.getParameter("endDate")));
     classroom.setRoom(request.getParameter("room"));
     classroom.setBno(Integer.parseInt(request.getParameter("no")));
     try {
        ClassroomDAO classroomDao = (ClassroomDAO) getServletContext().getAttribute("classroomDao");
        classroomDao.update(classroom);
        response.sendRedirect("list");
     } catch (Exception e) {
         request.setAttribute("error", e);
         RequestDispatcher rd = request.getRequestDispatcher("/error.jsp");
         rd.forward(request, response);
     }
   
 }
}

