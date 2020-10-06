
public abstract class Account{
    private String username;
    private String salt;
    private String hashedPassword;
    private String name;
    public Account(String u,String s,String hp, String n){
        username = u;
        salt = s;
        hashedPassword = hp;
        name = n;
    }
    public String getUsername(){
        return username;
    }
    public String getSalt(){
        return salt;
    } 
    public String getHashedPassword(){
        return hashedPassword;
    } 
    public void setSalt(String s){
        salt = s;
    }
    public void setName(String n){
        name = n;
    }
        public String getName(){
        return name;
    }
    public void setHashedPassword(String hp){
        hashedPassword = hp;
    } 
    abstract String getAdmin();
}
