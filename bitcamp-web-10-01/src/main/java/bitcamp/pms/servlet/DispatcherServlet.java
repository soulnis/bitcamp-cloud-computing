package bitcamp.pms.servlet;

import java.io.IOException;
import java.lang.reflect.Method;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.support.ClassPathXmlApplicationContext;

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
        
        //ServlerContext 보관서에 저장된 IOC컨테이너를 꺼낸다.
        ClassPathXmlApplicationContext iocContainer = 
                    (ClassPathXmlApplicationContext) request.getServletContext().getAttribute("iocContainer");
        
        
        
        try {
            // ServletContext 보관소에 저장된 페이지 컨트롤러를 찾는다.
            Object pageController = iocContainer.getBean(pathInfo);
            //페이지 컨트롤러를 못찾았으면 오류를 내보낸다.
            if(pageController == null) {
                throw new Exception("해당 URL에 대해 서비스를 처리할수없습니다.");
            }
            //페이지 컨트롤러에 있는 매서드중에 @RequestMapping이라는 
            //애노테이션을 붙은 매서드를 찾아서 호출.
            Method requestHandler = getRequestHandler(pageController.getClass());
           
            if(requestHandler == null) {
                throw new Exception("요청핸들러(매서드)가 없습니다.");
            }
            
            //페이지 컨트롤러의 메서드를 호출한다. invoke 해당매서드를 호출 , 첫번째 인수로 들어갈것은 클래스를 담는다. 두번쨰부터는 해당메소드 인수를넣는다.
            //PageController는 해당 객체를 가져옴 (viewController라던가 listController라던가를 넣어줌)
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
        
        //클래스 정보에서 매서드 정보를 추출한다.
        Method[] methods=clazz.getMethods();
        
        //매서드 중에서 @RequestMapping 애노테이션이 붙은 매서드를 찾아낸다.
        for(Method m : methods) {
            RequestMapping anno = m.getAnnotation(RequestMapping.class);
            if(anno != null) {
                return m;
            }
        }
        return null;
    }
}