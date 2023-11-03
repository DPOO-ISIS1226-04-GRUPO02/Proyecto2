package GraphicalInterface;
import javax.swing.*;
    import java.awt.*;
    import java.awt.event.ActionEvent;
    import java.awt.event.ActionListener;

public class ChangePersonalInformation extends JPanel {

    private JPanel personalInfoPanel;
    private JPanel changePaymentMethodPanel;
    private JPanel changeLicence;

    public ChangePersonalInformation(String login, ClientView client)
    {
        ChangePersonalInformation panel = this;
        setLayout(new CardLayout());
        JPanel mainCard = new JPanel();
        mainCard.setLayout(new FlowLayout());

        personalInfoPanel = new PersonalInformation(login, this);
        changePaymentMethodPanel = new ChangePaymentMethod(login, this);
        changeLicence = new ChangeLicence(login, this);

        JButton changePersonalInformationButton = new JButton("Cambiar Infromación Personal");
        changePersonalInformationButton.addActionListener(new ActionListener() 
        {
            public void actionPerformed(ActionEvent e) 
            {
                CardLayout cardLayout = (CardLayout) panel.getLayout();
                cardLayout.show(panel, "Personal");
            }
            
        });
        mainCard.add(changePersonalInformationButton);

        JButton changePaymentMethodButton = new JButton("Cambiar Metodo de Pago");
        changePaymentMethodButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e) 
            {
                CardLayout cardLayout = (CardLayout) panel.getLayout();
                cardLayout.show(panel, "Payment");
            }
        });
        mainCard.add(changePaymentMethodButton);

        JButton changeLicenceButton= new JButton("Cambiar Licencia"); 
        changeLicenceButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e) 
            {
                CardLayout cardLayout = (CardLayout) panel.getLayout();
                cardLayout.show(panel, "Licence");
            }  
        });
        mainCard.add(changeLicenceButton);

        JButton back = new JButton("Atrás");
        back.addActionListener(new ActionListener() 
        {
            public void actionPerformed(ActionEvent e)
            {
                client.showMain();
            }
        });
        mainCard.add(back);

        add(mainCard, "Main");
        add(personalInfoPanel, "Personal");
        add(changePaymentMethodPanel, "Payment");
        add(changeLicence, "Licence");

    }

    void showMain() 
    {
        CardLayout cardLayout = (CardLayout) getLayout();
        cardLayout.show(this, "Main");
    }
        
}
