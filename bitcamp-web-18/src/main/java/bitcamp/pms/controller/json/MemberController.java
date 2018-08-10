package bitcamp.pms.controller.json;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import bitcamp.pms.domain.Member;
import bitcamp.pms.service.MemberService;
@RestController
//@CrossOrigin
@RequestMapping("/member")
public class MemberController {


    @Autowired MemberService memberService;
    
    //@CrossOrigin
    @RequestMapping("list")
    public Object list(@RequestParam(defaultValue="1") int page,
            @RequestParam(defaultValue="3") int size) throws Exception {
        
        if(page < 1) page =1;
        if(size < 1 || size > 20) size=3;
        List<Member> list =memberService.selectList(size,page);
       
        HashMap<String,Object> data = new HashMap<>();
        
        data.put("list", list);
        data.put("size",size);
        data.put("page",page);
        data.put("totalPage",memberService.getTotalPage(size));
        //DB에서 가져올 데이터의 페이지 정보
        return data;
    }
    
    @GetMapping("form")
    public void form() throws Exception {
        /*  현재패스가 url과 같으면 리턴값 Stirng을 void로 해도됨         return "member/form"; */     
        }
    

    @PostMapping("add")
    public Object add(Member member) throws Exception {
        memberService.insert(member);
        HashMap<String,Object> result = new HashMap<>();
        result.put("status", "success");
        return result;
    
    }
    
    @RequestMapping("delete")
    public Object delete(String id) throws Exception {
        HashMap<String,Object> result = new HashMap<>();
        
        if(memberService.delete(id) == 0) {
            result.put("status", "fail");
            result.put("error","해당 아이디가 없습니다.");
        }else {
            result.put("status", "success");
        }
        return result;
    }
    
    @RequestMapping("update")
    public Object update(Member member) throws Exception {
           HashMap<String,Object> result = new HashMap<>();
        
           if(memberService.update(member) == 0) {
               result.put("status", "fail");
               result.put("error","해당 아이디가 없습니다.");
           }else {
               result.put("status", "success");
           }
           
           return result;
    }
    
    //보통 조회시에 pathvariable을 사용함
    @RequestMapping("view/{id}")
    public Object view(@PathVariable String id) throws Exception {
        HashMap<String,Object> data = new HashMap<>();
        data.put("member", memberService.selectOne(id));
        return data;
        }
}
    
