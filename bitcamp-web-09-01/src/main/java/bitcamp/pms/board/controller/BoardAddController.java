package bitcamp.pms.board.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Date;

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


@Controller("/board/add")
public class BoardAddController{
    
    BoardDAO boardDao;
    
    public BoardAddController() {
        super();
    }
   
    @Autowired
    public void setBoardDao(BoardDAO boardDao) {
        this.boardDao = boardDao;
    }
  
    @RequestMapping
    public String add(HttpServletRequest request, HttpServletResponse response) throws Exception {
        
        if(request.getMethod().equals("GET")) {
            System.out.println("겟이다@");
            return "/board/form.jsp"; 
        }
        
        Board board = new Board();
        board.setTitle(request.getParameter("title"));
        board.setContent(request.getParameter("content"));
        response.setContentType("text/html;charset=UTF-8");
        
        
        // 지정된 시간이 경과하면 특정 서블릿을 요청하도록 태그를 삽입!
        // => 웹브라우저는 meta 태그의 내용대로 동작한다.
        //    content='경과시간(초);url=요청할URL'
        //
            
            boardDao.add(board);
            return "redirect:list";
      
    }

    
  
 }
