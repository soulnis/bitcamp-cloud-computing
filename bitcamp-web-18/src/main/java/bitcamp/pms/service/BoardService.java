package bitcamp.pms.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import bitcamp.pms.Dao.BoardDAO;
import bitcamp.pms.Dao.MemberDAO;
import bitcamp.pms.domain.Board;
import bitcamp.pms.domain.Member;
@Service
public class BoardService {

    @Autowired BoardDAO boardDao;
    
    public  void insert(Board board) throws Exception{
        boardDao.insert(board);
    };
    
    public  void delete(int no)throws Exception{
        boardDao.delete(no);
    };
    
    public  List<Board> selectList(int size, int page) throws Exception{
       
        HashMap<String, Object> params = new HashMap<>();
        params.put("startIndex",(page - 1)*size);
        params.put("pageSize", size);
        
        return boardDao.selectList(params);
    };
    
    public  int update(Board board) throws Exception{
        return boardDao.update(board);
    };
    
    public  Board selectOne(int no)throws Exception{
        return boardDao.selectOne(no);
    };
    
    public  int getTotalPage(int size) throws Exception{
        //모든 게시물 숫자 가져오기
        int count =  boardDao.countAll();
        int totalPage = count / size;
        if(count % size > 0) totalPage++;
        
        return totalPage;
        
    };
    
}
