package bitcamp.mvc.web;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
//톰캣서버의 시작종료의 알림을 받고싶으면 ServletContextListenr를 인터페이스상속
@WebListener
public class MyServletContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
      System.out.println("톰캣 시작");
    
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
      System.out.println("톰캣 종료");
    }

    
}
