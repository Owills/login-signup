
public class UserAccount extends Account{
    public UserAccount(String u,String s,String hp, String n){
        super(u,s,hp,n);
    }
    public String getAdmin(){
        return "false";
    }  
    public String submitAdminRequest(){
        return "";
    }    
}
