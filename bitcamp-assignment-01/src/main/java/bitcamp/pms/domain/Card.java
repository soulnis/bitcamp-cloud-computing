package bitcamp.pms.domain;

public class Card {

    private int no;
    private String name;
    private String email;
    private String mobile;
    private String phone;
    private String fax;
    private String memo;
    private int forno;
    public int getNo() {
        return no;
    }
    public void setNo(int no) {
        this.no = no;
    }
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
    public String getMobile() {
        return mobile;
    }
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getFax() {
        return fax;
    }
    public void setFax(String fax) {
        this.fax = fax;
    }
    public String getMemo() {
        return memo;
    }
    public void setMemo(String memo) {
        this.memo = memo;
    }
    public int getForno() {
        return forno;
    }
    public void setForno(int forno) {
        this.forno = forno;
    }
    
    @Override
    public String toString() {
        return "Card [no=" + no + ", name=" + name + ", email=" + email + ", mobile=" + mobile + ", phone=" + phone
                + ", fax=" + fax + ", memo=" + memo + ", forno=" + forno + "]";
    }
    
    
}
