package bitcamp.pms.servletContextListner;

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
public class ContextLoaderListener implements javax.servlet.ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
       System.out.println("서버시작시 실행");
       
     try {
       //DI 다오가쓸 객체를 외부에서만든다 복잡한경우를 대비해서
       
       //설계도 주소
       String resource = "bitcamp/pms/config/mybatis-config.xml";
       //설계도읽기
       InputStream inputStream = Resources.getResourceAsStream(resource);
       SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
       
       
       MemberDAO memberDao = new MemberDAO(sqlSessionFactory);
       BoardDAO boardDao =new BoardDAO("jdbc:mysql://52.79.239.97:3306/studydb","study","1111");
       ClassroomDAO classroomDao =new ClassroomDAO("jdbc:mysql://52.79.239.97:3306/studydb","study","1111");
       TeamDAO teamDao = new TeamDAO("jdbc:mysql://52.79.239.97:3306/studydb","study","1111");
       ServletContext sc = sce.getServletContext();
       sc.setAttribute("memberDao", memberDao);
       sc.setAttribute("boardDao", boardDao);
       sc.setAttribute("classroomDao", classroomDao);
       sc.setAttribute("team", teamDao);
     } catch (Exception e) {
       e.printStackTrace();
     }
  }
}
