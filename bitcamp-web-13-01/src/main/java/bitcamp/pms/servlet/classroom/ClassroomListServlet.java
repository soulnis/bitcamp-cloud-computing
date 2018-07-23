package bitcamp.pms.servlet.classroom;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bitcamp.pms.Dao.ClassroomDAO;
import bitcamp.pms.domain.Classroom;
@SuppressWarnings("serial")
@WebServlet("/classroom/list")
public class ClassroomListServlet extends HttpServlet {
 @Override
protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
     //요청에 응답할 텍스트 타입과 문자셋을 정해줌.
     response.setContentType("text/html;charset=UTF-8");
         try {
                 ClassroomDAO classroomDao =  (ClassroomDAO) getServletContext().getAttribute("classroomDao");             
                 List<Classroom> list = classroomDao.classroomList();
                 request.setAttribute("list", list);
                 RequestDispatcher rd = request.getRequestDispatcher("/classroom/list.jsp");
                 rd.include(request, response);
         
     } catch (Exception e) {
         request.setAttribute("error", e);
         RequestDispatcher rd = request.getRequestDispatcher("/error.jsp");
         rd.forward(request, response);
      
     }
 }
     
     
 }

