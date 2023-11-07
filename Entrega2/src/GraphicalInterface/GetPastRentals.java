package GraphicalInterface;

import java.util.ArrayList;

import java.awt.GridLayout;
import java.awt.event.*;

import javax.swing.*;

import Processing.CarRental;

public class GetPastRentals extends JPanel
{
    GetPastRentals(JFrame main, GManagerView gManager)
    {
        JLabel carLabel = new JLabel("Ingrese la placa del carro a buscar: ");
        JTextField carPlate = new JTextField("ABC123", 6);
        JButton accept = new JButton("Aceptar");
        JButton back = new JButton("Atrás");

        setLayout(new GridLayout(2, 2));
        setSize(getPreferredSize());

        accept.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                String plate = carPlate.getText();
                if (!CarRental.carExists(plate))
                {
                    new ErrorDialog("Este carro no existe en la base de datos.", main);
                    return;
                }
                ArrayList<String> result = CarRental.getPastRentals(CarRental.getCar(plate));
                // TODO: Finish the creation of a JList and its corresponding items
            }
        });

        back.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                gManager.showMain();
            }
        });

        add(carLabel);
        add(carPlate);
        add(accept);
        add(back);
    }
}
