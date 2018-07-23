package bitcamp.pms.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

@WebFilter("/*")
public class CharacterEncodingFilter implements Filter {

    @Override
    public void doFilter(
            ServletRequest request, 
            ServletResponse response, 
            FilterChain chain)
            throws IOException, ServletException {
        
        // ?„œë¸”ë¦¿?„ ?‹¤?–‰?•˜ê¸? ? „?— ?ˆ˜?–‰?•  ?‘?—…
        request.setCharacterEncoding("UTF-8");
        
        // ?‹¤?Œ ?•„?„° ?˜?Š” ?„œë¸”ë¦¿ ?‹¤?–‰
        chain.doFilter(request, response);
        
    }

}







