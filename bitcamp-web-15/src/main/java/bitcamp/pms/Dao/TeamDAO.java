package bitcamp.pms.Dao;


import java.util.List;


import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;


import bitcamp.pms.domain.Team;

public interface TeamDAO {
       public List<Team> selectList()throws Exception;
       public Team selectOne(String id) throws Exception;
       public int insert(Team team) throws Exception;
       public int update(Team team) throws Exception;
       public int delete(String id) throws Exception;
}
