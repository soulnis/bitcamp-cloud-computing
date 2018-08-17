package bitcamp.assignment.web.json;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import bitcamp.assignment.domain.Member;
import bitcamp.assignment.service.MemberService;

@RestController
@RequestMapping("/member")
public class MemberController {
    
    @Autowired MemberService memberService;
    
    
    //@RequestMapping(path="singup",method=RequestMethod.POST) //path == value 랑 같다 url을 저장해둠
    @PostMapping("singUp")
    public Object signUp(Member member) {
        System.out.println(member); // .tostring()을 쓰지않아도 자동적으로 tostring을 실행함
        HashMap<String,Object> result =new HashMap<>();
        try {
            memberService.add(member);
            result.put("status", "success");
        } catch (Exception e) {
            result.put("status", "fail");
            result.put("message", e.getStackTrace());
        }
        
        return result;
        
    }
}








