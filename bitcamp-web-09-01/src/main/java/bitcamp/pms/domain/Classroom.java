package bitcamp.pms.domain;

import java.sql.Date;

public class Classroom {

    protected int bno;
    protected String title;
    protected Date startTime;
    protected Date endTime;
    protected String room;
    
    public int getBno() {
        return bno;
    }
    public void setBno(int bno) {
        this.bno = bno;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public Date getStartTime() {
        return startTime;
    }
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }
    public Date getEndTime() {
        return endTime;
    }
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }
    public String getRoom() {
        return room;
    }
    public void setRoom(String room) {
        this.room = room;
    }
    
    @Override
    public String toString() {
        return "Classroom [bno=" + bno + ", title=" + title + ", startTime=" + startTime + ", endTime=" + endTime
                + ", room=" + room + "]";
    }
    
}
