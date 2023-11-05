package GraphicalInterface;

import java.awt.CardLayout;
import java.awt.FlowLayout;
import java.awt.event.*;

import javax.swing.*;

public class EmployeeView extends JPanel {

    EmployeeView(String login, String password, JFrame main) 
    {
        JPanel panel = this;
        PickUp pickUp = new PickUp(this, login, password, main);
        Devolution devolution = new Devolution(this, login, password, main);

        setLayout(new CardLayout());
        JPanel mainCard = new JPanel();
        JButton pickUpButton = new JButton("Recogida de un vehículo");
        JButton devolutionButton = new JButton("Retorno de vehículo");

        mainCard.setLayout(new FlowLayout());
        mainCard.add(pickUpButton);
        mainCard.add(devolutionButton);

        pickUpButton.addActionListener(new ActionListener() 
        {
            public void actionPerformed(ActionEvent e)
            {
                CardLayout cardLayout = (CardLayout) panel.getLayout();
                cardLayout.show(panel, "PickUp");
            }    
        });

        devolutionButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                CardLayout cardLayout = (CardLayout) panel.getLayout();
                cardLayout.show(panel, "Devolution");
            }
        });

        add(mainCard, "Main");
        add(pickUp, "PickUp");
        add(devolution, "Devolution");
    }

    void showMain()
    {
        CardLayout cardLayout = (CardLayout) getLayout();
        cardLayout.show(this, "Main");
    }
}
