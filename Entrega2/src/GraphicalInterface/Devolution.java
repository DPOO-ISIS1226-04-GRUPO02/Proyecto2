package GraphicalInterface;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import Processing.CarRental;

public class Devolution extends JPanel
{
    Devolution(EmployeeView employee, String loginEmployee, String passwordEmployee, JFrame main)
    {
        setLayout(new GridLayout(3, 2));
        setSize(getPreferredSize());

        JLabel clientUserLabel = new JLabel("Ingrese el nombre de usuario del cliente: ");
        JTextField clientUser = new JTextField(20);
        JLabel daysLabel = new JLabel("Ingrese la cantidad de días en los que el carro no estará disponible: ");
        JTextField days = new JTextField(5);
        JButton accept = new JButton("Aceptar");
        JButton back = new JButton("Atrás");

        accept.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                int daysInt = 0;
                try
                {
                    daysInt = Integer.parseInt(days.getText());
                }
                catch (NumberFormatException ex)
                {
                    new ErrorDialog("El número de días ingresado no es un entero.", main);
                    return;
                }
                String correct = CarRental.confirmReturn(clientUser.getText(), daysInt, loginEmployee, passwordEmployee);
                if (!correct.equals(null)) new ErrorDialog(correct, main);
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
        add(daysLabel);
        add(days);
        add(back);
        add(accept);
    }
}
