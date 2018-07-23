package bitcamp.pms.board.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

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

@Controller("/board/delete")
public class BoardDeleteController {

    BoardDAO boardDao;
    
public BoardDeleteController() {
        super();
    }
public BoardDAO getBoardDao() {
        return boardDao;
    }
    @Autowired
    public void setBoardDao(BoardDAO boardDao) {
        this.boardDao = boardDao;
    }
@RequestMapping
public String delete(HttpServletRequest request, HttpServletResponse response) throws Exception {
   
    
    response.setContentType("text/html;charset=UTF-8");
    
    
        int no = Integer.parseInt(request.getParameter("no"));
            boardDao.delete(no);
       return "redirect:list";

    }
 }

