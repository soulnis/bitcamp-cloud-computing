package bitcamp.pms.Dao;

import java.util.List;
import java.util.Map;

import bitcamp.pms.domain.Board;

public interface BoardDAO {
    public  void insert(Board board) throws Exception;
    public  void delete(int no)throws Exception;
    public  List<Board> selectList(Map<String, Object> params) throws Exception;
    public  int update(Board board) throws Exception;
    public  Board selectOne(int no)throws Exception;
    public  int countAll() throws Exception;
}
