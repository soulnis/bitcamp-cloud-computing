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
import bitcamp.pms.domain.Member;
@Controller
@RequestMapping("/member")
public class MemberController {


    @Autowired MemberDAO memberDao;

    @RequestMapping("list")
    public String list(@RequestParam(defaultValue="1") int page,
            @RequestParam(defaultValue="3") int size,Model model) throws Exception {
        
        if(page < 1) page =1;
        if(size < 1 || size > 20) size=3;
        
        //DB에서 가져올 데이터의 페이지 정보
        Map<String,Object> params = new HashMap<>();
            params.put("startIndex",(page - 1)*size);
            params.put("pageSize", size);
            List<Member> list =memberDao.selectList(params);
            model.addAttribute("list", list);
            return "member/list";
    }
    
    @GetMapping("form")
    public void form() throws Exception {
        /*  현재패스가 url과 같으면 리턴값 Stirng을 void로 해도됨         return "member/form"; */     
        }
    

    @PostMapping("add")
    public String add(Member member) throws Exception {
     
        memberDao.insert(member);
        return "redirect:list";
    
    }
    
    @RequestMapping("delete")
    public String delete(String id) throws Exception {
        memberDao.delete(id);
        return "redirect:list";
    }
    
    @RequestMapping("update")
    public String update(Member member) throws Exception {
     
      
           if(memberDao.update(member)==0) {
               return "member/updatefail";
           }else {
               return "redirect:list";
           }
    }
    //보통 조회시에 pathvariable을 사용함
    @RequestMapping("view/{id}")
    public String view(@PathVariable String id,Model model ) throws Exception {
        //PrintWriter객체는 출력 
        Member member = memberDao.selectOne(id);
        model.addAttribute("member", member);
        return "member/view";
        }
}
    
