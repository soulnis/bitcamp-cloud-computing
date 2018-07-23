package bitcamp.pms.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import bitcamp.pms.Dao.MemberDAO;
import bitcamp.pms.domain.Member;

@Service
public class MemeberService {

    @Autowired MemberDAO memberDao; 
    
    public List<Member> selectList(int size,int page){
        //DB에서 가져올 데이터의 페이지 정보
        Map<String,Object> params = new HashMap<>();
        params.put("startIndex",(page - 1)*size);
        params.put("pageSize", size);
        return memberDao.selectList(params);
    }
    
    public  Member selectOne(String id) {
        return memberDao.selectOne(id);
    } 
    
    public int insert(Member member) {
        return memberDao.insert(member);
    }
    
    @Transactional
    public int update(Member member) {
        int count = memberDao.update(member);
        
        if (count != 100)
            throw new RuntimeException("일부로 예외 발생!");
        
        return count;
    }
    
    public int delete(String id) {
        return memberDao.delete(id);
    }
    
    public int getTotalPage(int size) {
        int count = memberDao.countAll();
        int totalPage = count / size;
        
        if(count / size >0) totalPage++;
        return totalPage;
        
    }
    
}
