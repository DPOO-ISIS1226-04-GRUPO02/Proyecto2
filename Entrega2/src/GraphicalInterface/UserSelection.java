package GraphicalInterface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.text.ParseException;

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
        setSize(1000, 500);
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

        login.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    System.out.println("Login button clicked");
                    int access = Users.loadUser(username.getText(), password.getText()).getAccess();
                    setVisible(false);
                    mi.loadView(access, username.getText(), password.getText());
                    mi.setVisible(true);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    new ErrorDialog("Este usuario y esta contraseña no coinciden.", userSelection);
                }
            }
        });

        register.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    System.out.println("Register button clicked");
                    User registered = Users.registerNewUser(username.getText(), password.getText(), 0, null);
                    if (registered.equals(null)) {
                        new ErrorDialog("No se pudo crear el usuario! Este nombre de usuario ya existe.",
                                userSelection);
                        return;
                    }
                    setVisible(false);
                    mi.loadView(0, username.getText(), password.getText());
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
        
        System.out.println("UserSelection constructor completed");
    }

    public static void main(String[] args) throws IOException, ParseException {
        UserSelection userSelection = new UserSelection();
        try {
            Users.loadUsers();
        } catch (RuntimeException ex) {
            new ErrorDialog("Error al inicializar los usuarios: " + ex.getMessage(), userSelection);
        }
        // Añade esta línea para mostrar el JFrame después de la configuración
        userSelection.setVisible(true);
        System.out.println("main method completed");
    }
}



