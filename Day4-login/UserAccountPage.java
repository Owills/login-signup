import java.awt.*; 
import javax.swing.*; 
import java.awt.event.*; 
import javax.swing.text.PlainDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import java.awt.dnd.*;  
public class UserAccountPage extends JFrame implements ItemListener, ActionListener, KeyListener{
    private static JFrame f; 
    private static Choice c; 
    private static JPanel p;
    private static Label output1;
    private static UserAccountPage ap;
    private static GridBagConstraints gc;
    private static Label l; 
    private static Label l2;
    private static JTextField t; 
    private static JTextField t2;
    private static JButton b; 
    private static UserAccount acc; 
    public static void main(UserAccount a){
        acc = a;
        f = new JFrame("Welcome " + acc.getName());
        p = new JPanel();
        
        ap = new UserAccountPage(); 
        
        c = new Choice(); 
        c.add("Play Conway's Game Of Life");
        c.add("Submit Admin Request"); 
        c.add("Reset Password"); 
        c.add("Sign Out"); 
        c.addItemListener(ap); 
        
        t = new JTextField(16);
        t.addKeyListener(ap);
        t2 = new JTextField(16);
        t2.addKeyListener(ap);
        
        b = new JButton("ENTER"); 
        b.addActionListener(ap);
        
        output1 = new Label("");
        
        l = new Label(); 
        l.setText(" Press Enter to Play");
        l2 = new Label(); 
        
        p.setLayout(new GridBagLayout()); 
        gc = new GridBagConstraints();
        
        ap.CGOL();
        f.add(p);
        
        f.show(); 
        f.setSize(1000, 500); 
        f.setResizable(false);
    } 
    UserAccountPage() { 
    } 
    public void itemStateChanged(ItemEvent e) { 
        t.setText("");
        t2.setText("");
        output1.setText("");
        if(c.getSelectedItem().equals("Sign Out")){
            l.setText(" Press Enter to Confirm Sign Out");
            ap.signOut();
        }else if(c.getSelectedItem().equals("Play Conway's Game Of Life")){
            l.setText(" Press Enter to Play");
            ap.CGOL();
        }else if(c.getSelectedItem().equals("Submit Admin Request")){
            l.setText(" Submit Admin Request: ");
            ap.submitAdminRequest();
        }else if(c.getSelectedItem().equals("Reset Password")){
            l.setText(" Enter New Password: ");
            l2.setText(" Confirm New Password: ");
            ap.resetPassword();
        }
    } 
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
    
    public void enter(){
       output1.setSize(1000,100);
       if(c.getSelectedItem().equals("Sign Out")){
            Login.shows();
            f.dispatchEvent(new WindowEvent(f, WindowEvent.WINDOW_CLOSING));
       } else if(c.getSelectedItem().equals("Play Conway's Game Of Life")){
            GameOfLife.main();
       }else if(c.getSelectedItem().equals("Submit Admin Request")){
           output1.setText(acc.submitAdminRequest());
           Login.writeF();
       }else if(c.getSelectedItem().equals("Reset Password")){ 
           output1.setText(acc.resetPassword(t.getText(),t2.getText()));
           Login.writeF();
       }   
    } 
    public void signOut(){
        p.removeAll();
        gc.gridx = 0; 
        gc.gridy = 0; 
        gc.ipadx = 0; 
        gc.ipady = 1; 
        p.add(c,gc);
        
        gc.gridx = 0; 
        gc.gridy = 1; 
        p.add(l,gc);
        
        gc.gridx = 1; 
        gc.gridy = 1; 
        p.add(b,gc);
        
        f.add(p); 
        f.show(); 
    }   
    public void resetPassword(){
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
        
        f.add(p); 
        f.show(); 
    } 
    public void submitAdminRequest(){
        p.removeAll();
        gc.gridx = 0; 
        gc.gridy = 0; 
        gc.ipadx = 0; 
        gc.ipady = 1; 
        p.add(c,gc);
        
        gc.gridx = 0; 
        gc.gridy = 1; 
        p.add(l,gc); 
        
        gc.gridx = 1; 
        gc.gridy = 1; 
        p.add(b,gc);
        
        gc.gridx = 0; 
        gc.gridy = 2; 
        p.add(output1,gc);
        
        f.add(p); 
        f.show(); 
    } 
    public void CGOL(){
        p.removeAll();
        gc.gridx = 0; 
        gc.gridy = 0; 
        gc.ipadx = 0; 
        gc.ipady = 1; 
        p.add(c,gc);
        
        gc.gridx = 0; 
        gc.gridy = 1; 
        p.add(l,gc); 
        
        gc.gridx = 1; 
        gc.gridy = 1; 
        p.add(b,gc);
        
        gc.gridx = 0; 
        gc.gridy = 2; 
        p.add(output1,gc);
        
        f.add(p); 
        f.show(); 
    }  
}
