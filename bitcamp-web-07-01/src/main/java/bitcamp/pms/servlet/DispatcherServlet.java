package bitcamp.pms.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bitcamp.pms.controller.PageController;

@SuppressWarnings("serial")
@WebServlet("/app/*")
public class DispatcherServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String pathInfo= request.getPathInfo();
        
        PageController pageController = (PageController) getServletContext().getAttribute(pathInfo);
        
        try {
            if(pageController ==null) {
                    throw new Exception("URL 오류입니다.");
            }    
        
        String view = pageController.service(request, response);
        
            if(view.startsWith("redirect:") ) {
                response.sendRedirect(view.substring(9));
            } else  {
               RequestDispatcher rd = request.getRequestDispatcher(view);
               rd.include(request, response);
            }  
        } catch (Exception e) {
            e.printStackTrace();
            RequestDispatcher rd= request.getRequestDispatcher("/error.jsp");
            rd.forward(request, response);
        }
         
    }
}