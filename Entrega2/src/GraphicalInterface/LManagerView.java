package GraphicalInterface;

import java.awt.CardLayout;
import java.awt.FlowLayout;
import java.awt.event.*;

import javax.swing.*;

public class LManagerView extends JPanel
{
    LManagerView(String login, String password, JFrame main) 
    {
        JPanel panel = this;
        RegisterEmployee employee = new RegisterEmployee(login, password, main, this);

        setLayout(new CardLayout());
        JPanel mainCard = new JPanel();
        JButton registerEmployeeButton = new JButton("Registrar un nuevo empleado");

        mainCard.setLayout(new FlowLayout());
        mainCard.add(registerEmployeeButton);

        registerEmployeeButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                CardLayout cardLayout = (CardLayout) panel.getLayout();
                cardLayout.show(panel, "Register");
            }    
        });

        add(mainCard, "Main");
        add(employee, "Register");
    }

    void showMain()
    {
        CardLayout cardLayout = (CardLayout) getLayout();
        cardLayout.show(this, "Main");
    }
}
