package GraphicalInterface;

import javax.swing.*;
import javax.swing.text.DateFormatter;
import javax.swing.text.DefaultFormatterFactory;

import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import Processing.CarRental;

public class RegisterStore extends JPanel
{
    RegisterStore(GManagerView gManager, JFrame main)
    {
        setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();

        JLabel nameLabel = new JLabel("Ingrese el nombre para la sede: ");
        JTextField nameText = new JTextField(20);
        JLabel locationLabel = new JLabel("Ingrese la ubicación para la sede: ");
        JTextField locationText = new JTextField(30);
        JLabel opTimeLabel = new JLabel("Ingrese la hora en que abre la sede: ");
        JFormattedTextField opTimeText = new JFormattedTextField();
        opTimeText.setFormatterFactory(new DefaultFormatterFactory(new DateFormatter(
            new SimpleDateFormat("HH:mm"))));
        opTimeText.setValue(Calendar.getInstance().getTime());
        JLabel clTimeLabel = new JLabel("Ingrese la hora en que cierra la sede: ");
        JFormattedTextField clTimeText = new JFormattedTextField();
        clTimeText.setFormatterFactory(new DefaultFormatterFactory(new DateFormatter(
            new SimpleDateFormat("HH:mm"))));
        clTimeText.setValue(Calendar.getInstance().getTime());
        JLabel openingDaysLabel = new JLabel("Seleccione los días en que abre la sede: ");
        String[] weekDays = {"Lunes", "Martes", "Miércoles", "Jueves", "Viernes", "Sábado", "Domingo"};
        JCheckBox[] weekDayBoxes = new JCheckBox[7];
        for (int i = 0; i < 7; i++) weekDayBoxes[i] = new JCheckBox(weekDays[i]);
        JButton accept = new JButton("Aceptar");
        JButton back = new JButton("Atrás");

        accept.addActionListener(e ->
        {
            String name = "Galetto Cars " + nameText.getText();
            String location = locationText.getText();
            Date opTimeDate = (Date) opTimeText.getValue();
            Calendar opTime = Calendar.getInstance();
            opTime.setTime(opTimeDate);
            Date clTimeDate = (Date) clTimeText.getValue();
            Calendar clTime = Calendar.getInstance();
            clTime.setTime(clTimeDate);
            byte openingDays = 0;
            for (int i = 0; i < 7; i++)
            {
                byte addition = 1;
                for (int j = 0; j < i; j++) addition *= 2;
                if (weekDayBoxes[i].isSelected()) openingDays += addition;
            }
            boolean result = CarRental.newStore(name, location, opTime, clTime, openingDays);
            if (result) JOptionPane.showMessageDialog(main, "Tienda creada correctamente!", "Information", JOptionPane.PLAIN_MESSAGE);
            else
            {
                new ErrorDialog("Este nombre de tienda ya existe!", main); 
                return;
            }
            gManager.showMain();
        });
        back.addActionListener(e ->
        {
            gManager.showMain();
        });

        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 4;
        add(nameLabel, constraints);
        constraints.gridx = 4;
        add(nameText, constraints);
        constraints.gridx = 0;
        constraints.gridy = 1;
        add(locationLabel, constraints);
        constraints.gridx = 4;
        add(locationText, constraints);
        constraints.gridx = 0;
        constraints.gridy = 2;
        add(opTimeLabel, constraints);
        constraints.gridx = 4;
        add(opTimeText, constraints);
        constraints.gridx = 0;
        constraints.gridy = 3;
        add(clTimeLabel, constraints);
        constraints.gridx = 4;
        add(clTimeText, constraints);
        constraints.gridx = 0;
        constraints.gridy = 4;
        constraints.gridwidth = 8;
        add(openingDaysLabel, constraints);
        constraints.gridy = 5;
        constraints.gridwidth = 1;
        for (int i = 0; i < 7; i++)
        {
            constraints.gridx = i;
            add(weekDayBoxes[i], constraints);
        }
        constraints.gridx = 0;
        constraints.gridy = 6;
        constraints.gridwidth = 4;
        add(accept, constraints);
        constraints.gridx = 4;
        add(back, constraints);
    }
}
