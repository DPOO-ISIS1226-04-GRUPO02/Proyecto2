package GraphicalInterface;

import javax.swing.*;

import Processing.CarRental;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;


public class PersonalInformation extends JPanel{

    private JTextField nameField;
    private JTextField phoneField;
    private JTextField emailField;
    private JTextField dateBirthField;
    private JTextField nationalityField;
    private JTextField idPhotoPathField;
    private JTextField licenceNumberField;
    private JTextField licenceCountryField;
    private JTextField licenceExpirationField;
    private JTextField licencePhotoPathField;
    private JTextField cardNumberField;
    private JTextField cardCodeField;
    private JTextField cardOwnerField;
    private JTextField cardExpirationField;
    private JTextField cardAddressField;

    public PersonalInformation()
    {
        
        setSize(500, 500);
        

        JPanel personalInfoPanel = new JPanel(new GridLayout(5,3));


        JLabel nameLabel = new JLabel("Nombre: ");
        nameField = new JTextField();

        JLabel phoneLabel = new JLabel("Celular: ");
        phoneField = new JTextField();

        JLabel emailLabel = new JLabel("Email: ");
        //TODO: manejo de error verificar que sea un email valido: (linea 108 view) while (!email.contains("@")) {System.out.println("Ingrese un email válido");
        emailField = new JTextField();

        JLabel dateBirthLabel = new JLabel("Fecha de Nacimiento: ");
        dateBirthField = new JTextField("dd/mm/aaaa");

        JLabel nationalityLabel = new JLabel("País de Nacimiento: ");
        nationalityField = new JTextField();

        JLabel idPhotoPathLabel = new JLabel("Id Photo Path: ");
        idPhotoPathField = new JTextField();

        JLabel licenceNumberLabel = new JLabel("Número Licencia: ");
        licenceNumberField = new JTextField();

        JLabel licenceCountryLabel = new JLabel("País Licencia: ");
        licenceCountryField = new JTextField();

        JLabel licenceExpirationLabel = new JLabel("Fecha de Vencimiento Licencia: ");
        licenceExpirationField = new JTextField("dd/mm/aaaa");

        JLabel licencePhotoPathLabel = new JLabel("Licencia Photo Path:");
        licencePhotoPathField = new JTextField();

        JLabel cardNumberLabel = new JLabel("Número Tarjeta: ");
        cardNumberField = new JTextField();

        JLabel cardCodeLabel = new JLabel("Código CVV: ");
        cardCodeField = new JTextField();

        JLabel cardOwnerLabel = new JLabel("Nombre ne la Tarjeta: ");
        cardOwnerField = new JTextField();

        JLabel cardExpirationLabel = new JLabel("Fecha de Vencimiento Tarjeta: ");
        cardExpirationField = new JTextField("dd/mm/aaaa");

        JLabel cardAddressLabel = new JLabel("Dirección: ");
        cardAddressField = new JTextField();

        personalInfoPanel.add(nameLabel);
        personalInfoPanel.add(nameField);

        personalInfoPanel.add(phoneLabel);
        personalInfoPanel.add(phoneField);

        personalInfoPanel.add(emailLabel);
        personalInfoPanel.add(emailField);

        personalInfoPanel.add(dateBirthLabel);
        personalInfoPanel.add(dateBirthField);

        personalInfoPanel.add(nationalityLabel);
        personalInfoPanel.add(nationalityField);

        personalInfoPanel.add(idPhotoPathLabel);
        personalInfoPanel.add(idPhotoPathField);

        personalInfoPanel.add(licenceNumberLabel);
        personalInfoPanel.add(licenceNumberField);

        personalInfoPanel.add(licenceCountryLabel);
        personalInfoPanel.add(licenceCountryField);

        personalInfoPanel.add(licenceExpirationLabel);
        personalInfoPanel.add(licenceExpirationField);

        personalInfoPanel.add(licencePhotoPathLabel);
        personalInfoPanel.add(licencePhotoPathField);

        personalInfoPanel.add(cardNumberLabel);
        personalInfoPanel.add(cardNumberField);

        personalInfoPanel.add(cardCodeLabel);
        personalInfoPanel.add(cardCodeField);

        personalInfoPanel.add(cardOwnerLabel);
        personalInfoPanel.add(cardOwnerField);

        personalInfoPanel.add(cardExpirationLabel);
        personalInfoPanel.add(cardExpirationField);

        personalInfoPanel.add(cardAddressLabel);
        personalInfoPanel.add(cardAddressField);

        JButton okButton = new JButton("Aceptar");
        okButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                String name = nameField.getText();
                String phoneStr = phoneField.getText();
                long phone = Long.parseLong(phoneStr);
                String email = emailField.getText();
                String dateBirthString = dateBirthField.getText();
                String nationality = nationalityField.getText();
                String idPhotoPath = idPhotoPathField.getText();
                String cardNumberStr = cardNumberField.getText();
                long cardNumber = Long.parseLong(cardNumberStr);
                String cardExpirationString = cardExpirationField.getText();
                String cardCodeStr = cardCodeField.getText();
                short cardCode = Short.parseShort(cardCodeStr);
                String cardOwner = cardOwnerField.getText();
                String cardAddress = cardAddressField.getText();
                String licenceNumberStr= licenceNumberField.getText();
                long licenceNumber = Long.parseLong(licenceNumberStr);
                String licenceCountry = licenceCountryField.getText();
                String licenceExpirationString = licenceExpirationField.getText();
                String licencePhotoPath = licencePhotoPathField.getText();





                //cambiar dateBirthStr a Calendar
                byte i = 0;
					int[] calendarValues = {0, 0, 0};
					for (String value: dateBirthString.split("/")) {
						calendarValues[i] = Integer.parseInt(value);
						i += 1;
					}
					Calendar dateBirth = Calendar.getInstance();
					dateBirth.set(calendarValues[0], calendarValues[1], calendarValues[2], 0, 0, 0);

                //cambiar cardExpirationStr a Calendar
                i = 0;
					for (String value: cardExpirationString.split("/")) {
						calendarValues[i] = Integer.parseInt(value);
						i += 1;
					}
					Calendar cardExpiration = Calendar.getInstance();
					cardExpiration.set(calendarValues[0], calendarValues[1], calendarValues[2], 0, 0, 0);
                
                //cambiar licenceExpirationStr a Calendar
                i = 0;
					for (String value: licenceExpirationString.split("/")) {
						calendarValues[i] = Integer.parseInt(value);
						i += 1;
					}
					Calendar licenceExpiration = Calendar.getInstance();
					licenceExpiration.set(calendarValues[0], calendarValues[1], calendarValues[2], 0, 0, 0);


                //guardar la información

                CarRental.registerNewClient(name, phone, email, dateBirth, nationality, idPhotoPath, cardNumber, 
						cardExpiration, cardCode, cardOwner, cardAddress, login, licenceNumber, licenceCountry, 
						licenceExpiration, licencePhotoPath);
					
            }

        });
        personalInfoPanel.add(okButton);













    }

    







}



