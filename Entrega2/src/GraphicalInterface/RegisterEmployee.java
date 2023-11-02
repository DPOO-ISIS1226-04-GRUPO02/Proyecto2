package GraphicalInterface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import Processing.Users;

public class RegisterEmployee extends JPanel{

   public RegisterEmployee(String login, String password)
   {
    JPanel registerEmployeePanel = new JPanel(new GridLayout(2, 1));

    JLabel employeeLoginLabel = new JLabel("Nombre de usuario para el nuevo empleado: ");
    JTextField employeeLoginField = new JTextField();

    JLabel employeePasswordLabel= new JLabel("Contraseña para el nuevo empleado: ");
    JTextField employeePasswordField = new JTextField();

    registerEmployeePanel.add(employeeLoginLabel);
    registerEmployeePanel.add(employeeLoginField);
    registerEmployeePanel.add(employeePasswordLabel);
    registerEmployeePanel.add(employeePasswordField);

    JButton okButton = new JButton("Aceptar");
        okButton.addActionListener(new ActionListener()
        {

            @Override
            public void actionPerformed(ActionEvent e) {
               String employeeLogin = employeeLoginField.getText();
               String employeePassword = employeePasswordField.getText();

               //TODO: manejar error de usuario existente: (linea 261 view)while (usernames.contains(employeeLogin)) {System.out.println("¡Este nombre de usuario ya existe!");System.out.println("Ingrese un nuevo nombre de usuario: ") employeeLogin = scan.nextLine();

               Users.registerNewUser(employeeLogin, employeePassword, 1, login, password);
               //TODO: falta manejar de users linea 35.
            }
            

        });
        registerEmployeePanel.add(okButton);




   }
    
}
