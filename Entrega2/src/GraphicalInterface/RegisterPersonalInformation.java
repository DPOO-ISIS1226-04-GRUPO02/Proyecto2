package GraphicalInterface;

import javax.swing.*;

import Processing.CarRental;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;


public class RegisterPersonalInformation extends JPanel{

   

    public RegisterPersonalInformation(String login, JFrame main, ClientView client)
    {
        
        setSize(500, 500);
        setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();

        JLabel nameLabel = new JLabel("Nombre: ");
        JTextField nameField = new JTextField(20);

        JLabel phoneLabel = new JLabel("Celular: ");
        JTextField phoneField = new JTextField(10);

        JLabel emailLabel = new JLabel("Email: ");
        JTextField emailField = new JTextField(20);

        JLabel dateBirthLabel = new JLabel("Fecha de Nacimiento: ");
        JTextField dateBirthField = new JTextField("dd/mm/aaaa", 10);

        JLabel nationalityLabel = new JLabel("País de Nacimiento: ");
        JTextField nationalityField = new JTextField(20);

        JLabel idPhotoPathLabel = new JLabel("Id Photo Path: ");
        JTextField idPhotoPathField = new JTextField(20);

        JLabel licenceNumberLabel = new JLabel("Número Licencia: ");
        JTextField licenceNumberField = new JTextField(20);

        JLabel licenceCountryLabel = new JLabel("País Licencia: ");
        JTextField licenceCountryField = new JTextField(20);

        JLabel licenceExpirationLabel = new JLabel("Fecha de Vencimiento Licencia: ");
        JTextField licenceExpirationField = new JTextField("dd/mm/aaaa", 10);

        JLabel licencePhotoPathLabel = new JLabel("Licencia Photo Path:");
        JTextField licencePhotoPathField = new JTextField(10);

        JLabel cardNumberLabel = new JLabel("Número Tarjeta: ");
        JTextField cardNumberField = new JTextField(20);

        JLabel cardCodeLabel = new JLabel("Código CVV: ");
        JTextField cardCodeField = new JTextField(5);

        JLabel cardOwnerLabel = new JLabel("Nombre en la Tarjeta: ");
        JTextField cardOwnerField = new JTextField(20);

        JLabel cardExpirationLabel = new JLabel("Fecha de Vencimiento Tarjeta: ");
        JTextField cardExpirationField = new JTextField("mm/aaaa", 10);

        JLabel cardAddressLabel = new JLabel("Dirección: ");
        JTextField cardAddressField = new JTextField(20);

        constraints.gridx = 0;
        constraints.gridy = 0;
        add(nameLabel, constraints);
        constraints.gridx = 1;
        add(nameField, constraints);

        constraints.gridx = 0;
        constraints.gridy = 1;
        add(phoneLabel, constraints);
        constraints.gridx = 1;
        add(phoneField, constraints);

        constraints.gridx = 0;
        constraints.gridy = 2;
        add(emailLabel, constraints);
        constraints.gridx = 1;
        add(emailField, constraints);

        constraints.gridx = 0;
        constraints.gridy = 3;
        add(dateBirthLabel, constraints);
        constraints.gridx = 1;
        add(dateBirthField, constraints);

        constraints.gridx = 0;
        constraints.gridy = 4;
        add(nationalityLabel, constraints);
        constraints.gridx = 1;
        add(nationalityField, constraints);

        constraints.gridx = 0;
        constraints.gridy = 5;
        add(idPhotoPathLabel, constraints);
        constraints.gridx = 1;
        add(idPhotoPathField, constraints);

        constraints.gridx = 0;
        constraints.gridy = 6;
        add(licenceNumberLabel, constraints);
        constraints.gridx = 1;
        add(licenceNumberField, constraints);

        constraints.gridx = 0;
        constraints.gridy = 7;
        add(licenceCountryLabel, constraints);
        constraints.gridx = 1;
        add(licenceCountryField, constraints);

        constraints.gridx = 0;
        constraints.gridy = 8;
        add(licenceExpirationLabel, constraints);
        constraints.gridx = 1;
        add(licenceExpirationField, constraints);

        constraints.gridx = 0;
        constraints.gridy = 9;
        add(licencePhotoPathLabel, constraints);
        constraints.gridx = 1;
        add(licencePhotoPathField, constraints);

        constraints.gridx = 0;
        constraints.gridy = 10;
        add(cardNumberLabel, constraints);
        constraints.gridx = 1;
        add(cardNumberField, constraints);

        constraints.gridx = 0;
        constraints.gridy = 11;
        add(cardCodeLabel, constraints);
        constraints.gridx = 1;
        add(cardCodeField, constraints);

        constraints.gridx = 0;
        constraints.gridy = 12;
        add(cardOwnerLabel, constraints);
        constraints.gridx = 1;
        add(cardOwnerField, constraints);

        constraints.gridx = 0;
        constraints.gridy = 13;
        add(cardExpirationLabel, constraints);
        constraints.gridx = 1;
        add(cardExpirationField, constraints);

        constraints.gridx = 0;
        constraints.gridy = 14;
        add(cardAddressLabel, constraints);
        constraints.gridx = 1;
        add(cardAddressField, constraints);

        JButton okButton = new JButton("Aceptar");
        okButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e) 
            {

                String name = nameField.getText();
                String phoneStr = phoneField.getText();
                long phone = 0;
                try {
                    phone = Long.parseLong(phoneStr);
                } catch (NumberFormatException ex) {
                    new ErrorDialog("El formato del telefono esta mal", main);
                    return;
                }
                String email = emailField.getText();
                String dateBirthString = dateBirthField.getText();
                String nationality = nationalityField.getText();
                String idPhotoPath = idPhotoPathField.getText();
                String cardNumberStr = cardNumberField.getText();
                long cardNumber = 0;
                try {
                    cardNumber = Long.parseLong(cardNumberStr);
                } catch (NumberFormatException ex) {
                    new ErrorDialog("El formato del número de la tarjeta esta mal", main);
                    return;
                }
                String cardExpirationString = cardExpirationField.getText();
                String cardCodeStr = cardCodeField.getText();
                short cardCode = 0;
                try {
                    cardCode = Short.parseShort(cardCodeStr);
                } catch (NumberFormatException ex) {
                    new ErrorDialog("El formato del código de la tarjeta esta mal", main);
                    return;
                }
                String cardOwner = cardOwnerField.getText();
                String cardAddress = cardAddressField.getText();
                String licenceNumberStr= licenceNumberField.getText();
                long licenceNumber = 0;
                try {
                    licenceNumber = Long.parseLong(licenceNumberStr);
                } catch (NumberFormatException ex) {
                    new ErrorDialog("El formato del número de la licencia esta mal", main);
                    return;
                }
                String licenceCountry = licenceCountryField.getText();
                String licenceExpirationString = licenceExpirationField.getText();
                String licencePhotoPath = licencePhotoPathField.getText();

                //cambiar dateBirthStr a Calendar
                byte i = 0;
				int[] calendarValues = {0, 0, 0};
                Calendar dateBirth = null;
                try {
    				for (String value: dateBirthString.split("/")) {
    					calendarValues[i] = Integer.parseInt(value);
    					i += 1;
    				}
    				dateBirth = Calendar.getInstance();
    				dateBirth.set(calendarValues[0], calendarValues[1], calendarValues[2], 0, 0, 0);
                } catch (NumberFormatException ex) {
                    new ErrorDialog("El formato de la fecha de nacimiento esta mal", main);
                    return;
                }

                //cambiar cardExpirationStr a Calendar
                i = 0;
                Calendar cardExpiration = null;
                try {
				    for (String value: cardExpirationString.split("/")) {
				    	calendarValues[i] = Integer.parseInt(value);
				    	i += 1;
				    }
				    cardExpiration = Calendar.getInstance();
				    cardExpiration.set(calendarValues[0], calendarValues[1], 0, 0, 0, 0);
                } catch (NumberFormatException ex) {
                    new ErrorDialog("El formato de la expiración de la tarjeta esta mal", main);
                    return;
                }
                
                //cambiar licenceExpirationStr a Calendar
                i = 0;
                Calendar licenceExpiration = null;
                try {
				    for (String value: licenceExpirationString.split("/")) {
				    	calendarValues[i] = Integer.parseInt(value);
				    	i += 1;
				    }
				    licenceExpiration = Calendar.getInstance();
				    licenceExpiration.set(calendarValues[0], calendarValues[1], calendarValues[2], 0, 0, 0);
                } catch (NumberFormatException ex) {
                    new ErrorDialog("El formato de la expiración de la licencia esta mal", main);
                    return;
                }

                //guardar la información
                CarRental.registerNewClient(name, phone, email, dateBirth, nationality, idPhotoPath, cardNumber, 
						cardExpiration, cardCode, cardOwner, cardAddress, login, licenceNumber, licenceCountry, 
						licenceExpiration, licencePhotoPath);
                client.showMain();
					
            }

        });
        constraints.gridx = 0;
        constraints.gridy = 15;
        constraints.gridwidth = 2;
        add(okButton, constraints);













    }

    







}



