package bitcamp.pms.Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bitcamp.pms.domain.Board;

public class BoardDAO {
    String jdbcUrl ="jdbc:mysql://52.79.239.97:3306/studydb";
    String name ="study";
    String password ="1111";
    
    
    public BoardDAO( String jdbcUrl,String name,String password) {
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
    
    public  void add(Board board) throws Exception {
       
        try (
                Connection con = DriverManager.getConnection(
                        jdbcUrl,
                        name, password);
                PreparedStatement stmt = con.prepareStatement(
                        "insert into pms2_board(titl,cont,cdt) values(?,?,now())");) {
            
            stmt.setString(1, board.getTitle());
            stmt.setString(2, board.getContent());
           
            stmt.executeUpdate();  
    }
   }   
    
    public  int delete(int no)throws Exception{
      
        try (
                Connection con = DriverManager.getConnection(
                        jdbcUrl,
                        name, password);
            PreparedStatement stmt = con.prepareStatement(
                "delete from pms2_board where bno=?");) {
            
            stmt.setInt(1, no);
         
        return   stmt.executeUpdate() ;
        } 
       }
    
    public  List<Board> boardList() throws Exception{
       
        try (
                Connection con = DriverManager.getConnection(
                        jdbcUrl,
                        name, password);
            PreparedStatement stmt = con.prepareStatement(
                "select bno,titl,cdt from pms2_board");
            ResultSet rs = stmt.executeQuery();) {
            
            List<Board> list = new ArrayList<>();
            while (rs.next()) {
                
              Board board=new Board();
              board.setBno(rs.getInt("bno"));
              board.setTitle(rs.getString("titl"));
              board.setCnt( rs.getDate("cdt")); 
              list.add(board);    
            }
           return list;
        }
    }
    
    public  int update(Board board) throws Exception{
        try (
                Connection con = DriverManager.getConnection(
                        jdbcUrl,
                        name, password);
            PreparedStatement stmt = con.prepareStatement(
                "update pms2_board set titl=?, cont=?, cdt=now() where bno=?");) {
            
            stmt.setString(1,board.getTitle() );
            stmt.setString(2,board.getContent() );
            stmt.setInt(3, board.getBno());
            
            return stmt.executeUpdate() ;
    }
   }
    
    
    public  Board boardView(int no)throws Exception{
        Board board=new Board();
        
        try (
                Connection con = DriverManager.getConnection(
                        jdbcUrl,
                        name, password);
                PreparedStatement stmt = con.prepareStatement(
                        "select bno,titl,cont,cdt from pms2_board where bno=?");) {

            stmt.setInt(1, no);

    try (ResultSet rs = stmt.executeQuery();) {
        
        if (!rs.next()) {
           throw new Exception("<p>유효하지 않은 게시물 번호입니다.</p>");
        } else {
            board.setBno(rs.getInt("bno"));
            board.setTitle(rs.getString("titl"));
            board.setContent(rs.getString("cont"));
            board.setCnt(rs.getDate("cdt"));
            }
          }
        }
         return board;
     }
}
