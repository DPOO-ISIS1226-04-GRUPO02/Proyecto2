package GraphicalInterface;

import javax.swing.*;

import Processing.CarRental;

public class ToggleCarStatus extends JPanel
{
    ToggleCarStatus(ChangeCar changeCar, JFrame main)
    {
        String[] stati = {"Disponible", "Inhabilitado"};

        JLabel plateLabel = new JLabel("Ingrese la placa del carro a modificar: ");
        JTextField plateText = new JTextField("ABC123", 6);
        JLabel statusLabel = new JLabel("Seleccione el estado al que desea cambiar el carro: ");
        JComboBox<String> statusOptions = new JComboBox<String>(stati);
        JButton accept = new JButton("Aceptar");
        JButton back = new JButton("Atrás");

        accept.addActionListener(e ->
        {
            String plate = plateText.getText();
            if (!CarRental.carExists(plate))
            {
                new ErrorDialog("Este carro no fue encontrado en el sistema!", main);
                return;
            }
            String changedStatus = (String) statusOptions.getSelectedItem();
            byte status = -1;
            if (changedStatus.equals("Disponible")) status = 0;
            else status = 3;
            boolean result = CarRental.changeVehicleStatus(plate, status);
            if (!result) 
            {
                new ErrorDialog("No se puede cambiar el estado: carro reservado o en uso!", main);
                return;
            }
            changeCar.showMain();
        });
        back.addActionListener(e ->
        {
            changeCar.showMain();
        });

        add(plateLabel);
        add(plateText);
        add(statusLabel);
        add(statusOptions);
        add(accept);
        add(back);
    }
}
