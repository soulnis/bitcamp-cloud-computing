package bitcamp.pms.servletContextListner;

import java.io.InputStream;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.annotation.WebListener;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.context.support.ClassPathXmlApplicationContext;



@WebListener
public class ContextLoderListener implements javax.servlet.ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
       System.out.println("서버시작시 실행");
       
        try {
            ClassPathXmlApplicationContext iocContainer =
                    new ClassPathXmlApplicationContext("bitcamp/pms/config/application-context.xml");
            String[] names = iocContainer.getBeanDefinitionNames();
            System.out.println("=====================================================================");
            for(String name : names) {
                System.out.printf("%s ==> %s\n",name,iocContainer.getBean(name).getClass().getName());
            }
            System.out.println("=====================================================================");
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
