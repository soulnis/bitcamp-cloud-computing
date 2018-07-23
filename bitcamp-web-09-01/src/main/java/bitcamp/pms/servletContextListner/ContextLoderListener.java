package bitcamp.pms.servletContextListner;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.annotation.WebListener;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import bitcamp.pms.Dao.BoardDAO;
import bitcamp.pms.Dao.ClassroomDAO;
import bitcamp.pms.Dao.MemberDAO;
import bitcamp.pms.Dao.TeamDAO;
import bitcamp.pms.context.ApplicationContext;
import bitcamp.pms.controller.MemberAddController;
import bitcamp.pms.controller.MemberDeleteController;
import bitcamp.pms.controller.MemberListController;
import bitcamp.pms.controller.MemberUpdateController;
import bitcamp.pms.controller.MemberViewController;
import bitcamp.pms.domain.Team;

@WebListener
public class ContextLoderListener implements javax.servlet.ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
       System.out.println("서버시작시 실행");
       
       String resource = "bitcamp/pms/config/mybatis-config.xml";
    try {
        InputStream inputStream;
        inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
       
       ApplicationContext iocContainer = new ApplicationContext("bitcamp.pms");
       
       iocContainer.addBean("sqlSessionFactory", sqlSessionFactory);
       
       iocContainer.refresh();
       ServletContext sc = sce.getServletContext();
       sc.setAttribute("iocContainer", iocContainer);
       
      
    } catch (Exception e) {
        e.printStackTrace();
    }
    }

    
}
