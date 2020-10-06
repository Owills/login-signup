import java.awt.*; 
import javax.swing.*; 
import java.awt.event.*; 
import javax.swing.text.PlainDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import java.awt.dnd.*;  
public class AdminAccountPage{
    private static JFrame f; 
    public static void main(AdminAccount acc){
        f = new JFrame("Welcome " + acc.getName());
        AdminAccountPage ap = new AdminAccountPage(); 
        f.show(); 
        f.setSize(1000, 500); 
        f.setResizable(false);
    } 
    AdminAccountPage() { 
    } 
}