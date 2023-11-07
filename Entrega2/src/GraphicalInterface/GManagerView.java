package GraphicalInterface;

import java.awt.CardLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class GManagerView extends JPanel
{
    GManagerView(String login, String password, JFrame main) 
    {
        JPanel panel = this;
        GetPastRentals rentals = new GetPastRentals(main, this);
        // TODO: List all the panels for the possible options

        setLayout(new CardLayout());
        JPanel mainCard = new JPanel();
        JButton rentalsButton = new JButton("Ver historial de rentas de un carro");
        // TODO: List all the buttons and its corresponding panels

        mainCard.setLayout(new FlowLayout());
        mainCard.add(rentalsButton);
        // TODO: Add all buttons to the mainCard

        rentalsButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                CardLayout cardLayout = (CardLayout) panel.getLayout();
                cardLayout.show(panel, "Rentals");
            }
        });
        // TODO: Add actionListeners for all buttons

        add(mainCard, "Main");
        add(rentals, "Rentals");
        // TODO: Add all the panels to the CardLayout
    }

    void showMain()
    {
        CardLayout cardLayout = (CardLayout) getLayout();
        cardLayout.show(this, "Main");
    }   
}
