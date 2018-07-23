package bitcamp.pms.board.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bitcamp.pms.Dao.BoardDAO;
import bitcamp.pms.annotation.Autowired;
import bitcamp.pms.annotation.Controller;
import bitcamp.pms.annotation.RequestMapping;
import bitcamp.pms.domain.Board;


@Controller("/board/update")
public class BoardUpdateController {
    
    BoardDAO boardDao;
    
    
    
    
    public BoardUpdateController() {
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
   public String update(HttpServletRequest request, HttpServletResponse  response) throws Exception {
        
        Board board = new Board();
        board.setBno(Integer.parseInt(request.getParameter("no")));
        board.setContent(request.getParameter("content"));
        board.setTitle(request.getParameter("title"));
        response.setContentType("text/html;charset=UTF-8");
        
           boardDao.update(board);
           return "redirect:list";
        
    }
}
  

