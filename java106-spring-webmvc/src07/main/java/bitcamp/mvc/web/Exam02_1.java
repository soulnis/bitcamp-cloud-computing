// 페이지 컨트롤러에 의존 객체 주입하기 - 생성자 이용
package bitcamp.mvc.web;

import javax.servlet.ServletContext;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import bitcamp.mvc.dao.BoardDao;

@Controller 
@RequestMapping("/exam02_1") 
public class Exam02_1 {
    
    BoardDao boardDao;
    
    // ServletContext를 주입 받을 때도 인스턴스 변수로 주입받는다.
    ServletContext servletContext;
    
    //생성자를 통해서 주입가능하다 @Inject등쓸필요없다. 최근추세는 생성자를 통한주입
    public Exam02_1(BoardDao boardDao, ServletContext servletContext) {
        this.boardDao = boardDao;
        this.servletContext = servletContext;
    }
    
    
    @RequestMapping("m1")  
    @ResponseBody  
    public String m1() {
        return this.servletContext.getContextPath();
    }
    
}
