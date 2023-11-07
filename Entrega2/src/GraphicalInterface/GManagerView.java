package GraphicalInterface;

import java.awt.CardLayout;
import java.awt.FlowLayout;

import javax.swing.*;

public class GManagerView extends JPanel
{
    GManagerView(String login, String password, JFrame main) 
    {
        JPanel panel = this;
        // TODO: List all the panels for the possible options

        setLayout(new CardLayout());
        JPanel mainCard = new JPanel();
        // TODO: List all the buttons and its corresponding panels

        mainCard.setLayout(new FlowLayout());
        // TODO: Add all buttons to the mainCard

        // TODO: Add actionListeners for all buttons

        // TODO: Add all the panels to the CardLayout
    }

    void showMain()
    {
        CardLayout cardLayout = (CardLayout) getLayout();
        cardLayout.show(this, "Main");
    }   
}
