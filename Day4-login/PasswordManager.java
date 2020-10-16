public class PasswordManager{
    public PasswordManager(){}
    public boolean hasNonLetters(String str){
        for(int i = 0; i<str.length(); i++){
            Character c = str.charAt(i);
            if (!Character.isLetter(c)){
                return true;
            }
        }    
        return false;
    }   
    public boolean hasCapitalLetters(String str){
        for(int i = 0; i<str.length(); i++){
            Character c = str.charAt(i);
            if(Character.isUpperCase(c)){
                return true;
            }
        }    
        return false;
    }
    public boolean passwordsMatch(String str, String str2){
        if(str.equals(str2))
            return true;
        return false;
    }   
    public boolean isValidLength(String str){
        if(str.length() >= 7)
            return true;
        return false;    
    }    
}
