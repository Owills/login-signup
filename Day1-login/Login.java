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
    private static Label l3; 
    private static JTextField t; 
    private static JTextField t2; 
    private static JTextField t3; 
    private static Label output1;
    private static JButton b; 
    private static String file;
    private static MasterFileManager MFM;
    private static HASH h;
    Login() { 
    } 
    public static void main(String args[]) { 
        //set up interface
        f = new JFrame("Marker Bank"); 
        file = "";
        //main pannel
        Login pl = new Login(); 
        p = new JPanel(); 
        // create wordlist object

        //text box
        t = new JTextField(16);
        t.addKeyListener(pl);
        t2 = new JTextField(16);
        t2.addKeyListener(pl);
        t3 = new JTextField(16);
        t3.addKeyListener(pl);
        //set character limit
        //t.setDocument(new CharLimit(50));
        
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
            l3.setText(" Confrim Password: ");
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
           System.out.println("LOGIN");
       }else{
           System.out.println("SIGNUP");
       }     
    }    
    public static boolean endsInSpecialCharacter(String str){
        Character c = str.charAt(str.length()-1);
        if (Character.isLetter(c)){
            return false;
        }
        return true;
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
        
        gc.gridx = 1; 
        gc.gridy = 6; 
        p.add(b,gc);
        
        gc.gridx = 0; 
        gc.gridy = 7; 
        p.add(output1,gc);
    } 
} 
