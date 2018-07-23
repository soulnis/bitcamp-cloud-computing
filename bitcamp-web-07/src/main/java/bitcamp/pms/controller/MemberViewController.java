package bitcamp.pms.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bitcamp.pms.Dao.MemberDAO;
import bitcamp.pms.domain.Member;


public class MemberViewController implements PageController  {

    MemberDAO memberDao;
    
    public MemberViewController(MemberDAO memberDao) {
        super();
        this.memberDao = memberDao;
    }

    @Override
    public String service(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String id = request.getParameter("id");
        response.setContentType("text/html;charset=UTF-8");
        //PrintWriter객체는 출력 
        Member member = memberDao.view(id);
        request.setAttribute("member", member);
        return "/member/view.jsp";
        }
}