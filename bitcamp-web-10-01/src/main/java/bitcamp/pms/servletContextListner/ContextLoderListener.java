package bitcamp.pms.servletContextListner;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.annotation.WebListener;

import org.springframework.context.support.ClassPathXmlApplicationContext;


@WebListener
public class ContextLoderListener implements javax.servlet.ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
       System.out.println("서버시작시 실행");
       
        try {
            ClassPathXmlApplicationContext iocContainer 
                = new ClassPathXmlApplicationContext("bitcamp/pms/config/application-context.xml");
       
            //서블릿 컨테이터 가져오기
            ServletContext sc =sce.getServletContext();
            //프론트컨트롤러가사용할수있도록 
            //가져온서블릿컨테이너에 iocContainer저장!
            sc.setAttribute("iocContainer", iocContainer);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
