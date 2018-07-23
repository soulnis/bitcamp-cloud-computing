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

import bitcamp.pms.domain.Classroom;
import bitcamp.pms.domain.Member;

public class ClassroomDAO {

    String jdbcUrl ;
    String name;
    String password ;
   
   public ClassroomDAO(String jdbcUrl,String name,String password){
       this.jdbcUrl = jdbcUrl;
       this.name = name;
       this.password = password;
   }
   
   static {
       try {
           Class.forName("com.mysql.jdbc.Driver");
       } catch (ClassNotFoundException e) {
           // TODO Auto-generated catch block
           e.printStackTrace();
       }
   }
   
   public List<Classroom> classroomList()throws Exception{
       try (
           Connection con = DriverManager.getConnection(
                   jdbcUrl,
                   name, password);
               PreparedStatement stmt = con.prepareStatement(
                       "select crno,titl,sdt,edt,room from pms2_classroom");
                   ResultSet rs = stmt.executeQuery();) {
           
           List<Classroom> list = new ArrayList<>();
           while (rs.next()) {
               Classroom classroom =new Classroom();
               classroom.setBno(rs.getInt("crno"));
               classroom.setTitle(rs.getString("titl"));
               classroom.setStartTime( rs.getDate("sdt"));
               classroom.setEndTime(rs.getDate("edt"));
               classroom.setRoom(rs.getString("room"));
               System.out.println(rs.getInt("crno"));
               list.add(classroom);
               
           }
           return list;
       }
   }
   
  public  Classroom view(int no) throws Exception{
     
       try (
               Connection con = DriverManager.getConnection(
                       jdbcUrl,
                       name, password);
               PreparedStatement stmt = con.prepareStatement(
                       "select crno,titl,sdt,edt,room from pms2_classroom where crno=?");) {
                   
               stmt.setInt(1, no);
           
           try (ResultSet rs = stmt.executeQuery();) {
               if (!rs.next()) {
                  return null;
               } else { 
                 Classroom classroom = new Classroom();
                 classroom.setBno(rs.getInt("crno"));
                 classroom.setTitle(rs.getString("titl"));
                 classroom.setStartTime( rs.getDate("sdt"));
                 classroom.setEndTime(rs.getDate("edt"));
                 classroom.setRoom(rs.getString("room"));
                 return classroom;
               }
           }
       }  
    
   }
   
   public int insert(Classroom classroom) throws Exception{
       try (
               Connection con = DriverManager.getConnection(
                       jdbcUrl,
                       name, password);
               PreparedStatement stmt = con.prepareStatement(
                       "insert into pms2_classroom(titl,sdt,edt,room) values(?,?,?,?)");) {
                   
                stmt.setString(1, classroom.getTitle());
                stmt.setDate(2,(java.sql.Date) classroom.getStartTime(), Calendar.getInstance(Locale.KOREAN));
                stmt.setDate(3,(java.sql.Date) classroom.getEndTime(), Calendar.getInstance(Locale.KOREAN));
                stmt.setString(4, classroom.getRoom());
       
           return stmt.executeUpdate();
       }
       }
   
   public int update(Classroom classroom) throws Exception{
       
       try (
               Connection con = DriverManager.getConnection(
                       jdbcUrl,
                       name, password);
               PreparedStatement stmt = con.prepareStatement(
                       "update pms2_classroom set titl=?, sdt=?, edt=?, room=? where crno=?");) {
                   
                   stmt.setString(1,classroom.getTitle());
                   stmt.setDate(2,(java.sql.Date) classroom.getStartTime(), Calendar.getInstance(Locale.KOREAN));
                   stmt.setDate(3,(java.sql.Date) classroom.getEndTime(), Calendar.getInstance(Locale.KOREAN));
                   stmt.setString(4, classroom.getRoom());
                   stmt.setInt(5, classroom.getBno());
          
           return stmt.executeUpdate();          
   }
  }

   public int delete(int no) throws Exception{
    try (
            Connection con = DriverManager.getConnection(
                    jdbcUrl,
                    name, password);
        PreparedStatement stmt = con.prepareStatement(
                "delete from pms2_classroom where crno=?");) {
            
         stmt.setInt(1, no);
         return stmt.executeUpdate() ;
    }
}
    
}
