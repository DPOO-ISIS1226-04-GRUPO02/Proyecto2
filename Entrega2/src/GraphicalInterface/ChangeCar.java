package GraphicalInterface;

import javax.swing.*;

import java.awt.*;

public class ChangeCar extends JPanel
{
    ChangeCar(GManagerView gManager, JFrame main)
    {
        ChangeCar panel = this;
        setLayout(new CardLayout());
        JPanel mainCard = new JPanel();
        mainCard.setLayout(new FlowLayout());

        SendCar sendCar = new SendCar(this, main);
        ToggleCarStatus toggleCarStatus = new ToggleCarStatus(this, main);

        JButton sendCarButton = new JButton("Cambiar vehículo de sede");
        JButton toggleButton = new JButton("Cambiar estado de un vehículo");
        JButton back = new JButton("Atrás");

        sendCarButton.addActionListener(e ->
        {
            CardLayout cardLayout = (CardLayout) panel.getLayout();
            cardLayout.show(panel, "Send");
        });
        toggleButton.addActionListener(e ->
        {
            CardLayout cardLayout = (CardLayout) panel.getLayout();
            cardLayout.show(panel, "Toggle");
        });
        back.addActionListener(e ->
        {
            gManager.showMain();
        });

        mainCard.add(sendCarButton);
        mainCard.add(toggleButton);
        mainCard.add(back);

        add(mainCard, "Main");
        add(sendCar, "Send");
        add(toggleCarStatus, "Toggle");
    }

    void showMain()
    {
        CardLayout cardLayout = (CardLayout) getLayout();
        cardLayout.show(this, "Main");
    }
}
