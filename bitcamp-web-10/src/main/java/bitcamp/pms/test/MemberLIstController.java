package bitcamp.pms.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
@Component
public class MemberLIstController {

    MemberDAO memberDao;

    @Autowired
    public void setMemberDao(MemberDAO memberDao) {
        this.memberDao = memberDao;
    }

    @Override
    public String toString() {
        return "MemberLIstController [memberDao=" + memberDao + "]";
    }
    
}
