import java.util.*;
public class AdminAccount extends Account{
    Hashtable strA;
    public AdminAccount(String u,String s,String hp,String n, Hashtable temp){
        super(u,s,hp,n);
        strA = temp;
    }
    public String getAdmin(){
        return "true";
    }    
    public String reviewAdminRequests(){
        return "";
    }    
}
