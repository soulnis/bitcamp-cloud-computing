package bitcamp.pms.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import bitcamp.pms.Dao.MemberDAO;

@Controller
public class MemberDeleteController {
    
    MemberDAO memberDao;

    public MemberDeleteController(MemberDAO memberDao) {
        super();
        this.memberDao = memberDao;
    }
    
    public MemberDeleteController() {}
    @Autowired
    public void setMemberDao(MemberDAO memberDao) {
        this.memberDao = memberDao;
    }
    @RequestMapping("/member/delete")ss
    public String delete(HttpServletRequest request, HttpServletResponse response) throws Exception {
        memberDao.delete(request.getParameter("id"));
        return "redirect:list";
    }
}