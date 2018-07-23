package bitcamp.pms.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bitcamp.pms.Dao.MemberDAO;
import bitcamp.pms.domain.Member;


public class MemberViewController implements PageController {

    MemberDAO memberDao;
    
    
    public MemberViewController(MemberDAO memberDao) {
        super();
        this.memberDao = memberDao;
    }

   

    @Override
    public String service(HttpServletRequest request, HttpServletResponse response) throws Exception {
   

        String id = request.getParameter("id");
        
            Member member = memberDao.view(id);
            request.setAttribute("member", member);
            return "/member/view.jsp";
               
        
    }
   
  }

