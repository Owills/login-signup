import java.awt.*; 
import javax.swing.*; 
import java.awt.event.*; 
import javax.swing.text.PlainDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import java.awt.dnd.*;  
import java.util.ArrayList;
public class AdminAccountPage extends JFrame implements ItemListener, ActionListener, KeyListener{
    private static JFrame f; 
    private static Choice c; 
    private static JPanel p;
    private static GridBagConstraints gc;
    private static AdminAccountPage ap;
    private static Label l; 
    private static Label l2;
    private static Label output1;
    private static JTextField t; 
    private static JTextField t2; 
    private static JButton b; 
    private static AdminAccount acc; 
    public static ArrayList<Checkbox> checkboxes;
    public static void main(AdminAccount a){
        acc = a;
        checkboxes = new ArrayList<Checkbox>();
        f = new JFrame("Welcome " + acc.getName());
        p = new JPanel();
        ap = new AdminAccountPage(); 
        
        c = new Choice();
        c.add("Sign Out"); 
        c.add("View Pending Admin Requests"); 
        c.add("Reset Password"); 
        
        c.addItemListener(ap); 
        t = new JTextField(16);
        t.addKeyListener(ap);
        t2 = new JTextField(16);
        t2.addKeyListener(ap);
        
        b = new JButton("ENTER"); 
        b.addActionListener(ap);
        
        output1 = new Label("");
        
        l = new Label(); 
        l.setText(" Press Enter to Confirm Sign Out");
        l2 = new Label(); 
        
        //layout manager
        p.setLayout(new GridBagLayout()); 
        gc = new GridBagConstraints();
        
        ap.signOut();
        f.add(p);
        
        f.show(); 
        f.setSize(1000, 500); 
        f.setResizable(false);
    } 
    public void itemStateChanged(ItemEvent e) { 
        t.setText("");
        t2.setText("");
        output1.setText("");
        if(c.getSelectedItem().equals("Sign Out")){
            l.setText(" Press Enter to Confirm Sign Out");
            ap.signOut();
        }else if(c.getSelectedItem().equals("View Pending Admin Requests")){
            ArrayList adminRequests = acc.getAdminRequests();
            while(checkboxes.size() > 0){
                checkboxes.remove(0);
            }    
            if(adminRequests.size() == 0)
                l.setText(" No Pending Admin Requests");
            else    
                l.setText(" Click Pending Admin Requests and Press Enter to Verify Them: ");
            ap.viewAdminRequests(adminRequests);
        }else if(c.getSelectedItem().equals("Reset Password")){
            l.setText(" Enter New Password: ");
            l2.setText(" Confirm New Password: ");
            ap.resetPassword();
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
    public void enter(){
       output1.setSize(1000,100);
       if(c.getSelectedItem().equals("Sign Out")){
            Login.shows();
            f.dispatchEvent(new WindowEvent(f, WindowEvent.WINDOW_CLOSING));
       }else if(c.getSelectedItem().equals("View Pending Admin Requests")){
           output1.setText("No Admin Requests Confirmed");
           for(int i = 0; i <checkboxes.size(); i++){
               if(checkboxes.get(i).getState()){
                   acc.convertToAdminAccount(checkboxes.get(i).getLabel());
                   output1.setText("Admin Requests Confirmed");
               }    
           }
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
    public void viewAdminRequests(ArrayList<String> adminRequests){
        p.removeAll();
        gc.gridx = 0; 
        gc.gridy = 0; 
        gc.ipadx = 0; 
        gc.ipady = 1; 
        p.add(c,gc);
        
        gc.gridx = 0; 
        gc.gridy = 1; 
        p.add(l,gc); 
        for(int i = 0; i <adminRequests.size(); i++){
            gc.gridx = 0; 
            gc.gridy = 2+i; 
            Checkbox checkbox = new Checkbox(adminRequests.get(i));
            p.add(checkbox,gc); 
            checkboxes.add(checkbox);
            if(i == adminRequests.size()-1){
                gc.gridx = 1; 
                gc.gridy = 2+i; 
                p.add(b,gc);
                gc.gridx = 0; 
                gc.gridy = 3+i; 
                p.add(output1,gc);
            }    
        } 
        f.add(p); 
        f.show(); 
    }  
    AdminAccountPage() { 
    } 
}