package GraphicalInterface;

import javax.swing.*;

import java.awt.*;

public class ChangeInsurance extends JPanel
{
    ChangeInsurance(GManagerView gManager, JFrame main)
    {
        ChangeInsurance panel = this;
        setLayout(new CardLayout());
        JPanel mainCard = new JPanel();
        mainCard.setLayout(new FlowLayout());

        RegisterInsurance registerInsurance = new RegisterInsurance(this, main);
        ToggleInsuranceStatus toggleInsuranceStatus = new ToggleInsuranceStatus(this, main);

        JButton registerInsuranceButton = new JButton("Registrar nuevo seguro");
        JButton toggleInsuranceStatusButton = new JButton("Cambiar estado de un seguro");
        JButton back = new JButton("Atrás");

        registerInsuranceButton.addActionListener(e ->
        {
            CardLayout cardLayout = (CardLayout) panel.getLayout();
            cardLayout.show(panel, "Register");
        });
        toggleInsuranceStatusButton.addActionListener(e ->
        {
            CardLayout cardLayout = (CardLayout) panel.getLayout();
            cardLayout.show(panel, "Toggle");
        });
        back.addActionListener(e ->
        {
            gManager.showMain();
        });

        mainCard.add(registerInsuranceButton);
        mainCard.add(toggleInsuranceStatusButton);
        mainCard.add(back);

        add(mainCard, "Main");
        add(registerInsurance, "Register");
        add(toggleInsuranceStatus, "Toggle");


    }


    void showMain()
    {
        CardLayout cardLayout = (CardLayout) getLayout();
        cardLayout.show(this, "Main");
    }

}
