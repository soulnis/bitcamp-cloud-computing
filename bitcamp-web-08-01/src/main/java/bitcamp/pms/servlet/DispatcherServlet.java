package bitcamp.pms.servlet;

import java.io.IOException;
import java.lang.reflect.Method;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bitcamp.pms.annotation.RequestMapping;




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
        Object pageController = getServletContext().getAttribute(pathInfo);
        try {
            if(pageController == null) {
                throw new RuntimeException("해당 URL에 대해 서비스를 처리할수없습니다.");
            }
            
            Method requestHandler = getRequestHandler(pageController.getClass());
            System.out.println(requestHandler.getName());
            String view = (String) requestHandler.invoke(pageController, request,response);
            
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

    private Method getRequestHandler(Class<? extends Object> clazz) {
        
        Method[] methods = clazz.getMethods();
            for(Method m: methods) {
                System.out.println(m.getName());
                RequestMapping anno = m.getAnnotation(RequestMapping.class);
                 //리퀘스트맵핑이있는 매서드이면
                 System.out.println(anno != null);
                 if(anno != null)
                     //해당 매소드를 리턴
                    return m;
            }
        //리퀘스트 맵핑이 없으면 널은 리턴
        return null;
    }
}