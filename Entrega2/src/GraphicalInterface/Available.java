package GraphicalInterface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Available extends JPanel {
    private JComboBox<String> comboMes;
    private JComboBox<String> comboSede;
    private JPanel panelCalendario;
    public Available(JPanel parentPanel, GManagerView gManagerView) {
        setLayout(new FlowLayout());

        JLabel etiquetaMes = new JLabel("Seleccione el mes:");
        comboMes = new JComboBox<>(new String[]{"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio",
                "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"});
        JLabel etiquetaSede = new JLabel("Seleccione una sede:");
        comboSede = new JComboBox<>(new String[]{"Galetto Cars Andino", "Galetto Cars Chapinero", "Galetto Cars Engativá", "Galetto Cars Fontibón", "Galetto Cars Kennedy", "Galetto Cars Santa Fe", "Galetto Cars Suba", "Galtto Cars Teusaquillo", "Galetto Cars Usaquén"});
        JButton btnMostrarCalendario = new JButton("Mostrar Calendario");
        panelCalendario = new JPanel(new GridLayout(0, 7, 10, 10));

        add(etiquetaSede);
        add(comboSede);
        add(etiquetaMes);
        add(comboMes);
        add(btnMostrarCalendario);
        add(panelCalendario);

        btnMostrarCalendario.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarCalendario();
            }
        });

        JButton volverButton = new JButton("Volver");
        volverButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CardLayout cardLayout = (CardLayout) parentPanel.getLayout();
                cardLayout.show(parentPanel, "Main");
            }
        });
        add(volverButton);
    }

    private void mostrarCalendario() {
        int mesSeleccionado = comboMes.getSelectedIndex() + 1;

        Calendar calendario = new GregorianCalendar(2023, mesSeleccionado - 1, 1);
        int diasEnMes = calendario.getActualMaximum(Calendar.DAY_OF_MONTH);

        panelCalendario.removeAll();

        for (int dia = 1; dia <= diasEnMes; dia++) {
            JButton btnDia = new JButton(String.valueOf(dia));
            btnDia.setPreferredSize(new Dimension(80, 60));
            btnDia.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
            btnDia.setBackground(new Color(144, 238, 144));
            panelCalendario.add(btnDia);
        }

        revalidate();
        repaint();
    }

    
}