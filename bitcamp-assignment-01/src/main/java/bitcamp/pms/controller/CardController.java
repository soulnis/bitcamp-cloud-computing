package bitcamp.pms.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import bitcamp.pms.domain.Card;
import bitcamp.pms.service.CardService;

@RestController
@RequestMapping("/card")
public class CardController {

    @Autowired CardService cardservice;
    
    @RequestMapping("/insert")
    public ResponseEntity<Integer> insert(HttpServletRequest req,HttpServletResponse res,Card card) throws Exception {
        ResponseEntity<Integer> entity = null;
        cardservice.insert(card);
        System.out.println(card.getNo());
        
        try {
            entity = new ResponseEntity<Integer>(card.getNo(),HttpStatus.OK);   
        } catch (Exception e) {
            entity = new ResponseEntity<Integer>(0,HttpStatus.BAD_REQUEST);     
        }
        return entity;
    }
    
    @RequestMapping("namelist")
    public ResponseEntity<Object> nameList(HttpSession session,HttpServletRequest req)throws Exception{
        ResponseEntity<Object> entity = null;
        Card card = new Card();
        List<Card> cardList = cardservice.nameList(card);
        System.out.println(cardList);
        try {
            entity = new ResponseEntity<Object>(cardList,HttpStatus.OK);   
        } catch (Exception e) {
            entity = new ResponseEntity<Object>(e.getMessage(),HttpStatus.BAD_REQUEST);     
        }
        return entity;
    }
    
    @RequestMapping("cardview")
    public ResponseEntity<Object> cardview(HttpSession session,HttpServletRequest req,Card card)throws Exception{
        ResponseEntity<Object> entity = null;
        cardservice.cardView(card);
        try {
            entity = new ResponseEntity<Object>(cardservice.cardView(card),HttpStatus.OK);   
        } catch (Exception e) {
            entity = new ResponseEntity<Object>(e.getMessage(),HttpStatus.BAD_REQUEST);     
        }
        return entity;
    }
    
    @RequestMapping("update")
    public ResponseEntity<Object> cardUpdate(HttpSession session,HttpServletRequest req,Card card)throws Exception{
        ResponseEntity<Object> entity = null;
        System.out.println(card.toString());
        cardservice.cardUpdate(card);
        try {
            entity = new ResponseEntity<Object>(cardservice.cardView(card),HttpStatus.OK);   
        } catch (Exception e) {
            entity = new ResponseEntity<Object>(e.getMessage(),HttpStatus.BAD_REQUEST);     
        }
        return entity;
    }
    
    @RequestMapping("search")
    public ResponseEntity<Object> search(HttpSession session,HttpServletRequest req,String search)throws Exception{
        ResponseEntity<Object> entity = null;
        System.out.println(search);
        List<Card> list = cardservice.search(search);
        //req.getParameter("search");
        try {
            entity = new ResponseEntity<Object>(list,HttpStatus.OK);   
        } catch (Exception e) {
            entity = new ResponseEntity<Object>(e.getMessage(),HttpStatus.BAD_REQUEST);     
        }
        return entity;
    }
    
    @RequestMapping("delete")
    public ResponseEntity<Object> delete(HttpSession session,HttpServletRequest req,Card card)throws Exception{
        ResponseEntity<Object> entity = null;
        cardservice.delete(card.getNo());
        try {
            entity = new ResponseEntity<Object>("success",HttpStatus.OK);   
        } catch (Exception e) {
            entity = new ResponseEntity<Object>(e.getMessage(),HttpStatus.BAD_REQUEST);     
        }
        System.out.println(entity.toString());
        return entity;
    }
}
