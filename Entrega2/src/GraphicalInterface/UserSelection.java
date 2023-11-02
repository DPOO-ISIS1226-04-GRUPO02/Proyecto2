package GraphicalInterface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import Processing.Users;
import Model.User;

public class UserSelection extends JFrame {

    // Labels and Buttons
    JLabel usernameLabel = new JLabel("Usuario: ");
    JLabel passwordLabel = new JLabel("Contraseña: ");
    JTextField username = new JTextField(30);
    JTextField password = new JTextField(30);
    JButton login = new JButton("Login");
    JButton register = new JButton("Register");

    // Frames and main elements
    JFrame userSelection = this;
    MainInterface mi = new MainInterface();

    UserSelection() {

        setTitle("User Selection");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(getPreferredSize());
        setLayout(new GridLayout(2, 1));
        setResizable(false);
        setLocationRelativeTo(null);

        JPanel fields = new JPanel();
        fields.setLayout(new GridLayout(1, 4));
        fields.setSize(fields.getPreferredSize());

        fields.add(usernameLabel);
        fields.add(username);
        fields.add(passwordLabel);
        fields.add(password);

        add(fields);

        JPanel buttons = new JPanel();
        buttons.setLayout(new GridLayout(1, 2));
        buttons.setSize(fields.getPreferredSize());

        buttons.add(login);
        buttons.add(register);

        add(buttons);

        setVisible(true);

        login.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    int access = Users.loadUser(username.getText(), password.getText()).getAccess();
                    setVisible(false);
                    mi.loadView(access, username.getText(), password.getText());
                } catch (Exception ex) {
                    new ErrorDialog("Este usuario y esta contraseña no coinciden.", userSelection);
                }
            }
        });

        register.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                User registered = Users.registerNewUser(username.getText(), password.getText(), 0, null);
                if (registered.equals(null)) 
                    new ErrorDialog("No se pudo crear el usuario! Este nombre de usuario ya existe.", 
                                    userSelection);
                else mi.loadView(0, username.getText(), password.getText());
            }
        });

        mi.setVisible(false);

    }
    
}
