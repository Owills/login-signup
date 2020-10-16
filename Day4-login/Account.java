import java.security.NoSuchAlgorithmException; 
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
    public String resetPassword(String pass, String confirm){
        PasswordManager pm = new PasswordManager();
        if(!pm.passwordsMatch(pass,confirm))
            return "Passwords Do Not Match";
        if(!pm.isValidLength(pass))
            return "Passwords Must Be At Least Seven Characters";     
        if(!pm.hasCapitalLetters(pass))
            return "Passwords Must Have At Least One Capital Letter";
        if(!pm.hasNonLetters(pass))
            return "Passwords Must Have At Least One Non Letter Character";
        try { 
                HASH h = new HASH();
                pass = h.toHexString(h.getSHA(pass + salt));
            }catch (NoSuchAlgorithmException e) {  
                return ("Exception thrown for incorrect algorithm: " + e);  
            }  
        this.setHashedPassword(pass);
        return "Passwords Reset Seuccessfully";   
    }    
    abstract String getAdmin();
    abstract String getAdminRequest();
}
