package Tests;

public class Register {
    private String email;
    private String password;
    private String login;
    private Boolean adminFlag;
    public Register(String login,  String password){
        //this.email=email;
        this.adminFlag=false;
        this.login=login;
        this.password=password;
    }
}
