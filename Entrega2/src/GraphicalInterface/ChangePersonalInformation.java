package GraphicalInterface;
import javax.swing.*;
    import java.awt.*;
    import java.awt.event.ActionEvent;
    import java.awt.event.ActionListener;

public class ChangePersonalInformation extends JPanel {

    String login;

    public ChangePersonalInformation(String login)
    {

        this.login = login;

        JPanel changePersonalInfoPanel = new JPanel(new GridLayout(3,1));

        JButton changePersonalInformationButton = new JButton("Cambiar Infromación Personal");
        changePersonalInformationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                JPanel personalInfoPanel = new PersonalInformation(login);
                

            }
            
        });
        changePersonalInfoPanel.add(changePersonalInformationButton);

        JButton changePaymentMethodButton = new JButton("Cambiar Metodo de Pago");
        changePaymentMethodButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                JPanel changePaymentMethodPanel = new ChangePaymentMethod(login);
            }
           

        });
        changePersonalInfoPanel.add(changePaymentMethodButton);

        JButton changeLicenceButton= new JButton("CambiarInformación Licencia"); 
        changeLicenceButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                JPanel changeLicence = new ChangeLicence(login);
            }
            
        });
        changePersonalInfoPanel.add(changeLicenceButton);

    



        
        

    }
        
}
