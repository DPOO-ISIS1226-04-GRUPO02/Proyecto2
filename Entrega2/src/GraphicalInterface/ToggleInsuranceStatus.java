package GraphicalInterface;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Processing.CarRental;

public class ToggleInsuranceStatus extends JPanel{

    ToggleInsuranceStatus(ChangeInsurance changeInsurance, JFrame main)
    {

        String[] stati = {"Disponible", "Inhabilitado"};
        JLabel insuranceLabel = new JLabel("Ingrese el nombre del seguro a modificar: ");
        JTextField insuranceText = new JTextField(6);
        JLabel statusLabel = new JLabel("Seleccione el estado al que desea cambiar el seguro: ");
        JComboBox<String> statusOptions = new JComboBox<String>(stati);
        JButton accept = new JButton("Aceptar");
        JButton back = new JButton("Atrás");

        accept.addActionListener(e ->
        {


        String insurance = insuranceText.getText();
        if (!CarRental.insuranceExists(insurance))
        {
            new ErrorDialog("Este seguro no fue encontrado en el sistema!", main);
            return;
        }

        CarRental.changeInsuranceStatus(insurance);
        changeInsurance.showMain();

    });

    back.addActionListener(e ->
        {
            changeInsurance.showMain();
        });

        add(insuranceLabel);
        add(insuranceText);
        add(statusLabel);
        add(statusOptions);
        add(accept);
        add(back);
    
}
}
