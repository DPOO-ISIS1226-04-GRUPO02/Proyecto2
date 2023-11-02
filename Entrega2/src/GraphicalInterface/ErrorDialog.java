package GraphicalInterface;

import javax.swing.JOptionPane;
import javax.swing.JFrame;

public class ErrorDialog extends JOptionPane {
    
    ErrorDialog(String message, JFrame parent) {
        
        showMessageDialog(parent, message);

    }

}
