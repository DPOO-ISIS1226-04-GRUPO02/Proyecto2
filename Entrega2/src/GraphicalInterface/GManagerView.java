package GraphicalInterface;

import java.awt.CardLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;

public class GManagerView extends JPanel {
    private JPanel rentalButtons = new JPanel();

    GManagerView(String login, String password, JFrame main) {
        JPanel panel = this;
        GetPastRentals rentals = new GetPastRentals(main, this);
        RegisterCar registerCar = new RegisterCar(this, main);
        RegisterStore registerStore = new RegisterStore(this, main);
        RegisterManager registerManager = new RegisterManager(this, main);

        setLayout(new CardLayout());
        JPanel mainCard = new JPanel();
        JButton rentalsButton = new JButton("Ver historial de rentas de un carro");
        JButton registerCarButton = new JButton("Registrar un nuevo carro");
        JButton registerStoreButton = new JButton("Registrar una nueva sede");
        JButton registerManagerButton = new JButton("Registrar un nuevo gerente local");

        mainCard.setLayout(new FlowLayout());
        mainCard.add(rentalsButton);
        mainCard.add(registerCarButton);
        mainCard.add(registerStoreButton);
        mainCard.add(registerManagerButton);

        rentalsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                CardLayout cardLayout = (CardLayout) panel.getLayout();
                cardLayout.show(panel, "Rentals");
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

        add(mainCard, "Main");
        add(rentals, "Rentals");
        add(rentalButtons, "Buttons");
        add(registerCar, "Car");
        add(registerStore, "Store");
        add(registerManager, "Manager");
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

        // Configurar el layout del panel de botones
        rentalsPanel.setLayout(new GridLayout(rentalsList.size() + 1, 1));

        // Añadir el botón "Volver"
        JButton done = new JButton("Volver");
        done.addActionListener(e -> rentalsFrame.dispose()); // Cierra el nuevo frame al hacer clic en "Volver"
        rentalsPanel.add(done);

        // Añadir los botones de rentas
        for (int i = 0; i < rentalsList.size(); i++) {
            JButton currentButton = rentalsList.get(i);

            // ... Lógica para obtener la información del alquiler ...

            currentButton.addActionListener(e -> {
                // ... Lógica para mostrar la información del alquiler en un nuevo panel o frame ...
            });

            rentalsPanel.add(currentButton);
        }

        // Añadir el panel de botones al nuevo frame
        rentalsFrame.add(rentalsPanel);

        // Configurar tamaño, visibilidad y otras propiedades del nuevo frame
        rentalsFrame.setSize(400, 300); // Ajusta el tamaño según tus necesidades
        rentalsFrame.setLocationRelativeTo(null); // Centra el frame en la pantalla
        rentalsFrame.setVisible(true);
    }

    void showMain() {
        CardLayout cardLayout = (CardLayout) getLayout();
        cardLayout.show(this, "Main");
    }
}

