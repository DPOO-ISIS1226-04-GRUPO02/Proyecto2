package GraphicalInterface;

import javax.swing.*;

import java.awt.*;

import Processing.CarRental;

public class SendCar extends JPanel
{
    SendCar(ChangeCar changeCar, JFrame main)
    {
        setLayout(new GridLayout(4, 2));

        String[] stores = new String[CarRental.getStores().size()];
        int i = 0;
        for (String store: CarRental.getStores()) stores[i++] = store;

        JLabel plateLabel = new JLabel("Ingrese la placa del carro a mandar a otra sedee: ");
        JTextField plateText = new JTextField("ABC123", 6);
        JLabel destinationLabel = new JLabel("Seleccione la tienda a donde desea mandar el carro: ");
        JComboBox<String> destinationOptions = new JComboBox<String>(stores);
        JLabel daysLabel = new JLabel("Ingrese el número de días que se demora el traslado: ");
        JTextField daysText = new JTextField("Numbers only", 10);
        JButton accept = new JButton("Aceptar");
        JButton back = new JButton("Atrás");

        accept.addActionListener(e ->
        {
            String plate = plateText.getText();
            if (!CarRental.carExists(plate)) 
            {
                new ErrorDialog("No se econtró el carro!", main);
                return;
            }
            String destination = (String) destinationOptions.getSelectedItem();
            int days = 0;
            try
            {
                days = Integer.parseInt(daysText.getText());
            }
            catch (NumberFormatException ex)
            {
                new ErrorDialog("No se ingresó un numero de días correcto!" + ex.getMessage(), main);
                return;
            }
            CarRental.reserveCar(plate, destination, days);
            changeCar.showMain();
        });
        back.addActionListener(e ->
        {
            changeCar.showMain();
        });

        add(plateLabel);
        add(plateText);
        add(destinationLabel);
        add(destinationOptions);
        add(daysLabel);
        add(daysText);
        add(accept);
        add(back);
    }
}
