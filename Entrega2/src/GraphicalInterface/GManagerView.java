package GraphicalInterface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class GManagerView extends JPanel {
    private JPanel rentalButtons = new JPanel();

    public GManagerView(String login, String password, JFrame main) {
        JPanel panel = this;
        GetPastRentals rentals = new GetPastRentals(main, this);
        RegisterCar registerCar = new RegisterCar(this, main);
        RegisterStore registerStore = new RegisterStore(this, main);
        RegisterManager registerManager = new RegisterManager(this, main);
        ChangeInsurance registerInsurance = new ChangeInsurance(this, main);
        ChangeCategory registerCategory = new ChangeCategory(this, main);
        Available available = new Available(panel, this);

        setLayout(new CardLayout());
        JPanel mainCard = new JPanel();
        JButton rentalsButton = new JButton("Ver historial de rentas de un carro");
        JButton registerCarButton = new JButton("Registrar un nuevo carro");
        JButton registerStoreButton = new JButton("Registrar una nueva sede");
        JButton registerManagerButton = new JButton("Registrar un nuevo gerente local");
        JButton registerInsuranceButton = new JButton("Registrar un nuevo seguro");
        JButton registerCategoryButton = new JButton("Registrar nueva categoría");
        JButton availableButton = new JButton("Consultar vehículos disponibles por mes y sede");

        mainCard.setLayout(new FlowLayout());
        mainCard.add(rentalsButton);
        mainCard.add(registerCarButton);
        mainCard.add(registerStoreButton);
        mainCard.add(registerManagerButton);
        mainCard.add(registerInsuranceButton);
        mainCard.add(registerCategoryButton);
        mainCard.add(availableButton);

        rentalsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                CardLayout cardLayout = (CardLayout) panel.getLayout();
                cardLayout.show(panel, "Rentals");
            }
        });

        availableButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                CardLayout cardLayout = (CardLayout) panel.getLayout();
                cardLayout.show(panel, "Available");
            }
        });

        registerCarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                CardLayout cardLayout = (CardLayout) panel.getLayout();
                cardLayout.show(panel, "Car");
            }
        });

        registerStoreButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                CardLayout cardLayout = (CardLayout) panel.getLayout();
                cardLayout.show(panel, "Store");
            }
        });

        registerManagerButton.addActionListener(e -> {
            CardLayout cardLayout = (CardLayout) panel.getLayout();
            cardLayout.show(panel, "Manager");
        });

        registerInsuranceButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                CardLayout cardLayout = (CardLayout) panel.getLayout();
                cardLayout.show(panel, "Insurance");
            }
        });
        registerCategoryButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                CardLayout cardLayout = (CardLayout) panel.getLayout();
                cardLayout.show(panel, "Category");
            }
        });

        add(mainCard, "Main");
        add(rentals, "Rentals");
        add(rentalButtons, "Buttons");
        add(registerCar, "Car");
        add(registerStore, "Store");
        add(registerManager, "Manager");
        add(registerInsurance, "Insurance");
        add(registerCategory, "Category");
        add(available, "Available");
    }

    void showRentalsPanel(ArrayList<String> result) {
        // Crear un nuevo JFrame para mostrar las rentas
        JFrame rentalsFrame = new JFrame("Historial de Rentas");
        rentalsFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Crear un JPanel para contener los botones y otros componentes
        JPanel rentalsPanel = new JPanel();

        ArrayList<JButton> rentalsList = new ArrayList<>();
        for (int k = 1; k < result.size(); k += 4) {
            JButton rentalButton = new JButton(result.get(k + 1));
            rentalsList.add(rentalButton);
        }

        rentalsPanel.setLayout(new GridLayout(rentalsList.size() + 1, 1));

        JButton done = new JButton("Volver");
        done.addActionListener(e -> rentalsFrame.dispose());
        rentalsPanel.add(done);

        for (int i = 0; i < rentalsList.size(); i++) {
            JButton currentButton = rentalsList.get(i);
            currentButton.addActionListener(e -> {
                // Lógica para mostrar la información del alquiler en un nuevo panel o frame
            });
            rentalsPanel.add(currentButton);
        }

        rentalsFrame.add(rentalsPanel);
        rentalsFrame.setSize(400, 300);
        rentalsFrame.setLocationRelativeTo(null);
        rentalsFrame.setVisible(true);
    }

    void showMain() {
        CardLayout cardLayout = (CardLayout) getLayout();
        cardLayout.show(this, "Main");
    }
}

