import java.util.*;
import java.io.*;
import java.math.BigInteger;  
import java.nio.charset.StandardCharsets; 
import java.security.MessageDigest;  
import java.security.NoSuchAlgorithmException; 
import java.security.SecureRandom;
public class MasterFileManager{
    private Hashtable<String,ArrayList> strA;
    private String filename;
    private static final Random RANDOM = new SecureRandom();
    public MasterFileManager(String filename){
        this.filename = filename;
        strA = new Hashtable<String,ArrayList>();
        
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
        boolean firstLine = true;
        String user = "";
        while ((str = br.readLine()) != null) {
            if(firstLine){
                strA.put(str,new ArrayList<String>());
                firstLine = false;
                user = str;
            } else if (str.equals(" ")){
                firstLine = false;
            }else{
                strA.get(user).add(str);
            }    
        }    
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
            for(int i = 0; i <strA.get(key).size(); i ++){
                bw.write((String)strA.get(key).get(i));
                if(i < strA.get(key).size()-1 || keys.hasMoreElements())
                    bw.newLine();
            }  
            if(keys.hasMoreElements())
                bw.newLine();
        }
        bw.close();
    }
    public boolean newAccount(String user, String pass, boolean admin){
        if(strA.get(user) == null){
            String salt = getSalt();
            try { 
                pass = toHexString(getSHA(pass + salt));
            }catch (NoSuchAlgorithmException e) {  
                System.out.println("Exception thrown for incorrect algorithm: " + e);  
            }  
            strA.put(user,new ArrayList<String>());
            strA.get(user).add(salt);
            strA.get(user).add(pass);
            
            if(admin)
                strA.get(user).add("true");
            else  
                strA.get(user).add("false");
            try { 
                writeFile();
            } catch( Exception e){
                System.out.println("shit");
            }    
            return true;
        }    
        return false;    
    } 
    public boolean login(String user, String pass){
       if(strA.get(user) != null){
            if(strA.get(user).get(0).equals(pass))
                return true;
       }    
       return false;     
    } 
     public static String getSalt() {
        byte[] salt = new byte[16];
        RANDOM.nextBytes(salt);
        return Base64.getEncoder().encodeToString(salt);
    }   
    public byte[] getSHA(String input) throws NoSuchAlgorithmException {  
        MessageDigest md = MessageDigest.getInstance("SHA-256");  
        return md.digest(input.getBytes(StandardCharsets.UTF_8));  
    } 
    public String toHexString(byte[] hash) { 
        BigInteger number = new BigInteger(1, hash);  
        StringBuilder hexString = new StringBuilder(number.toString(16));  
        while (hexString.length() < 32)  {  
            hexString.insert(0, '0');  
        }  
        return hexString.toString();  
    } 
}