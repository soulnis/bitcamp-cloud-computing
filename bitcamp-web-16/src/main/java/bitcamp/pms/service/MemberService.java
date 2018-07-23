package bitcamp.pms.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bitcamp.pms.Dao.MemberDAO;
import bitcamp.pms.domain.Member;
@Service
public class MemberService {

    @Autowired MemberDAO memberdao;
    
    public List<Member> selectList(int size,int page){
        
        Map<String,Object> params = new HashMap<>();
        params.put("startIndex",(page - 1)*size);
        params.put("pageSize", size);
        
        return memberdao.selectList(params);
    }
    public  Member selectOne(String id) {
        
        return memberdao.selectOne(id);
    } 
    public int insert(Member member) {
        
        return memberdao.insert(member);
    } 
    public int update(Member member) {
        return memberdao.update(member);
    }
    public int delete(String id) {
        return memberdao.delete(id);
    }
    
    public int getTotalPage(int size) {
        //모든 게시물의 숫자 가져오기
        int count =  memberdao.countAll();
        //총 게시물수분에 size로 나누기
        int totalPage = count / size;
        //91개면 10개의 페이지가 필요하기때문에 1개때문에 ++해준다
        if(count % size >0) totalPage++;
        
        return totalPage;
    }
}
