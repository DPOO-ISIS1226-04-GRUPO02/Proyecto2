package GraphicalInterface;

import java.awt.*;
import java.awt.event.*;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.*;

import Processing.CarRental;

public class ReserveCar extends JPanel
{

    ReserveCar(String login, JFrame main, ClientView client) 
    {
        setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        setSize(getPreferredSize());

        constraints.gridx = 0;
        constraints.gridy = 0;
        JLabel categoryLabel = new JLabel("Categoría:");
        add(categoryLabel, constraints);

        constraints.gridx = 1;
        ArrayList<String> categories = new ArrayList<String>(CarRental.getCategories());
        String[] categoryOptions = new String[categories.size()];
        for (int i = 0; i < categories.size(); i++)
        {
            categoryOptions[i] = categories.get(i);
        }
        JComboBox<String> category = new JComboBox<String>(categoryOptions);
        add(category, constraints);

        constraints.gridx = 0;
        constraints.gridy = 1;
        JLabel originLabel = new JLabel("Tienda donde recogerá el carro: ");
        add(originLabel, constraints);

        constraints.gridy = 2;
        JLabel destinationLabel = new JLabel("Tienda donde lo devolverá: ");
        add(destinationLabel, constraints);

        constraints.gridx = 1;
        constraints.gridy = 1;
        ArrayList<String> stores = new ArrayList<String>(CarRental.getStores());
        String[] storeOptions = new String[stores.size()];
        for (int i = 0; i < stores.size(); i++)
        {
            storeOptions[i] = stores.get(i);
        }
        JComboBox<String> origin = new JComboBox<String>(storeOptions);
        add(origin, constraints);

        constraints.gridy = 2;
        JComboBox<String> destination = new JComboBox<String>(storeOptions);
        add(destination, constraints);

        constraints.gridx = 0;
        constraints.gridy = 3;
        JLabel initialDateLabel = new JLabel("Ingrese la fecha en que retornará el vehículo: ");
        add(initialDateLabel, constraints);

        constraints.gridx = 1;
        JTextField initialDate = new JTextField("AA-MM-DD/HH:MM", 14);
        add(initialDate, constraints);

        constraints.gridx = 0;
        constraints.gridy = 4;
        JLabel returnDateLabel = new JLabel("Ingrese la fecha en que devolverá el vehículo: ");
        add(returnDateLabel, constraints);

        constraints.gridx = 1;
        JTextField returnDate = new JTextField("AA-MM-DD/HH:MM", 14);
        add(returnDate, constraints);

        constraints.gridx = 0;
        constraints.gridy = 5;
        constraints.gridwidth = 2;
        JButton accept = new JButton("Accept");
        accept.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                String initialDateString = initialDate.getText(); 
                SimpleDateFormat sdf = new SimpleDateFormat("AA-MM-DD/HH:MM");
                Calendar initialCalendar = Calendar.getInstance();
                try
                {
                    Date initialDate2 = (Date) sdf.parse(initialDateString); 
                    initialCalendar.setTime(initialDate2);
                } 
                catch (ParseException ex) 
                {
                    new ErrorDialog("El formato de la fecha para recoger el vehículo es erroneo.", main);
                    return;
                }
                String returnDateString = returnDate.getText();
                Calendar returnCalendar = Calendar.getInstance();
                try
                {
                    Date returnDate2 = (Date) sdf.parse(returnDateString); 
                    returnCalendar.setTime(returnDate2);
                }
                catch (ParseException ex)
                {
                    new ErrorDialog("El formato de la fecha para devolver el vehículo es erroneo.", main);
                    return;
                }
                String category_ = (String) category.getSelectedItem();
                String origin_ = (String) origin.getSelectedItem();
                String destination_ = (String) destination.getSelectedItem();
                String response = CarRental.reserveCar(login, category_, origin_, destination_, initialCalendar, returnCalendar);
                if (!response.equals(null)) new ErrorDialog(response, main); 
                else client.showMain();
            }
        });
        add(accept, constraints);
    }
    
}
