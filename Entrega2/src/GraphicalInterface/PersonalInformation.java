package GraphicalInterface;

import javax.swing.*;

import java.awt.FlowLayout;
import java.awt.event.*;

import Processing.CarRental;

public class PersonalInformation extends JPanel {

    public PersonalInformation(String login)
    {
        setLayout(new FlowLayout());
        setSize(getPreferredSize());

        JLabel labelOptions = new JLabel("Información a cambiar:"); 
        String[] options = {"Nombre", "Teléfono", "Email", "Id Photo Path"};
        JComboBox<Object> optionsComboBox = new JComboBox<>(options); 
        JTextField changeInfoField = new JTextField();
        JButton okButton = new JButton("Aceptar");

        okButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e) 
            {
                String selectedOption = (String) optionsComboBox.getSelectedItem();
                String infoString = changeInfoField.getText();
                CarRental.modifyInfoClient(login, selectedOption, infoString);
            }
        }
        );

        add(labelOptions);
        add(optionsComboBox);
        add(changeInfoField);
        add(okButton);
    }
    
}
