import java.util.*;
import java.io.*;
import java.math.BigInteger;  
import java.nio.charset.StandardCharsets; 
import java.security.MessageDigest;  
import java.security.NoSuchAlgorithmException; 
import java.security.SecureRandom;
public class MasterFileManager{
    private Hashtable<String,Account> strA;
    private String filename;
    private static final Random RANDOM = new SecureRandom();
    public MasterFileManager(String filename){
        this.filename = filename;
        strA = new Hashtable<String,Account>();
        
        try{
            readFile();
        }catch(Exception e){
            System.out.println("Error Getting File Info");
        }    
    }
    public void readFile() throws Exception{
        File newFile = new File(filename);
        FileReader reading = new FileReader(newFile);
        BufferedReader br = new BufferedReader(reading);
        String str; 
        int i = 0;
        String user = "";
        String name = "";
        String salt = "";
        String hashedPassword = "";
        String adminRequest = "";
        while ((str = br.readLine()) != null) {
            if(i == 0){
                user = str;
            }else if(str.equals("")){
                i = -1;
            }else if (i == 1){
                name = str;
            }else if (i == 2){
                salt = str;
            } else if (i == 3){
                hashedPassword = str;
            }else if (i == 4){
                adminRequest = str;
            }else if (i == 5){
                if(str.equals("true"))
                    strA.put(user,new AdminAccount(user,salt,hashedPassword,name, strA));
                else  
                    strA.put(user,new UserAccount(user,salt,hashedPassword,name, adminRequest));
            } 
            i++; 
        }   
        // Enumeration<String> keys = strA.keys();
        // while(keys.hasMoreElements()){
            // String key = keys.nextElement();
            // System.out.println(key);
            // System.out.println(strA.get(key).getName());
            // System.out.println(strA.get(key).getSalt());
            // System.out.println(strA.get(key).getHashedPassword());
            // System.out.println(strA.get(key).getAdmin());
            // System.out.println();
        // }    
    } 
    public void writeFile() throws Exception{
        File copyOfFile =  new File(filename);
        FileWriter writing = new FileWriter(copyOfFile);
        BufferedWriter bw = new BufferedWriter(writing);
        Enumeration<String> keys = strA.keys();
        while(keys.hasMoreElements()){
            String key = keys.nextElement();
            bw.write(key);
            bw.newLine();
            bw.write(strA.get(key).getName());
            bw.newLine();
            bw.write(strA.get(key).getSalt());
            bw.newLine();
            bw.write(strA.get(key).getHashedPassword());
            bw.newLine();
            bw.write(strA.get(key).getAdminRequest());
            bw.newLine();
            bw.write(strA.get(key).getAdmin());
            if(keys.hasMoreElements()){
                bw.newLine();
                bw.newLine();
            }    
        }
        bw.close();
    }
    public void appendFile(String user)throws Exception{
        File copyOfFile =  new File(filename);
        FileWriter writing = new FileWriter(copyOfFile, true);
        BufferedWriter bw = new BufferedWriter(writing);
        bw.newLine();
        bw.newLine();
        bw.write(user);
        bw.newLine();
        bw.write(strA.get(user).getName());
        bw.newLine();
        bw.write(strA.get(user).getSalt());
        bw.newLine();
        bw.write(strA.get(user).getHashedPassword());
        bw.newLine();
        bw.write(strA.get(user).getAdminRequest());
        bw.newLine();
        bw.write(strA.get(user).getAdmin());
        bw.close();
    }    
    public String newAccount(String user, String name, String pass, String confirm, boolean admin){
        PasswordManager pm = new PasswordManager();
        //acount creation checks  
        if(strA.get(user) != null)
            return ("The Username '" + user + "' is taken");
        if(!pm.passwordsMatch(pass,confirm))
            return "Passwords Do Not Match";
        if(!pm.isValidLength(pass))
            return "Passwords Must Be At Least Seven Characters";    
        if(!pm.hasCapitalLetters(pass))
            return "Passwords Must Have At Least One Capital Letter";
        if(!pm.hasNonLetters(pass))
            return "Passwords Must Have At Least One Non Letter Character";   
        //acount can be created     
            String salt = getSalt();
            try { 
                HASH h = new HASH();
                pass = h.toHexString(h.getSHA(pass + salt));
            }catch (NoSuchAlgorithmException e) {  
                return ("Exception thrown for incorrect algorithm: " + e);  
            }  
            if(admin)
                strA.put(user, new AdminAccount(user,salt,pass,name, strA));
            else 
                strA.put(user, new UserAccount(user,salt,pass, name, "false")); 
            try { 
                appendFile(user);
            } catch( Exception e){
                return "An Error Occured Writting the File";
            }    
        return "Acount Created Successfully";
    } 
    public String login(String user, String pass){
       if(strA.get(user) != null){
          String salt = strA.get(user).getSalt();
          try { 
                HASH h = new HASH();
                pass = h.toHexString(h.getSHA(pass + salt));
                if(pass.equals(strA.get(user).getHashedPassword())){
                    if(strA.get(user).getAdmin().equals("true"))
                        AdminAccountPage.main((AdminAccount)strA.get(user));   
                    else
                        UserAccountPage.main((UserAccount)strA.get(user));   
                    Login.hides();
                    return "Login Successful";
                }    
            }catch (NoSuchAlgorithmException e) {  
                return "Login Failed";
          }  
       }    
       return "Username or Password was Inccorect";     
    } 
    public static String getSalt() {
        byte[] salt = new byte[16];
        RANDOM.nextBytes(salt);
        return Base64.getEncoder().encodeToString(salt);
    }   
    
}