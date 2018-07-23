package bitcamp.pms.Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import bitcamp.pms.domain.Board;

public class BoardDAO {
   
    SqlSessionFactory sqlSessionFactory;
    
    public BoardDAO(SqlSessionFactory sqlSessionFactory) {
       this.sqlSessionFactory = sqlSessionFactory;
    }
    
    public  void add(Board board) throws Exception {
       
        try (
               SqlSession sqlSession = sqlSessionFactory.openSession();
               ) {
          sqlSession.insert("board.insert",board);
          sqlSession.commit();
          
          
    }
   }   
    
    public  void delete(int no)throws Exception{
      
        try (
                SqlSession sqlSession = sqlSessionFactory.openSession();
                ) {
           sqlSession.delete("board.delete",no);
           sqlSession.commit();
           
       }
    }
    
    public  List<Board> boardList() throws Exception{
       
        try (
                SqlSession sqlSession = sqlSessionFactory.openSession();
                ) {
          return sqlSession.selectList("board.selectList");
           
        }
    }
    
    public  void update(Board board) throws Exception{
        try (
                SqlSession sqlSession = sqlSessionFactory.openSession();
                ) {
           sqlSession.update("board.update",board);
           sqlSession.commit();
        }
   }
    
    
    public  Board boardView(int no)throws Exception{
        try (SqlSession sqlSession = sqlSessionFactory.openSession();) 
        {
          return sqlSession.selectOne("board.selectOne",no);
        }
     }
}
