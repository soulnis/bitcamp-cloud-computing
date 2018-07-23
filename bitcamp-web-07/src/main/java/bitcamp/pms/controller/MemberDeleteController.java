package bitcamp.pms.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bitcamp.pms.Dao.MemberDAO;


public class MemberDeleteController implements PageController {
    
    MemberDAO memberDao;

    public MemberDeleteController(MemberDAO memberDao) {
        super();
        this.memberDao = memberDao;
    }

    @Override
    public String service(HttpServletRequest request, HttpServletResponse response) throws Exception {
        memberDao.delete(request.getParameter("id"));
        return "redirect:list";
    }
}