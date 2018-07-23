package bitcamp.pms.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bitcamp.pms.Dao.MemberDAO;
import bitcamp.pms.annotation.RequestMapping;


public class MemberDeleteController {
    
    MemberDAO memberDao;

    public MemberDeleteController(MemberDAO memberDao) {
        super();
        this.memberDao = memberDao;
    }

    @RequestMapping
    public String delete(HttpServletRequest request, HttpServletResponse response) throws Exception {
        memberDao.delete(request.getParameter("id"));
        return "redirect:list";
    }
}