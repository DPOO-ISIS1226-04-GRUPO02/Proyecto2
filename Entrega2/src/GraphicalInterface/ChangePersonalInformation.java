package GraphicalInterface;
import javax.swing.*;
    import java.awt.*;
    import java.awt.event.ActionEvent;
    import java.awt.event.ActionListener;

public class ChangePersonalInformation extends JPanel {

    public ChangePersonalInformation(String login)
    {
        setLayout(new GridLayout(3,1));

        JButton changePersonalInformationButton = new JButton("Cambiar Infromación Personal");
        changePersonalInformationButton.addActionListener(new ActionListener() 
        {
            public void actionPerformed(ActionEvent e) 
            {
                JPanel personalInfoPanel = new PersonalInformation(login);
            }
            
        });
        add(changePersonalInformationButton);

        JButton changePaymentMethodButton = new JButton("Cambiar Metodo de Pago");
        changePaymentMethodButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e) 
            {
                JPanel changePaymentMethodPanel = new ChangePaymentMethod(login);
            }
        });
        add(changePaymentMethodButton);

        JButton changeLicenceButton= new JButton("Cambiar Licencia"); 
        changeLicenceButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e) 
            {
                JPanel changeLicence = new ChangeLicence(login);

            }  
        });
        add(changeLicenceButton);
    }
        
}
