package bitcamp.pms.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import bitcamp.pms.domain.Member;
import bitcamp.pms.service.MemberService;

@RestController
@RequestMapping("/member")
public class MemberController {
 
    @Autowired MemberService memberservice;
    
    @RequestMapping("/hello")
    public ResponseEntity<String> test(HttpServletRequest req,HttpServletResponse res) {
        ResponseEntity<String> entity = null;
        try {
            entity = new ResponseEntity<String>("SUCCESS",HttpStatus.OK);   
        } catch (Exception e) {
            entity = new ResponseEntity<String>(e.getMessage(),HttpStatus.BAD_REQUEST);     
        }
        
        return entity;
    }
 
    @RequestMapping("/insert")
    @ResponseBody
    public ResponseEntity<String> insert(HttpServletRequest req,HttpServletResponse res,Member member) throws Exception {
        ResponseEntity<String> entity = null;
        memberservice.insert(member);
        System.out.println(member.getEmail());
        
        try {
            entity = new ResponseEntity<String>("SUCCESS",HttpStatus.OK);   
        } catch (Exception e) {
            entity = new ResponseEntity<String>(e.getMessage(),HttpStatus.BAD_REQUEST);     
        }
        
        return entity;
    }
    
    @RequestMapping("/login")
    public ResponseEntity<String> login(HttpServletRequest req,HttpServletResponse res,Member member) throws Exception {
        HttpSession session = req.getSession();
        ResponseEntity<String> entity = null;
        int count = memberservice.login(member);
        if (count == 1) {
            session.setAttribute("login", memberservice.user(member));
            return  entity = new ResponseEntity<String>("SUCCESS",HttpStatus.OK);
        } else {
            return entity = new ResponseEntity<String>("error",HttpStatus.BAD_REQUEST);
        }
    }
    
    @RequestMapping("logout")
    public ResponseEntity<String> logout(HttpSession session)throws Exception{
        ResponseEntity<String> entity = null;
        
        session.invalidate();//세션 종료
        try {
            entity = new ResponseEntity<String>("SUCCESS",HttpStatus.OK);   
        } catch (Exception e) {
            entity = new ResponseEntity<String>(e.getMessage(),HttpStatus.BAD_REQUEST);     
        }
        return entity;
    }
    
   
    @RequestMapping("delete")
    public ResponseEntity<String> remove(HttpSession session,HttpServletRequest req)throws Exception{
        ResponseEntity<String> entity = null;
        Member user = (Member) req.getSession().getAttribute("login");
        System.out.println(user.getName());
        session.invalidate();//세션 종료
        memberservice.remove(user);
        try {
            entity = new ResponseEntity<String>("SUCCESS",HttpStatus.OK);   
        } catch (Exception e) {
            entity = new ResponseEntity<String>(e.getMessage(),HttpStatus.BAD_REQUEST);     
        }
        return entity;
    }
    
}
