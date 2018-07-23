package bitcamp.pms.domain;

import java.sql.Date;

public class Team {
   protected String name;
   protected String description;
   protected int maxQty;
   protected Date startTime;
   protected Date endTime;
public String getName() {
    return name;
}
public void setName(String name) {
    this.name = name;
}
public String getDescription() {
    return description;
}
public void setDescription(String description) {
    this.description = description;
}
public int getMaxQty() {
    return maxQty;
}
public void setMaxQty(int maxQty) {
    this.maxQty = maxQty;
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
@Override
public String toString() {
    return "Team [name=" + name + ", description=" + description + ", maxQty=" + maxQty + ", startTime=" + startTime
            + ", endTime=" + endTime + "]";
}
   
   
}
