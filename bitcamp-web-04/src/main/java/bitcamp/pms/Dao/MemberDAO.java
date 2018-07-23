package bitcamp.pms.Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bitcamp.pms.domain.Member;

public class MemberDAO {
    
     String jdbcUrl ;
     String name;
    String password ;
    
    public MemberDAO(String jdbcUrl,String name,String password){
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
    public List<Member> memberList()throws Exception{
        
      
        try (
            Connection con = DriverManager.getConnection(
                    jdbcUrl,
                    name, password);
            PreparedStatement stmt = con.prepareStatement(
                "select mid, email from pms2_member");
            ResultSet rs = stmt.executeQuery();) {
            
            ArrayList<Member> list = new ArrayList<>();
            while (rs.next()) {
                Member member=new Member();
                
                member.setEmail(rs.getString("email"));
                member.setId(rs.getString("mid"));
                list.add(member);
            }
            return list;
           
        }
    }
    
    public  Member view(String id) throws Exception{
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
 }
}
