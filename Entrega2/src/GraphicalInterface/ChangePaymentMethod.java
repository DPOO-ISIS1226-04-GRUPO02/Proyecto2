package GraphicalInterface;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Processing.CarRental;

public class ChangePaymentMethod extends JPanel{

    public ChangePaymentMethod(String login, String password){

    
    JPanel changePaymentMethodPanel = new JPanel();


    JLabel cardNumberLabel = new JLabel("Número Tarjeta: ");
    JTextField cardNumberField = new JTextField();

    JLabel cardCodeLabel = new JLabel("Código CVV: ");
    JTextField cardCodeField = new JTextField();

    JLabel cardOwnerLabel = new JLabel("Nombre ne la Tarjeta: ");
    JTextField cardOwnerField = new JTextField();

    JLabel cardExpirationLabel = new JLabel("Fecha de Vencimiento Tarjeta: ");
    JTextField cardExpirationField = new JTextField("dd/mm/aaaa");

    JLabel cardAddressLabel = new JLabel("Dirección: ");
    JTextField cardAddressField = new JTextField();

    

    changePaymentMethodPanel.add(cardNumberLabel);
    changePaymentMethodPanel.add(cardNumberField);

    changePaymentMethodPanel.add(cardCodeLabel);
    changePaymentMethodPanel.add(cardCodeField);

    changePaymentMethodPanel.add(cardOwnerLabel);
    changePaymentMethodPanel.add(cardOwnerField);

    changePaymentMethodPanel.add(cardExpirationLabel);
    changePaymentMethodPanel.add(cardExpirationField);

    changePaymentMethodPanel.add(cardAddressLabel);
    changePaymentMethodPanel.add(cardAddressField);

    JButton okButton = new JButton("Aceptar");
        okButton.addActionListener(new ActionListener() {
             public void actionPerformed(ActionEvent e) {
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
			for (String value: cardExpirationString.split("/")) {
				calendarValues[i] = Integer.parseInt(value);
				i += 1;
				}
			Calendar cardExpiration = Calendar.getInstance();

			cardExpiration.set(calendarValues[0], calendarValues[1], calendarValues[2], 0, 0, 0);
                
            CarRental.modifyPaymentMethod(login, cardNumber, cardExpiration, cardCode, cardOwner, cardAddress);

            }
            
            });
            changePaymentMethodPanel.add(okButton);



}}
