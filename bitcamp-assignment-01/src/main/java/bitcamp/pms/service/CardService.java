package bitcamp.pms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bitcamp.pms.dao.Carddao;
import bitcamp.pms.dao.Memberdao;
import bitcamp.pms.domain.Card;
import bitcamp.pms.domain.Member;

@Service
public class CardService {

   @Autowired Carddao carddao;
   
   public int insert(Card card) throws Exception{
       return carddao.insert(card);
   }
   public List<Card> nameList(Card card) throws Exception{
       System.out.println(card.getForno());
       return carddao.nameList(card);
   }
   
   public Card cardView(Card card) throws Exception{
       
       return carddao.cardView(card);
   }
   
   public int cardUpdate(Card card) throws Exception{
       return carddao.cardUpdate(card);
   }
   
   public List<Card> search(String query)throws Exception{
       return carddao.search(query);
   }
   public int delete(int no)throws Exception{
       return carddao.delete(no);
   }
}
