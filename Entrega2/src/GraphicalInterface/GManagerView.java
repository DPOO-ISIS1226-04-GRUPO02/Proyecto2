package GraphicalInterface;

import java.util.ArrayList;

import java.awt.CardLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class GManagerView extends JPanel
{
    private JPanel rentalButtons = new JPanel();

    GManagerView(String login, String password, JFrame main) 
    {
        JPanel panel = this;
        GetPastRentals rentals = new GetPastRentals(main, this);
        RegisterCar registerCar = new RegisterCar(this, main);
        RegisterStore registerStore = new RegisterStore(this, main);
        RegisterManager registerManager = new RegisterManager(this, main);
        
        // TODO: List all the panels for the possible options

        setLayout(new CardLayout());
        JPanel mainCard = new JPanel();
        JButton rentalsButton = new JButton("Ver historial de rentas de un carro");
        JButton registerCarButton = new JButton("Registrar un nuevo carro");
        JButton registerStoreButton = new JButton("Registrar una nueva sede");
        JButton registerManagerButton = new JButton("Registrar un nuevo gerente local");
        // TODO: List all the buttons and its corresponding panels

        mainCard.setLayout(new FlowLayout());
        mainCard.add(rentalsButton);
        mainCard.add(registerCarButton);
        mainCard.add(registerStoreButton);
        mainCard.add(registerManagerButton);
        // TODO: Add all buttons to the mainCard

        rentalsButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                CardLayout cardLayout = (CardLayout) panel.getLayout();
                cardLayout.show(panel, "Rentals");
            }
        });
        registerCarButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                CardLayout cardLayout = (CardLayout) panel.getLayout();
                cardLayout.show(panel, "Car");
            }
        });
        registerStoreButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                CardLayout cardLayout = (CardLayout) panel.getLayout();
                cardLayout.show(panel, "Store");
            }
        });
        registerManagerButton.addActionListener(e ->
        {
            CardLayout cardLayout = (CardLayout) panel.getLayout();
            cardLayout.show(panel, "Manager");
        });
        // TODO: Add actionListeners for all buttons

        add(mainCard, "Main");
        add(rentals, "Rentals");
        add(rentalButtons, "Buttons");
        add(registerCar, "Car");
        add(registerStore, "Store");
        add(registerManager, "Manager");
        // TODO: Add all the panels to the CardLayout
    }

    void showRentalsPanel(ArrayList<String> result)
    {
        JPanel panel = this;
        ArrayList<JButton> rentalsList = new ArrayList<JButton>();
        for (int k = 1; k < result.size(); k += 4)
        {
            JButton rentalButton = new JButton(result.get(k+1));
            rentalsList.add(rentalButton);
        }
        rentalButtons.setLayout(new GridLayout(rentalsList.size() + 1, 1));
        JButton done = new JButton("Volver");
        done.addActionListener(e ->
        {
            CardLayout cardLayout = (CardLayout) panel.getLayout();
            cardLayout.show(panel, "Rentals");
        });
        rentalButtons.add(done);
        for (int i = 0; i < rentalsList.size(); i++)
        {
            JButton currentButton = rentalsList.get(i);
            ArrayList<String> information = new ArrayList<String>();
            information.add(result.get(1 + i * 4));
            information.add(result.get(2 + i * 4));
            information.add(result.get(3 + i * 4));
            information.add(result.get(4 + i * 4));
            currentButton.addActionListener(e ->
            {
                new PastRental(information, panel);
            });
            rentalButtons.add(currentButton);
        }
    }

    void showMain()
    {
        CardLayout cardLayout = (CardLayout) getLayout();
        cardLayout.show(this, "Main");
    }
}
