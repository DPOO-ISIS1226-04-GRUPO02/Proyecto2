package GraphicalInterface;

import javax.swing.*;


import Processing.CarRental;

import java.awt.*;
    import java.awt.event.ActionEvent;
    import java.awt.event.ActionListener;

public class PersonalInformation extends JPanel {

    public PersonalInformation()
    {
        JPanel personalInfoPanel = new JPanel();

        JLabel labelOptions = new JLabel("Información a cambiar:"); 

        String[] options = {"Nombre", "Teléfono", "Email", "Id Photo Path"};
        JComboBox<Object> optionsComboBox = new JComboBox<>(options); 

        JTextField changeInfoField = new JTextField();

        optionsComboBox.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e) {
            String selectedOption = (String) optionsComboBox.getSelectedItem();
            String infoString = changeInfoField.getText();

            CarRental.modifyClient(login, selectedOption, infoString);

            }
            
        }
        );

 
        


    }
    
}
