package GraphicalInterface;

import java.awt.FlowLayout;
import java.awt.event.*;

import javax.swing.*;

import Processing.CarRental;
import Processing.Users;

public class PickUp extends JPanel 
{
    PickUp(EmployeeView employee, String loginEmployee, String passwordEmployee, JFrame main)
    {
        String workplace = Users.loadUser(loginEmployee, passwordEmployee).getWorkplace();

        JLabel clientUserLabel = new JLabel("Ingrese el nombre de usuario del cliente: ");
        JTextField clientUser = new JTextField(20);
        JButton accept = new JButton("Aceptar");
        JButton back = new JButton("Atrás");

        setLayout(new FlowLayout());
        setSize(getPreferredSize());

        accept.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                boolean correct = CarRental.confirmPickUp(clientUser.getText(), workplace);
                if (!correct) new ErrorDialog(
                    "No se ha podido confirmar la recogida. Revise que el cliente tenga una renta activa y sea este el orígen de dicha renta.",
                    main);
                else employee.showMain();
            }    
        });
        back.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                employee.showMain();
            }    
        });
        
        add(clientUserLabel);
        add(clientUser);
        add(accept);
        add(back);
        setVisible(true);
    }    
}
