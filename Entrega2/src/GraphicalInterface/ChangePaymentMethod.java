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

public class ChangePaymentMethod extends JPanel{

    public ChangePaymentMethod(String login)
    {

        setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();

        JLabel cardNumberLabel = new JLabel("Número Tarjeta: ");
        JTextField cardNumberField = new JTextField(20);
        constraints.gridx = 0;
        constraints.gridy = 0;
        add(cardNumberLabel, constraints);
        constraints.gridx = 1;
        add(cardNumberField, constraints);

        JLabel cardCodeLabel = new JLabel("Código CVV: ");
        JTextField cardCodeField = new JTextField(5);
        constraints.gridx = 2;
        add(cardCodeLabel, constraints);
        constraints.gridx = 3;
        add(cardCodeField, constraints);

        JLabel cardOwnerLabel = new JLabel("Nombre en la Tarjeta: ");
        JTextField cardOwnerField = new JTextField(20);
        constraints.gridx = 0;
        constraints.gridy = 1;
        add(cardOwnerLabel, constraints);
        constraints.gridx = 1;
        add(cardOwnerField, constraints);

        JLabel cardExpirationLabel = new JLabel("Fecha de Vencimiento Tarjeta: ");
        JTextField cardExpirationField = new JTextField("dd/mm/aaaa", 10);
        constraints.gridx = 2;
        add(cardExpirationLabel, constraints);
        constraints.gridx = 3;
        add(cardExpirationField, constraints);

        JLabel cardAddressLabel = new JLabel("Dirección: ");
        JTextField cardAddressField = new JTextField(30);
        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.gridwidth = 2;
        add(cardAddressLabel, constraints);
        constraints.gridx = 2;
        add(cardAddressField, constraints);

        JButton okButton = new JButton("Aceptar");
        okButton.addActionListener(new ActionListener() 
        {
            public void actionPerformed(ActionEvent e) 
            {
            String cardNumberStr = cardNumberField.getText();
            long cardNumber = Long.parseLong(cardNumberStr);
            String cardExpirationString = cardExpirationField.getText();
            String cardCodeStr = cardCodeField.getText();
            short cardCode = Short.parseShort(cardCodeStr);
            String cardOwner = cardOwnerField.getText();
            String cardAddress = cardAddressField.getText();

            //cambiar cardExpirationStr a Calendar
            Integer[] calendarValues = {0, 0, 0}; 
            byte i = 0;
			for (String value: cardExpirationString.split("/")) 
            {
				calendarValues[i] = Integer.parseInt(value);
				i += 1;
			}
			Calendar cardExpiration = Calendar.getInstance();
			cardExpiration.set(calendarValues[0], calendarValues[1], calendarValues[2], 0, 0, 0);

            CarRental.modifyPaymentMethod(login, cardNumber, cardExpiration, cardCode, cardOwner, cardAddress);
            }
        });
        constraints.gridx = 0;
        constraints.gridy = 3;
        constraints.gridwidth = 4;
        add(okButton, constraints);

}}
