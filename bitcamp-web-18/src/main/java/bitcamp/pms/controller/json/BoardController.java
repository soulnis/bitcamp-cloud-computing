package bitcamp.pms.controller.json;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import bitcamp.pms.Dao.BoardDAO;
import bitcamp.pms.domain.Board;
import bitcamp.pms.service.BoardService;

@RestController
@RequestMapping("/board")
public class BoardController {


    @Autowired BoardService boardService;

    @RequestMapping("list")
    public Object list(@RequestParam(defaultValue="1") int page,
            @RequestParam(defaultValue="3") int size) throws Exception {
            
            if(page < 1) page = 1;
            if(size <1 ||size >20) size = 3;
             
            HashMap<String,Object> map = new HashMap<>();
            
            List<Board> list =boardService.selectList(size,page);
            map.put("list", list);
            map.put("size",size);
            map.put("page",page);
            map.put("totalPage", boardService.getTotalPage(size));
            return map;
    }
    
    @GetMapping("form")
    public void form() throws Exception {
        /*  현재패스가 url과 같으면 리턴값 Stirng을 void로 해도됨         return "member/form"; */     
        }
    

    @PostMapping("add")
    public Object add(Board board) throws Exception {
        boardService.insert(board);
        HashMap<String, Object> result = new HashMap<>();
        result.put("status", "success");
        return result;
    
    }
    
    @RequestMapping("delete")
    public Object delete(int bno) throws Exception {
        HashMap<String, Object> result = new HashMap<>();
        
        boardService.delete(bno);
        result.put("status", "success");
        return result;
    }
    
    @RequestMapping("update")
    public Object update(Board board) throws Exception {
        HashMap<String, Object> result = new HashMap<>();
        
           if(boardService.update(board) == 0) {
               result.put("status", "fail");
               result.put("error", "에러입니다.");
           }else {
               result.put("status", "success");
           }
           return result;
    }
    //보통 조회시에 pathvariable을 사용함
    @RequestMapping("view/{no}")
    public Object view(@PathVariable int no,Model model ) throws Exception {
        
        HashMap<String, Object> map = new HashMap<>();
        
        //PrintWriter객체는 출력 
        Board board = boardService.selectOne(no);
        map.put("board", board);
        return map;
        }
}
    
