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
    // 메서드에 @Transactional 애노테이션을 붙이면,
    // 메서드에서 수행하는 작업들을 한 단위로 묶어서 다루겠다는 의미다.
    // 따라서 작성들 중에 한 개의 작업이라도 실패하면,
    // 이전에 성공했던 모든 작업들이 취소된다.
    // 메서드 호출이 예외 없이 정상적으로 끝나면, 
    // DBMS에 commit을 요청하여 지금까지 한 작업을 실제 테이블에 적용시킨다.
    //@Transactional // => 애노테이션 대신 xml 태그로 지정할 수 있다.
    public int update(Member member) {
       //단위 처리이다보니 update가 성공 후 에 오류가 발생해서 다시 그전에 작업까지 모두 롤백된다.
        int count = memberdao.update(member);

      /*  if (count != 100)
            throw new RuntimeException("일부로 예외 발생!");*/
        
        return count;
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
