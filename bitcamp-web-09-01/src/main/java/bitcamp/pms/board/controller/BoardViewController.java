package bitcamp.pms.board.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bitcamp.pms.Dao.BoardDAO;
import bitcamp.pms.annotation.Autowired;
import bitcamp.pms.annotation.Controller;
import bitcamp.pms.annotation.RequestMapping;
import bitcamp.pms.domain.Board;

@SuppressWarnings("serial")
@Controller("/board/view")
public class BoardViewController extends HttpServlet {
    BoardDAO boardDao;
    
    
    public BoardViewController() {
        super();
    }

   
    @Autowired
    public void setBoardDao(BoardDAO boardDao) {
        this.boardDao = boardDao;
    }

    @RequestMapping
    public String View(HttpServletRequest request, HttpServletResponse response) throws Exception {
     //request.setCharacterEncoding("UTF-8");
     System.out.println("뷰입니다.");
         int no = Integer.parseInt(request.getParameter("no"));
         Board board=boardDao.boardView(no);
         System.out.println("그다음음 뷰");
         request.setAttribute("board", board);
         System.out.println("그다음음 뷰뷰");
         return "/board/view.jsp";
         
  
 }



    
 
}

