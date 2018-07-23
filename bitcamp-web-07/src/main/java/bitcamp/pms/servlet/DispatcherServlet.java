package bitcamp.pms.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
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
      
        //클라이언트가 요청한 서비스 URL을 알아낸다.
        //즉 /app/* 에서 *에 해당하는 값을 추출
        // 예)/app/member/list => /member/list를 추출한다.
        String pathInfo = request.getPathInfo();
        System.out.println(pathInfo);
      
        //인클루드하는쪽에서 컨텐트 타입을 설정해줘야함
        response.setContentType("text/html;charset=UTF-8");
        
        // ServletContext 보관소에 저장된 페이지 컨트롤러를 찾는다.
        PageController pageController = (PageController)getServletContext().getAttribute(pathInfo);
        
        try {
            if(pageController == null) {
                throw new Exception("해당 URL에 대해 서비스를 처리할수없습니다.");
            }
            //Page 컨트롤러를 실행.
            String view = pageController.service(request, response);
            
            if(view.startsWith("redirect:")) {
               response.sendRedirect(view.substring(9));
            } else {
               RequestDispatcher rd = request.getRequestDispatcher(view);
               rd.include(request, response);
            }

        }catch (Exception e) {
         request.setAttribute("error", e);
         RequestDispatcher rd = request.getRequestDispatcher("/error.jsp");
         rd.forward(request, response);
        }
    }
}