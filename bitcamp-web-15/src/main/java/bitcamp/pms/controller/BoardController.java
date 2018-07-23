package bitcamp.pms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import bitcamp.pms.Dao.BoardDAO;
import bitcamp.pms.domain.Board;
@Controller
@RequestMapping("/board")
public class BoardController {


    @Autowired BoardDAO boardDao;

    @RequestMapping("list")
    public String list(Model model) throws Exception {
            List<Board> list =boardDao.selectList();
            model.addAttribute("list", list);
            return "board/list";
    }
    
    @GetMapping("form")
    public void form() throws Exception {
        /*  현재패스가 url과 같으면 리턴값 Stirng을 void로 해도됨         return "member/form"; */     
        }
    

    @PostMapping("add")
    public String add(Board board) throws Exception {
     
        boardDao.insert(board);
        return "redirect:list";
    
    }
    
    @RequestMapping("delete")
    public String delete(int no) throws Exception {
        boardDao.delete(no);
        return "redirect:list";
    }
    
    @RequestMapping("update")
    public String update(Board board) throws Exception {
     
        System.out.println(board.getContent());
           if(boardDao.update(board) == 0) {
               return "board/updatefail";
           }else {
               return "redirect:list";
           }
    }
    //보통 조회시에 pathvariable을 사용함
    @RequestMapping("view/{no}")
    public String view(@PathVariable int no,Model model ) throws Exception {
        //PrintWriter객체는 출력 
        Board board = boardDao.selectOne(no);
        model.addAttribute("board", board);
        return "board/view";
        }
}
    
