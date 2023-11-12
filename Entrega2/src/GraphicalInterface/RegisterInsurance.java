package GraphicalInterface;

import java.awt.GridLayout;
import javax.swing.*;

import Processing.CarRental;

public class RegisterInsurance extends JPanel {


    RegisterInsurance(ChangeInsurance changeInsurance, JFrame main)
    {
        setLayout(new GridLayout(4, 2));

        JLabel nameLabel = new JLabel("Ingrese nombre del seguro: ");
        JTextField nameText = new JTextField(20);
        JLabel tariffLabel = new JLabel("Ingrese tarifa diaria del seguro: ");
        JTextField tariffText = new JTextField("ABC123", 6);
        JLabel infoLabel = new JLabel("Ingrese las especificaciones: ");
        JTextField infoText = new JTextField(20);

        JButton accept = new JButton("Aceptar");
        JButton back = new JButton("Atrás");

        accept.addActionListener(e -> 
        {
            String name = nameText.getText();
            int cost = Integer.parseInt(tariffText.getText());
            String specs = infoText.getText();

            CarRental.addInsurance(name, cost, specs);


            changeInsurance.showMain();
        });
        back.addActionListener(e ->
        {
            changeInsurance.showMain();
        });

        add(nameLabel);
        add(nameText);
        add(tariffLabel);
        add(tariffText);
        add(infoLabel);
        add(infoText);
        add(accept);
        add(back);



    }
    
}
