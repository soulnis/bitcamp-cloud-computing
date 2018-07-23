package bitcamp.pms.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import bitcamp.pms.Dao.MemberDAO;
import bitcamp.pms.domain.Member;

@Controller
public class MemberViewController{

    MemberDAO memberDao;
    
    public MemberViewController(MemberDAO memberDao) {
        super();
        this.memberDao = memberDao;
    }
    
    public MemberViewController() {}
    
    @Autowired
    public void setMemberDao(MemberDAO memberDao) {
        this.memberDao = memberDao;
    }
    
    @RequestMapping("/member/view")
    public String view(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String id = request.getParameter("id");
        response.setContentType("text/html;charset=UTF-8");
        //PrintWriter객체는 출력 
        Member member = memberDao.selectOne(id);
        request.setAttribute("member", member);
        return "/member/view.jsp";
        }
}