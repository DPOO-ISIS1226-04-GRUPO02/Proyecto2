package GraphicalInterface;

import javax.swing.*;

import Model.Car;
import Model.Store;
import Processing.CarRental;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;

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

    public void actualizarInfo(String sede, int mes, String ruta) throws IOException, ParseException{
        CarRental.loadCarRental();
        Store store = CarRental.getStore(sede);
        Collection<ArrayList<String>> listValues= store.getInventory().values();
        ArrayList<String> lista = new ArrayList<>();
        for (ArrayList<String> l : listValues){
            lista.addAll(l);
        }
        int disponibles = 0;
        for (String placa: lista){
            Car car = CarRental.getCar(placa);
            Byte status = car.getStatus();
            Byte cmp = 0;
            if (status == cmp){
                disponibles += 1;
            }
        }
        LocalDate fechaHoy = LocalDate.now();
        int numeroDia = fechaHoy.getDayOfMonth();
        int numeroMes = fechaHoy.getMonthValue();

        if (mes >= numeroMes){
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(ruta));
            StringBuilder contenidoModificado = new StringBuilder();
            String linea;

            int numeroLinea = 1;

            while ((linea = bufferedReader.readLine())!= null){
                if (numeroLinea == mes ){
                    String[] valores = linea.split(";");
                    if (numeroDia <= valores.length){
                        String d = "" + disponibles;
                        for (int i = numeroDia - 1; i < valores.length; i++){
                            valores[i] = d;
                            linea = String.join(";", valores);
                        }
                    }
                }
                else if (numeroLinea >= mes){
                   String[] valores = linea.split(";"); 
                   String d = "" + disponibles;
                        for (int i = 0; i < valores.length; i++){
                            valores[i] = d;
                            linea = String.join(";", valores);
                        }
                }
                contenidoModificado.append(linea).append("\n");
                numeroLinea++;
            }
            bufferedReader.close();

            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(ruta));
            bufferedWriter.write(contenidoModificado.toString());
            bufferedWriter.close();
            
        } catch (IOException e){
            e.printStackTrace();
        }}


    }

    private void mostrarCalendario() {
        int mesSeleccionado = comboMes.getSelectedIndex() + 1;
        String sedeSeleccionada = (String) comboSede.getSelectedItem();
        String nombreArchivo = "Entrega2/data/Calendario/" + sedeSeleccionada + "-Calendario.txt";
        try {
            actualizarInfo(sedeSeleccionada, mesSeleccionado, nombreArchivo);
        } catch (IOException | ParseException e) {
            
            e.printStackTrace();
        }
        try (BufferedReader br = new BufferedReader(new FileReader(nombreArchivo))) {
            panelCalendario.removeAll();

            // Leer hasta la línea correspondiente al mes seleccionado
            for (int i = 1; i < mesSeleccionado; i++) {
                br.readLine();
            }

            // Leer la línea correspondiente al mes seleccionado
            String linea = br.readLine();

            // Procesar los valores de la línea
            if (linea != null) {
                String[] valores = linea.split(";");
                for (int dia = 1; dia <= valores.length; dia++) {
                    JButton btnDia = new JButton(String.valueOf(dia));
                    btnDia.setPreferredSize(new Dimension(80, 60));
                    btnDia.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));

                    int valorDia = Integer.parseInt(valores[dia - 1].trim());
                    Color color = obtenerColor(valorDia);

                    btnDia.setBackground(color);
                    panelCalendario.add(btnDia);
                }
            }

            revalidate();
            repaint();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Color obtenerColor(int valorDia) {
        int verdeBase = 144;
        int diferencia = 10 - valorDia;
        int verde = verdeBase + diferencia * 10;
        return new Color(0, verde, 0);
    }
}

