package GraphicalInterface;

import java.awt.CardLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class ClientView extends JPanel {

    private ChangePersonalInformation personal;
    private RegisterPersonalInformation register;
    private ReserveCar reserve;

    ClientView(String login, JFrame main) 
    {
        JPanel panel = this;
        personal = new ChangePersonalInformation(login, this);
        register = new RegisterPersonalInformation(login, main, this);
        reserve = new ReserveCar(login, main, this);

        setLayout(new CardLayout());
        JPanel mainCard = new JPanel();
        JButton RegisterInfoButton = new JButton("Registrar Información Personal");
        JButton personalInfoButton = new JButton("Cambiar Información Personal");
        JButton reserveCarButton = new JButton("Reservar un Carro");

        mainCard.setLayout(new FlowLayout());
        mainCard.add(RegisterInfoButton);
        mainCard.add(personalInfoButton);
        mainCard.add(reserveCarButton);

        RegisterInfoButton.addActionListener(new ActionListener() 
        {
            public void actionPerformed(ActionEvent e)
            {
                CardLayout cardLayout = (CardLayout) panel.getLayout();
                cardLayout.show(panel, "Register");
            }
        });

        personalInfoButton.addActionListener(new ActionListener() 
        {
            public void actionPerformed(ActionEvent e)
            {
                CardLayout cardLayout = (CardLayout) panel.getLayout();
                cardLayout.show(panel, "Personal");
            }
        });

        reserveCarButton.addActionListener(new ActionListener() 
        {
            public void actionPerformed(ActionEvent e)
            {
                CardLayout cardLayout = (CardLayout) panel.getLayout();
                cardLayout.show(panel, "Reserve");
            }
        });

        add(mainCard, "Main");
        add(register, "Register");
        add(personal, "Personal");
        add(reserve, "Reserve");

    }

    void showMain() {
        CardLayout cardLayout = (CardLayout) getLayout();
        cardLayout.show(this, "Main");
    }
    
}
