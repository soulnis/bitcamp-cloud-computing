package bitcamp.pms.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bitcamp.pms.Dao.MemberDAO;
import bitcamp.pms.domain.Member;

public class MemberListController implements PageController{


    MemberDAO memberDao;
    
    public MemberListController(MemberDAO memberDao) {
        this.memberDao = memberDao;
    }
    
    @Override
    public String service(HttpServletRequest request, HttpServletResponse response) throws Exception {
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
    
