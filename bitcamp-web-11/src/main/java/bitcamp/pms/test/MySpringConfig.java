package bitcamp.pms.test;

import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Bean;

import bitcamp.pms.dao.MemberDAO;
import bitcamp.pms.domain.Member;

public class MySpringConfig {
    
    @Bean("okok")
    public MemberDAO getMemberDao() {
        return new MemberDAO() {
            
            @Override
            public int update(Member member) {
                // TODO Auto-generated method stub
                return 0;
            }
            
            @Override
            public Member selectOne(String id) {
                // TODO Auto-generated method stub
                return null;
            }
            
            @Override
            public List<Member> selectList(Map<String, Object> params) {
                // TODO Auto-generated method stub
                return null;
            }
            
            @Override
            public int insert(Member member) {
                // TODO Auto-generated method stub
                return 0;
            }
            
            @Override
            public int delete(String id) {
                // TODO Auto-generated method stub
                return 0;
            }
        };
    }
}
