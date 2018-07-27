package bitcamp.pms.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import bitcamp.pms.Dao.MemberDAO;
import bitcamp.pms.Dao.TeamDAO;
import bitcamp.pms.domain.Member;
import bitcamp.pms.domain.Team;
@Controller
@RequestMapping("/team")
public class TeamController {


    @Autowired TeamDAO teamDao;

    @RequestMapping("list")
    public String list(Model model) throws Exception {
            List<Team> list =teamDao.selectList();
            model.addAttribute("list", list);
            return "team/list";
    }
    
    @GetMapping("form")
    public void form() throws Exception {
        /*  현재패스가 url과 같으면 리턴값 Stirng을 void로 해도됨         return "member/form"; */     
        }
    

    @PostMapping("add")
    public String add(Team team) throws Exception {
        teamDao.insert(team);
        return "redirect:list";
    
    }
    
    @RequestMapping("delete")
    public String delete(@RequestParam("name")String id) throws Exception {
        teamDao.delete(id);
        return "redirect:list";
    }
    
    @RequestMapping("update")
    public String update(Team team) throws Exception {
           if(teamDao.update(team)==0) {
               return "team/updatefail";
           }else {
               return "redirect:list";
           }
    }
    
    //보통 조회시에 pathvariable을 사용함
    @RequestMapping("view/{id}")
    public String view(@PathVariable String id,Model model ) throws Exception {
        //PrintWriter객체는 출력 
        Team team = teamDao.selectOne(id);
        model.addAttribute("team", team);
        return "team/view";
    }
}
    
