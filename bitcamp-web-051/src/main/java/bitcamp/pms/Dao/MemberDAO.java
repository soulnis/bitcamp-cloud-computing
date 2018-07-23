package bitcamp.pms.Dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import bitcamp.pms.domain.Member;

public class MemberDAO {
    
    SqlSessionFactory sqlSessionFactory;
    
    public MemberDAO(SqlSessionFactory sqlSessionFactory){
        this.sqlSessionFactory = sqlSessionFactory;
    }

    public List<Member> memberList()throws Exception{
        try (SqlSession sqlSession =sqlSessionFactory.openSession()) {
            return sqlSession.selectList("member.selectList");
        }
    }
    
  public  Member view(String id) throws Exception{
      try (SqlSession sqlSession =sqlSessionFactory.openSession()) {
          return sqlSession.selectOne("member.selectOne", id);
        }
    }
    
    public int add(Member member) throws Exception{
        try (SqlSession sqlSession =sqlSessionFactory.openSession()) {
            int count =sqlSession.insert("member.insert",member);
            //mybatis는 default값이 오토커밋이 아니다. JDBC는 오토 커밋이다.
            sqlSession.commit();
            return count;
        }
    }
    
    public int update(Member member) throws Exception{
        try (SqlSession sqlSession =sqlSessionFactory.openSession()) {
            int count = sqlSession.update("member.update",member);
            sqlSession.commit();
            return count;
        }
   }
 
    public int delete(String id) throws Exception{
        try (SqlSession sqlSession =sqlSessionFactory.openSession()) {
            int count =  sqlSession.delete("member.delete",id);
            sqlSession.commit();
            return count;
        }
   }
}
