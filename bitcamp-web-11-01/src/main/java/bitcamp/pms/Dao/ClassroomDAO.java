package bitcamp.pms.Dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import bitcamp.pms.domain.Classroom;
import bitcamp.pms.domain.Member;

public class ClassroomDAO {
    SqlSessionFactory sqlSessionFactory;
    
    public ClassroomDAO() {}
   
   public ClassroomDAO(SqlSessionFactory sqlSessionFactory) {
    this.sqlSessionFactory = sqlSessionFactory;
   }


   
   public List<Classroom> classroomList()throws Exception{
       try ( SqlSession sqlSession = sqlSessionFactory.openSession();
           ) {
          return sqlSession.selectList("classroom.selectList");
       }
   }
   
  public  Classroom view(int no) throws Exception{
     
      try ( SqlSession sqlSession = sqlSessionFactory.openSession();
              ) {
             return sqlSession.selectOne("classroom.selectOne",no);
          }
    
   }
   
   public void insert(Classroom classroom) throws Exception{
       try ( SqlSession sqlSession = sqlSessionFactory.openSession();
               ) {
              sqlSession.insert("classroom.insert",classroom);
              sqlSession.commit();
           }
       }
   
   public void update(Classroom classroom) throws Exception{
       try ( SqlSession sqlSession = sqlSessionFactory.openSession();
               ) {
              sqlSession.update("classroom.update",classroom);
              sqlSession.commit();
           }      
   }


   public void delete(int no) throws Exception{
       try ( SqlSession sqlSession = sqlSessionFactory.openSession();
               ) {
               
              sqlSession.delete("classroom.delete",no);
              sqlSession.commit();
           }
}
    
}
