import java.util.*;
import java.io.*;
public class MasterFileManager{
    private Hashtable<String,Boolean> strA;
    private String filename;
    public MasterFileManager(String filename){
        this.filename = filename;
        strA = new Hashtable<String,Boolean>();
        try{
            writeAL();
        }catch(Exception e){
            System.out.println("l");
        }    
    }
    public void writeAL() throws Exception{
        
    }  
    public boolean checkWord(String str){
        if(strA.get(str) != null)
            return true;
        return false;
    }    
}