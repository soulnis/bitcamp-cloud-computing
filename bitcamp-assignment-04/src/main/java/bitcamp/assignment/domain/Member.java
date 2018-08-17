package bitcamp.assignment.domain;

import java.io.Serializable;

public class Member implements Serializable {
    //어느 버전에서 만들어졌는지 저장함.
    private static final long serialVersionUID = 1L;
    
    protected String name;
    protected String email;
    protected String password;
    
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
   
    @Override
    public String toString() {
        return "Member [name=" + name + ", email=" + email + ", password=" + password + "]";
    }
   
}
