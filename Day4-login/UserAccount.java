
public class UserAccount extends Account{
    private String adminRequest;
    public UserAccount(String u,String s,String hp, String n, String ar){
        super(u,s,hp,n);
        adminRequest = ar;
    }
    public String getAdmin(){
        return "false";
    }  
    public String getAdminRequest(){
        return adminRequest;
    }
    public String submitAdminRequest(){
         if(adminRequest.equals("true"))
            return "Admin Request Already Submitted";
         adminRequest = "true";
         return "Admin Request Submitted";
    }
}
