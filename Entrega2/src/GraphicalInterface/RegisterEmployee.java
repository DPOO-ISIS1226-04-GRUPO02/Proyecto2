package GraphicalInterface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import Processing.Users;

public class RegisterEmployee extends JPanel{

   public RegisterEmployee(String login, String password, JFrame main, LManagerView lManager)
   {
      setLayout(new GridLayout(3, 2));

      JLabel employeeLoginLabel = new JLabel("Nombre de usuario para el nuevo empleado: ");
      JTextField employeeLoginField = new JTextField();

      JLabel employeePasswordLabel= new JLabel("Contraseña para el nuevo empleado: ");
      JTextField employeePasswordField = new JTextField();

      add(employeeLoginLabel);
      add(employeeLoginField);
      add(employeePasswordLabel);
      add(employeePasswordField);

      JButton okButton = new JButton("Aceptar");
      okButton.addActionListener(new ActionListener()
      {
         public void actionPerformed(ActionEvent e)
         {
            String employeeLogin = employeeLoginField.getText();
            String employeePassword = employeePasswordField.getText();
            if (Users.registerNewUser(employeeLogin, employeePassword, 1, login, password).equals(null))
            {
               new ErrorDialog("Este nombre de usuario ya existe. Escoga otro.", main);
            }
         }
      });
      add(okButton);

      JButton back = new JButton("Atrás");
      back.addActionListener(new ActionListener()
      {
         public void actionPerformed(ActionEvent e)
         {
            lManager.showMain();
         }
      });
      add(back);
   }
    
}
