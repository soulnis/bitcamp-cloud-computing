package bitcamp.pms.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import bitcamp.pms.Dao.MemberDAO;
import bitcamp.pms.domain.Member;
@Controller
@RequestMapping("/member")
public class MemberController {
    @Autowired MemberDAO memberDao;
    
    @RequestMapping("list")
    public String list(@RequestParam(defaultValue="1")int page,@RequestParam(defaultValue="3")int size,Model model) throws Exception {
        
        if(page <0) page =1;
        if(size <0 && size > 20) size =3;
        
        //DB에서 가져올 데이터의 페이지 정보
        Map<String,Object> params = new HashMap<>();
            params.put("startIndex",(page - 1)*size);
            params.put("pageSize", size);
        
            List<Member> list =memberDao.selectList(params);
            //request.setAttribute에 직접저장하는것이 아니라 모델로 데이터를 넘겨서
            //디스패쳐 서블릿이 setattribute에 알아서 넣어준다 map이나 model 그리고 ModelAndView객체반환시에도
            model.addAttribute("list", list);
            return "member/list";
    }
    
    @RequestMapping("view/{id}")
    public String view(@PathVariable String id,Model model) throws Exception {
        Member member = memberDao.selectOne(id);
        model.addAttribute("member", member);
        return "member/view";
    }
    
    @GetMapping("form")
    public void form() throws Exception {
    
    }
    
    
    @RequestMapping("add")
    public String add(Member member) throws Exception {
        
        memberDao.insert(member);
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
    
    @RequestMapping("delete")
    public String delete(@RequestParam String id) throws Exception {
        memberDao.delete(id);
        return "redirect:list";
    }
}
    
