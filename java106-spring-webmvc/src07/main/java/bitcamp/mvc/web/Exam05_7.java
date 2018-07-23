// 요청 핸들러의 파라미터 - Map, Model 받기
package bitcamp.mvc.web;

import java.util.Map;

import javax.servlet.ServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller 
@RequestMapping("/exam05_7") 
public class Exam05_7 {
    
    
    @GetMapping(value="m0")
    public String m0(ServletRequest request) {
        request.setAttribute("name", "홍길동");
        request.setAttribute("age", 20);
        return "/exam05_7.jsp";
    }
    // View 컴포넌트(JSP)에게 전달할 데이터가 있다면 
    // 프론트 컨트롤러에게 Map 또는 Model 객체를 요구하라. 비어있는 ,Map과 Model은 똑같다 넣는방법만 다르다
    // 그리고 이들 객체에 값을 담아 놓으면,
    // 프론트 컨트롤러가 이들 객체에 담아 놓은 값을 View 컴포넌트(jsp)가 사용할 수 있도록
    // 다시 ServletRequest 보관소에 복사한다.
    // 세가지 아래 다 똑같은방법 사용방법만 다를듯 서블릿기술을 최소화한다. 
    @GetMapping(value="m1")  
    public String m1(Map<String,Object> map) {
        map.put("name", "홍길동");
        map.put("age", 20);
        return "/exam05_7.jsp";
    }
    
    @GetMapping(value="m2")  
    public String m2(Model model) {
        model.addAttribute("name", "홍길동");
        model.addAttribute("age", 20);
        return "/exam05_7.jsp";
    }
    
    @GetMapping(value="m3")  
    public ModelAndView m3() {
        ModelAndView mv = new ModelAndView();
        mv.addObject("name", "홍길동");
        mv.addObject("age", 20);
        mv.setViewName("/exam05_7.jsp");
        return mv;
    }
    
}







