package bitcamp.pms.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface PageController {
    public String service(HttpServletRequest request,HttpServletResponse response) throws Exception;
}
