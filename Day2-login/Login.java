import java.io.*;
import java.util.ArrayList;
import java.awt.*; 
import javax.swing.*; 
import java.awt.event.*; 
import javax.swing.text.PlainDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import java.awt.dnd.*;  

class Login extends JFrame implements ItemListener, ActionListener, KeyListener{ 
    private static Choice c; 
    private static JFrame f; 
    private static GridBagConstraints gc;
    private static JPanel p;
    private static Label l; 
    private static Label l2; 
    private static Label lp; 
    private static Label l3; 
    private static JTextField t; 
    private static JTextField t2; 
    private static JTextField t3; 
    private static Label output1;
    private static JButton b; 
    private static String file;
    private static MasterFileManager MFM;
    private static Checkbox checkbox;
    //private static HASH h;
    Login() { 
    } 
    public static void main(String args[]) { 
        //set up interface
        f = new JFrame("Marker Bank"); 
        //main pannel
        Login pl = new Login(); 
        p = new JPanel(); 
        // create wordlist object
        MFM = new MasterFileManager("MasterFile.txt");
        //text box
        t = new JTextField(16);
        t.addKeyListener(pl);
        t2 = new JTextField(16);
        t2.addKeyListener(pl);
        t3 = new JTextField(16);
        t3.addKeyListener(pl);
        
        //admin checkbox
        checkbox = new Checkbox(" Admin");  
        
        //layout manager
        p.setLayout(new GridBagLayout()); 
        gc = new GridBagConstraints(); 
        
        //submit button
        b = new JButton("ENTER"); 
        b.addActionListener(pl);
        
        //top choice box
        c = new Choice(); 
        c.add("Sign Up"); 
        c.add("Login"); 
        c.addItemListener(pl); 
        //instruction labels
        l = new Label(); 
        l.setText(" Create Username: ");
        l2 = new Label(); 
        l2.setText(" Create Password: ");
        lp = new Label(); 
        lp.setText( " PassWord Must Be At least 7 Characters and Contain One Capital and Non Letter Character");
        l3 = new Label(); 
        l3.setText(" Confrim Password: ");
        //output display
        output1 = new Label("");
        
        //horizontal alignment
        gc.anchor = GridBagConstraints.WEST;
        gc.fill = GridBagConstraints.VERTICAL;
        
        gc.gridwidth = 1;
        gc.gridheight = 1;
        
        signupSetup();
        f.add(p); 

        f.show(); 
        f.setSize(1000, 500); 
        f.setResizable(false);
    } 
    // change selected item
    public void itemStateChanged(ItemEvent e) { 
        t.setText("");
        t2.setText("");
        if(c.getSelectedItem().equals("Login")){
            l.setText(" Username: ");
            l2.setText(" Password: ");
            output1.setText("");
            loginSetup();
            f.add(p); 
            f.show(); 
        }else {
            l.setText(" Create Username: ");
            l2.setText(" Create Password: ");
            output1.setText("");
            signupSetup();
            f.add(p); 
            f.show(); 
        }
    } 
    //submit button on click
    public void actionPerformed(ActionEvent e) { 
        enter();
    } 
    //submit button with enter key
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode()==KeyEvent.VK_ENTER){
             enter();
        }  
    }
    //statisfying abstract class, but do nothing
    public void keyReleased(KeyEvent e) {}
    public void keyTyped(KeyEvent e) {}
    //submit logic
    public static void enter(){
       if(c.getSelectedItem().equals("Login")){
           if(MFM.login(t.getText(),t2.getText()))
                System.out.print("login good");
       }else{
           boolean admin = false;
           if(checkbox.getState())
                admin = true;
           if(!t2.getText().equals(t3.getText())){
               output1.setText("Passwords Do Not Match");
           }else if(t2.getText().length() < 7){
               output1.setText("Password Must Be At Least Seven Characters");  
           }else if(!hasNonLetters(t2.getText())){    
                output1.setText("Password Must Have At Least One Non Letter Character");
           }else if(!hasCapitalLetters(t2.getText())){    
                output1.setText("Password Must Have At Least One Capital Letters");     
           }else if(MFM.newAccount(t.getText(),t2.getText(), admin)){
               output1.setText("Account Created");
           } else{
               output1.setText("The Usename '" + t.getText() +"' is Taken");
           }    
           output1.setSize(1000,100);
       }     
    }    
    public static boolean hasNonLetters(String str){
        for(int i = 0; i<str.length(); i++){
            Character c = str.charAt(i);
            if (!Character.isLetter(c)){
                return true;
            }
        }    
        return false;
    }   
    public static boolean hasCapitalLetters(String str){
        for(int i = 0; i<str.length(); i++){
            Character c = str.charAt(i);
            if(Character.isUpperCase(c)){
                return true;
            }
        }    
        return false;
    }   
    public static void loginSetup(){
        p.removeAll();
        gc.gridx = 0; 
        gc.gridy = 0; 
        gc.ipadx = 0; 
        gc.ipady = 1; 
        p.add(c,gc); 
        
        gc.gridx = 0; 
        gc.gridy = 1; 
        p.add(l,gc); 
        
        gc.gridx = 0; 
        gc.gridy = 2; 
        p.add(t,gc);
        
        gc.gridx = 0; 
        gc.gridy = 3; 
        p.add(l2,gc);
        
        gc.gridx = 0; 
        gc.gridy = 4; 
        p.add(t2,gc);
        
        gc.gridx = 1; 
        gc.gridy = 4; 
        p.add(b,gc);
        
        gc.gridx = 0; 
        gc.gridy = 5; 
        p.add(output1,gc);
    } 
    public static void signupSetup(){
        p.removeAll();
        gc.gridx = 0; 
        gc.gridy = 0; 
        gc.ipadx = 0; 
        gc.ipady = 1; 
        p.add(c,gc); 
        
        gc.gridx = 0; 
        gc.gridy = 1; 
        p.add(l,gc); 
        
        gc.gridx = 0; 
        gc.gridy = 2; 
        p.add(t,gc);
        
        gc.gridx = 0; 
        gc.gridy = 3; 
        p.add(l2,gc);
        
        gc.gridx = 0; 
        gc.gridy = 4; 
        p.add(t2,gc);
        
        gc.gridx = 0; 
        gc.gridy = 5; 
        p.add(l3,gc); 
        
        gc.gridx = 0; 
        gc.gridy = 6; 
        p.add(t3,gc);
        
        gc.gridx = 0; 
        gc.gridy = 7; 
        p.add(checkbox,gc);
        
        gc.gridx = 1; 
        gc.gridy = 7; 
        p.add(b,gc);
        
        gc.gridx = 0; 
        gc.gridy = 8; 
        p.add(output1,gc);
    } 
} 
