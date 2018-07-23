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
import bitcamp.pms.Dao.ClassroomDAO;
import bitcamp.pms.domain.Board;
import bitcamp.pms.domain.Classroom;
@Controller
@RequestMapping("/class")
public class ClassController {


    @Autowired ClassroomDAO classroomDao;

    @RequestMapping("list")
    public String list(Model model) throws Exception {
            List<Classroom> list =classroomDao.selectList();
            model.addAttribute("list", list);
            return "class/list";
    }
    
    @GetMapping("form")
    public void form() throws Exception {
        /*  현재패스가 url과 같으면 리턴값 Stirng을 void로 해도됨         return "member/form"; */     
        }
    

    @PostMapping("add")
    public String add(Classroom classroom) throws Exception {
        classroomDao.insert(classroom);
        return "redirect:list";
    
    }
    
    @RequestMapping("delete")
    public String delete(int no) throws Exception {
        System.out.println(no);
        classroomDao.delete(no);
        return "redirect:list";
    }
    
    @RequestMapping("update")
    public String update(Classroom classroom) throws Exception {
     
           if(classroomDao.update(classroom) == 0) {
               return "board/updatefail";
           }else {
               return "redirect:list";
           }
    }
   
    //보통 조회시에 pathvariable을 사용함
    @RequestMapping("view/{no}")
    public String view(@PathVariable int no,Model model ) throws Exception {
        //PrintWriter객체는 출력 
        Classroom classroom = classroomDao.selectOne(no);
        model.addAttribute("classroom", classroom);
        return "class/view";
    }
}
    
