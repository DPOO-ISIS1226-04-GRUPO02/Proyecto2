package GraphicalInterface;

import java.awt.CardLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class ChangeCategory extends JPanel {

    ChangeCategory(GManagerView gManager, JFrame main)
    {
        ChangeCategory panel = this;
        setLayout(new CardLayout());
        JPanel mainCard = new JPanel();
        mainCard.setLayout(new FlowLayout());

        RegisterCategory registerCategory = new RegisterCategory(this, main);
        SetTariffCategory setTariffCategory = new SetTariffCategory(this, main);

        
        JButton registerCategoryButton = new JButton("Registrar nueva categoria");
        JButton setTariffCategoryButton = new JButton("Cambai rprecio categoria");

        JButton back = new JButton("Atrás");

        registerCategoryButton.addActionListener(e ->
        {
            CardLayout cardLayout = (CardLayout) panel.getLayout();
            cardLayout.show(panel, "Register");
        });
        setTariffCategoryButton.addActionListener(e ->
        {
            CardLayout cardLayout = (CardLayout) panel.getLayout();
            cardLayout.show(panel, "Tariff");
        });
        back.addActionListener(e ->
        {
            gManager.showMain();
        });

        mainCard.add(registerCategoryButton);
        mainCard.add(setTariffCategoryButton);
        mainCard.add(back);

        add(mainCard, "Main");
        add(registerCategory, "Register");
        add(setTariffCategory, "Tariff");

    }
    
    void showMain()
    {
        CardLayout cardLayout = (CardLayout) getLayout();
        cardLayout.show(this, "Main");
    }
}
