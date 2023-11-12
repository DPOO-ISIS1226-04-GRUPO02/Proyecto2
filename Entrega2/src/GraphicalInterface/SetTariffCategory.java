package GraphicalInterface;

import java.awt.*;

import java.util.ArrayList;


import javax.swing.*;

import Processing.CarRental;

public class SetTariffCategory extends JPanel {

    SetTariffCategory (ChangeCategory changeCategory, JFrame main)
    {

        setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        setSize(getPreferredSize());

        constraints.gridx = 0;
        constraints.gridy = 0;
        JLabel categoryLabel = new JLabel("Seleccione la categoría:");
        add(categoryLabel, constraints);

        constraints.gridx = 1;
        ArrayList<String> categories = new ArrayList<String>(CarRental.getCategories());
        String[] categoryOptions = new String[categories.size()];
        for (int i = 0; i < categories.size(); i++)
        {
            categoryOptions[i] = categories.get(i);
        }
        JComboBox<String> categoriesBox = new JComboBox<String>(categoryOptions);
        add(categoriesBox, constraints);

        constraints.gridx = 0;
        constraints.gridy = 1;
        JLabel tariffLabel = new JLabel("Ingrese el precio nuevo de la categoria: ");
        add(tariffLabel, constraints);

        constraints.gridx = 1;
        JTextField tariffText = new JTextField();
        add(tariffText, constraints);

        constraints.gridx = 1;
        constraints.gridy = 1;
        constraints.gridwidth = 2;
        JButton accept = new JButton("Aceptar");

        constraints.gridx = 1;
        constraints.gridy = 2;
        constraints.gridwidth = 2;
        JButton back = new JButton("Atrás");

        accept.addActionListener(e -> 
        {
            
            String categoryString = (String) categoriesBox.getSelectedItem();
            int tariff = Integer.parseInt(tariffText.getText());
            CarRental.setTariff(categoryString, tariff);

            changeCategory.showMain();
        });
        back.addActionListener(e ->
        {
            changeCategory.showMain();
        });

        add(accept, constraints);
        add(back, constraints);

    }
    
}
