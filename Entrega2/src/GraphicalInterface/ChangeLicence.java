package GraphicalInterface;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Processing.CarRental;

public class ChangeLicence extends JPanel{

    public ChangeLicence()
    {
        JPanel changeLicencePanel = new JPanel();

        JLabel licenceNumberLabel = new JLabel("Número Licencia: ");
        JTextField licenceNumberField = new JTextField();

        JLabel licenceCountryLabel = new JLabel("País Licencia: ");
        JTextField licenceCountryField = new JTextField();

        JLabel licenceExpirationLabel = new JLabel("Fecha de Vencimiento Licencia: ");
        JTextField licenceExpirationField = new JTextField("dd/mm/aaaa");

        JLabel licencePhotoPathLabel = new JLabel("Licencia Photo Path:");
        JTextField licencePhotoPathField = new JTextField();

        changeLicencePanel.add(licenceNumberLabel);
        changeLicencePanel.add(licenceNumberField);

        changeLicencePanel.add(licenceCountryLabel);
        changeLicencePanel.add(licenceCountryField);

        changeLicencePanel.add(licenceExpirationLabel);
        changeLicencePanel.add(licenceExpirationField);

        changeLicencePanel.add(licencePhotoPathLabel);
        changeLicencePanel.add(licencePhotoPathField);

        JButton okButton = new JButton("Aceptar");
        okButton.addActionListener(new ActionListener() {
             public void actionPerformed(ActionEvent e) {

                String licenceNumberStr= licenceNumberField.getText();
                long licenceNumber = Long.parseLong(licenceNumberStr);
                String licenceCountry = licenceCountryField.getText();
                String licenceExpirationString = licenceExpirationField.getText();
                String licencePhotoPath = licencePhotoPathField.getText();

                //cambiar licenceExpirationStr a Calendar
                byte i = 0;
				int[] calendarValues = {0, 0, 0};
                for (String value: licenceExpirationString.split("/")) {
                    calendarValues[i] = Integer.parseInt(value);
                    i += 1;
                }
                Calendar licenceExpiration = Calendar.getInstance();
                licenceExpiration.set(calendarValues[0], calendarValues[1], calendarValues[2], 0, 0, 0);

                CarRental.modifyLicence(login,licenceExpirationString, licenceNumber, licenceCountry, licenceExpiration, licencePhotoPath);



             }
            });



    }
    
}
