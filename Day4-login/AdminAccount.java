import java.util.*;
public class AdminAccount extends Account{
    Hashtable<String,Account> strA;
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
    public String getAdminRequest(){
        return "null";
    }
    public ArrayList<String> getAdminRequests(){
        ArrayList adminRequests = new ArrayList<String>();
        Enumeration<String> keys = strA.keys();
        while(keys.hasMoreElements()){
           String key = keys.nextElement();
           if(strA.get(key).getAdminRequest().equals("true"))
                adminRequests.add(key);
        }    
        return adminRequests;
    } 
    public void convertToAdminAccount(String user){
        if(strA.get(user) == null)
            return;
        Account acc = new AdminAccount(user, strA.get(user).getSalt(),
                                    strA.get(user).getHashedPassword(),
                                    strA.get(user).getName(), strA);
        strA.remove(user);                             
        strA.put(user,acc);
    }    
}
