package bitcamp.pms.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.GenericServlet;
import javax.servlet.RequestDispatcher;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bitcamp.pms.Dao.MemberDAO;
import bitcamp.pms.domain.Member;


public class MemberListController implements PageController{

    MemberDAO memberDao;
    
    

    public MemberListController(MemberDAO memberDao) {
        super();
        this.memberDao = memberDao;
    }



    @Override
    public String service(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Map<String,Object> params = new HashMap<>();
        
        if(request.getParameter("page") != null && request.getParameter("size") != null) {
            int page = Integer.parseInt(request.getParameter("page"));
            int size = Integer.parseInt(request.getParameter("size"));
            
            params.put("startIndex", (page-1)*size);
            params.put("pageSize", size);
        }
        
       
                List<Member> list =memberDao.memberList(params);
                request.setAttribute("list", list);
               return "/member/list.jsp";
            
        
    }
}
    
