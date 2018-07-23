package bitcamp.pms.board.controller;

import java.util.List;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bitcamp.pms.Dao.BoardDAO;
import bitcamp.pms.annotation.Autowired;
import bitcamp.pms.annotation.Controller;
import bitcamp.pms.annotation.RequestMapping;
import bitcamp.pms.domain.Board;
import bitcamp.pms.domain.Member;


@Controller("/board/list")
public class BoardListController {

    
    BoardDAO boardDao;
    
   public BoardListController() {   }
  
    @Autowired
    public void setBoardDao(BoardDAO boardDao) {
        this.boardDao = boardDao;
    }

   @RequestMapping
   public String list(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // 출력할 때 String 객체의 값(UTF-16)을 어떤 문자표를 사용하여 인코딩해서 보낼 것인지 설정한다.
        // => 반드시 출력 스트림을 얻기 전에 설정해야 한다.
             response.setContentType("text/html;charset=UTF-8");
       
             List<Board> list = boardDao.boardList();
             request.setAttribute("list", list);
             return "/board/list.jsp";
    
    }
   
   
}

