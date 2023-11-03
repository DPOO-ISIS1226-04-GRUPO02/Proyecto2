package GraphicalInterface;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Processing.CarRental;

public class ChangeLicence extends JPanel{

    public ChangeLicence(String login, ChangePersonalInformation main)
    {
        setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();

        JLabel licenceNumberLabel = new JLabel("Número Licencia: ");
        JTextField licenceNumberField = new JTextField(20);
        constraints.gridx = 0;
        constraints.gridy = 0;
        add(licenceNumberLabel, constraints);
        constraints.gridx = 1;
        add(licenceNumberField, constraints);

        JLabel licenceCountryLabel = new JLabel("País Licencia: ");
        JTextField licenceCountryField = new JTextField(20);
        constraints.gridx = 2;
        add(licenceCountryLabel, constraints);
        constraints.gridx = 3;
        add(licenceCountryField, constraints);

        JLabel licenceExpirationLabel = new JLabel("Fecha de Vencimiento Licencia: ");
        JTextField licenceExpirationField = new JTextField("dd/mm/aaaa", 20);
        constraints.gridx = 0;
        constraints.gridy = 1;
        add(licenceExpirationLabel, constraints);
        constraints.gridx = 1;
        add(licenceExpirationField, constraints);

        JLabel licencePhotoPathLabel = new JLabel("Licencia Photo Path:");
        JTextField licencePhotoPathField = new JTextField(20);
        constraints.gridx = 2;
        add(licencePhotoPathLabel);
        constraints.gridx = 3;
        add(licencePhotoPathField);

        JButton okButton = new JButton("Aceptar");
        okButton.addActionListener(new ActionListener() 
        {
             public void actionPerformed(ActionEvent e) 
             {
                String licenceNumberStr = licenceNumberField.getText();
                long licenceNumber = Long.parseLong(licenceNumberStr);
                String licenceCountry = licenceCountryField.getText();
                String licenceExpirationString = licenceExpirationField.getText();
                String licencePhotoPath = licencePhotoPathField.getText();

                //cambiar licenceExpirationStr a Calendar
                byte i = 0;
				int[] calendarValues = {0, 0, 0};
                for (String value: licenceExpirationString.split("/")) 
                {
                    calendarValues[i] = Integer.parseInt(value);
                    i += 1;
                }
                Calendar licenceExpiration = Calendar.getInstance();
                licenceExpiration.set(calendarValues[0], calendarValues[1], calendarValues[2], 0, 0, 0);

                CarRental.modifyLicence(login, licenceNumber, licenceCountry, licenceExpiration, licencePhotoPath);
                main.showMain();
             }
        });
        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.gridwidth = 4;
        add(okButton, constraints);
        setVisible(true);

    }
    
}
