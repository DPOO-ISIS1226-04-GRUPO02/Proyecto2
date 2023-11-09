package GraphicalInterface;

import java.awt.GridLayout;

import javax.swing.*;

import Processing.Users;
import Processing.CarRental;

public class RegisterManager extends JPanel
{
    RegisterManager(GManagerView gManager, JFrame main)
    {
        setLayout(new GridLayout(4, 2));

        String[] stores = new String[CarRental.getStores().size()];
        int i = 0;
        for (String store: CarRental.getStores()) stores[i++] = store;

        JLabel usernameLabel = new JLabel("Ingrese el nombre de usuario para el nuevo gerente: ");
        JTextField usernameText = new JTextField(20);
        JLabel passwordLabel = new JLabel("Ingrese la contraseña para el nuevo gerente: ");
        JTextField passwordText = new JTextField(20);
        JLabel storeLabel = new JLabel("Seleccione la tienda que va a manejar este gerente: ");
        JComboBox<String> storeOptions = new JComboBox<String>(stores);
        JButton accept = new JButton("Aceptar");
        JButton back = new JButton("Atrás");

        accept.addActionListener(e ->
        {
            String username = usernameText.getText();
            String password = passwordText.getText();
            String store = (String) storeOptions.getSelectedItem();
            Object result = Users.registerNewUser(username, password, 2, store);
            if (result.equals(null)) 
            {
                new ErrorDialog("Este nombre de usuario ya fue usado.", main);
                return;
            }
            JOptionPane.showMessageDialog(main, "Gerente creado correctamente!", "Information", JOptionPane.PLAIN_MESSAGE);
            gManager.showMain();
        });
        back.addActionListener(e ->
        {
            gManager.showMain();
        });

        add(usernameLabel);
        add(usernameText);
        add(passwordLabel);
        add(passwordText);
        add(storeLabel);
        add(storeOptions);
        add(accept);
        add(back);
    }
}
