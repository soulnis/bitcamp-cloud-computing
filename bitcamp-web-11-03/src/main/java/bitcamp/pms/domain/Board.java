package bitcamp.pms.domain;

import java.sql.Date;

public class Board {
    private String title;
    private String content;
    private int bno;
    private Date cnt;
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public int getBno() {
        return bno;
    }
    public void setBno(int bno) {
        this.bno = bno;
    }
    public Date getCnt() {
        return cnt;
    }
    public void setCnt(Date cnt) {
        this.cnt = cnt;
    }
    
   
    
    
   
}
