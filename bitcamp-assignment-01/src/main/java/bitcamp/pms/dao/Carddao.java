package bitcamp.pms.dao;

import java.util.List;

import bitcamp.pms.domain.Card;

public interface Carddao {

    public int insert(Card card) throws Exception;
    public List<Card> nameList(Card card) throws Exception;
    public Card cardView(Card card) throws Exception;
    public int cardUpdate(Card card) throws Exception;
    public List<Card> search(String query)throws Exception;
    public int delete(int no)throws Exception;
}
