package bitcamp.pms.test;

import org.springframework.context.annotation.Bean;

import bitcamp.pms.Dao.MemberDAO;

public class MySpringConfig {
    
    @Bean("okok")
    public MemberDAO getMemberDao() {
        return new MemberDAO();
    }
}
