package bitcamp.pms.servletContextListner;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.annotation.WebListener;

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
       MemberDAO memberDao = new MemberDAO("jdbc:mysql://52.79.239.97:3306/studydb","study","1111");
       BoardDAO boardDao =new BoardDAO("jdbc:mysql://52.79.239.97:3306/studydb","study","1111");
       ClassroomDAO classroomDao =new ClassroomDAO("jdbc:mysql://52.79.239.97:3306/studydb","study","1111");
       TeamDAO teamDao = new TeamDAO("jdbc:mysql://52.79.239.97:3306/studydb","study","1111");
       ServletContext sc = sce.getServletContext();
       sc.setAttribute("memberDao", memberDao);
       sc.setAttribute("boardDao", boardDao);
       sc.setAttribute("classroomDao", classroomDao);
       sc.setAttribute("team", teamDao);
    }

    
}
