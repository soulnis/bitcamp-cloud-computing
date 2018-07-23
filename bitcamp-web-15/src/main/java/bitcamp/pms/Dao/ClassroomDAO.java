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

public interface ClassroomDAO {
   public List<Classroom> selectList()throws Exception;
   public  Classroom selectOne(int no) throws Exception;
   public void insert(Classroom classroom) throws Exception;
   public int update(Classroom classroom) throws Exception;
   public void delete(int no) throws Exception;
    
}
