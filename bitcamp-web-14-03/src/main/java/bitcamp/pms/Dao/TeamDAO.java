package bitcamp.pms.Dao;


import java.util.List;


import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;


import bitcamp.pms.domain.Team;

public class TeamDAO {
    
    SqlSessionFactory sqlSessionFactory;
       
       public TeamDAO(SqlSessionFactory sqlSessionFactory) {
       
           this.sqlSessionFactory =sqlSessionFactory;
       }
       public TeamDAO() {}
       public List<Team> teamList()throws Exception{
           
         
           try (
                   SqlSession sqlSession = sqlSessionFactory.openSession()
              ) {
               return sqlSession.selectList("team.selectList");
              
           }
       }
       
       /*public  Member view(String id) throws Exception{
           Member member =new Member();
          
           try (
                   Connection con = DriverManager.getConnection(
                           jdbcUrl,
                           name, password);
               PreparedStatement stmt = con.prepareStatement(
                   "select mid,email from pms2_member where mid=?");) {
               
               stmt.setString(1, id);
               
               try (ResultSet rs = stmt.executeQuery();) {
                   if (!rs.next()) {
                      return null;
                   } else { 
                     member.setEmail(rs.getString("email"));
                     member.setId(rs.getString("mid"));
                     return member;
                   }
               }
           }  
        
       }
       
       public int add(Member member) throws Exception{
           try (
                   Connection con = DriverManager.getConnection(
                           jdbcUrl,
                           name, password);
               PreparedStatement stmt = con.prepareStatement(
                   "insert into pms2_member(mid,email,pwd) values(?,?,password(?))");) {
               
               stmt.setString(1, member.getId());
               stmt.setString(2, member.getEmail());
               stmt.setString(3, member.getPassword());
           
               return stmt.executeUpdate();
           }
           }
       
       public int update(Member member) throws Exception{
           
           try (
                   Connection con = DriverManager.getConnection(
                           jdbcUrl,
                           name, password);
               PreparedStatement stmt = con.prepareStatement(
                   "update pms2_member set email=?, pwd=password(?) where mid=?");) {
               
               stmt.setString(1, member.getEmail());
               stmt.setString(2, member.getPassword());
               stmt.setString(3, member.getId());
               
               return stmt.executeUpdate();          
       }
      }
    
       public int delete(String id) throws Exception{
        try (
                Connection con = DriverManager.getConnection(
                        jdbcUrl,
                        name, password);
            PreparedStatement stmt = con.prepareStatement(
                "delete from pms2_member where mid=?");) {
            
             stmt.setString(1,id);
             return stmt.executeUpdate() ;
        }
    }*/
}
