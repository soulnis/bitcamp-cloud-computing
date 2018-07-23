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
import bitcamp.pms.domain.Team;
@WebListener
public class ServletContextListener implements javax.servlet.ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
       System.out.println("서버시작시 실행");
       
       String resource = "bitcamp/pms/config/mybatis-config.xml";
    try {
        InputStream inputStream;
        inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
       
       MemberDAO memberDao = new MemberDAO(sqlSessionFactory);
       BoardDAO boardDao =new BoardDAO(sqlSessionFactory);
       TeamDAO teamDao = new TeamDAO(sqlSessionFactory);
       ClassroomDAO classroomDao =new ClassroomDAO(sqlSessionFactory);
       ServletContext sc = sce.getServletContext();
       sc.setAttribute("memberDao", memberDao);
       sc.setAttribute("boardDao", boardDao);
       sc.setAttribute("classroomDao", classroomDao);
       sc.setAttribute("team", teamDao);
    } catch (IOException e) {
        e.printStackTrace();
    }
    }

    
}
