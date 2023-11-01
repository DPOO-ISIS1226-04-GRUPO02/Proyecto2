package GraphicalInterface;

import javax.swing.*;
import java.awt.*;

public class UserSelection extends JFrame {

    // Labels and Buttons
    JLabel usernameLabel = new JLabel("Usuario: ");
    JLabel passwordLabel = new JLabel("Contraseña: ");
    JTextField username = new JTextField(30);
    JTextField password = new JTextField(30);
    JButton login = new JButton("Login");
    JButton register = new JButton("Register");

    UserSelection() {

        setTitle("User Selection");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(getPreferredSize());
        setLayout(new GridLayout(1, 4));
        setResizable(false);
        setLocationRelativeTo(null);

        add(usernameLabel);
        add(username);


    }
    
}
