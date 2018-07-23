package bitcamp.pms.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bitcamp.pms.Dao.MemberDAO;
import bitcamp.pms.annotation.Autowired;
import bitcamp.pms.annotation.Controller;
import bitcamp.pms.annotation.RequestMapping;
import bitcamp.pms.domain.Member;
@Controller("/member/list")
public class MemberListController {


    MemberDAO memberDao;
    
    public MemberListController(MemberDAO memberDao) {
        this.memberDao = memberDao;
    }    

    public MemberListController() {}
    @Autowired
    public void setMemberDao(MemberDAO memberDao) {
        this.memberDao = memberDao;
    }
    
    @RequestMapping
    public String list(HttpServletRequest request, HttpServletResponse response) throws Exception {
        //DB에서 가져올 데이터의 페이지 정보
        Map<String,Object> params = new HashMap<>();
        if(request.getParameter("page") !=null && 
                request.getParameter("size")!=null ) {
            int page =Integer.parseInt(request.getParameter("page"));
            int size =Integer.parseInt(request.getParameter("size")); 
            
            params.put("startIndex",(page - 1)*size);
            params.put("pageSize", size);
        }
            List<Member> list =memberDao.memberList(params);
            request.setAttribute("list", list);
            return "/member/list.jsp";
    }
}
    
