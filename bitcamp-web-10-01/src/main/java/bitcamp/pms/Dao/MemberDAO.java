package bitcamp.pms.Dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import bitcamp.pms.domain.Member;
@org.springframework.stereotype.Repository
public class MemberDAO {
    
    SqlSessionFactory sqlSessionFactory;
    
    public MemberDAO() {}
    
    public MemberDAO(SqlSessionFactory sqlSessionFactory){
        this.sqlSessionFactory = sqlSessionFactory;
    }
    @Autowired
    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    public List<Member> memberList(Map<String,Object> params)throws Exception{
        try (
            SqlSession sqlSession = sqlSessionFactory.openSession()) {
            return sqlSession.selectList("member.selectList",params);
        }
    }
    
    public  Member view(String id) throws Exception{
      try (
              SqlSession sqlSession = sqlSessionFactory.openSession()) {
              return sqlSession.selectOne("member.selectOne",id);
          }
    }  
     
    
    
    public int add(Member member) throws Exception{
        try (
                SqlSession sqlSession = sqlSessionFactory.openSession()) {
                int count = sqlSession.insert("member.insert",member);
                sqlSession.commit();
                return count;
            }
    }
    
    public int update(Member member) throws Exception{
        
        try (
                SqlSession sqlSession = sqlSessionFactory.openSession()) {
                int count = sqlSession.update("member.update",member);
                sqlSession.commit();
                return count;
            }
    }
 
    public int delete(String id) throws Exception{
        try (
                SqlSession sqlSession = sqlSessionFactory.openSession()) {
                int count = sqlSession.delete("member.delete",id);
                sqlSession.commit();
                return count;
            }
    }
}
