package GraphicalInterface;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Processing.CarRental;

public class RegisterCategory extends JPanel {

    RegisterCategory(ChangeCategory changeCategory, JFrame main)
    {

        setLayout(new GridLayout(3, 2));

        JLabel nameLabel = new JLabel("Ingrese nombre de la categoria: ");
        JTextField nameText = new JTextField(20);
        JLabel tariffLabel = new JLabel("Ingrese tarifa diaria de la categoria: ");
        JTextField tariffText = new JTextField("ABC123", 6);

        JButton accept = new JButton("Aceptar");
        JButton back = new JButton("Atrás");

        accept.addActionListener(e -> 
        {
            String name = nameText.getText();
            int cost = Integer.parseInt(tariffText.getText());
            

            CarRental.setTariff(name, cost);

            changeCategory.showMain();
            
        });
        back.addActionListener(e ->
        {
            changeCategory.showMain();
        });

        add(nameLabel);
        add(nameText);
        add(tariffLabel);
        add(tariffText);
        add(accept);
        add(back);


    }
    
}
