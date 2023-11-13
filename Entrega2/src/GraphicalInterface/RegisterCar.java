package GraphicalInterface;

import java.awt.GridLayout;
import java.text.NumberFormat;
import javax.swing.*;

import Processing.CarRental;

public class RegisterCar extends JPanel
{
    RegisterCar(GManagerView gManager, JFrame main)
    {
        setLayout(new GridLayout(2, 9));

        String[] booleanValues = {"True", "False"};
        String[] categories = new String[CarRental.getCategories().size()];
        int i = 0;
        for (String category: CarRental.getCategories()) categories[i++] = category;
        NumberFormat integerFormat = NumberFormat.getIntegerInstance();
        integerFormat.setParseIntegerOnly(true);
        String[] stores = new String[CarRental.getStores().size()];
        i = 0;
        for (String store: CarRental.getStores()) stores[i++] = store;

        JLabel brandLabel = new JLabel("Ingrese la marca del carro: ");
        JTextField brandText = new JTextField(20);
        JLabel plateLabel = new JLabel("Ingrese la placa del carro: ");
        JTextField plateText = new JTextField("ABC123", 6);
        JLabel modelLabel = new JLabel("Ingrese el modelo del carro: ");
        JTextField modelText = new JTextField(20);
        JLabel colorLabel = new JLabel("Ingrese el color del carro: ");
        JTextField colorText = new JTextField(20);
        JLabel automaticLabel = new JLabel("¿Es automático este carro?");
        JComboBox<String> automaticOptions = new JComboBox<String>(booleanValues);
        JLabel categoryLabel = new JLabel("Seleccione la categoría del vehículo: ");
        JComboBox<String> categoryOptions = new JComboBox<String>(categories);
        JLabel availableLabel = new JLabel("Ingrese en cuantos días se encontrará disponible el carro: ");
        JFormattedTextField availableText = new JFormattedTextField(integerFormat);
        availableText.setColumns(20);
        JLabel storeLabel = new JLabel("Seleccione la tienda a donde desea registrar el carro: ");
        JComboBox<String> storeOptions = new JComboBox<String>(stores);
        JButton accept = new JButton("Aceptar");
        JButton back = new JButton("Atrás");

        accept.addActionListener(e -> 
        {
            String brand = brandText.getText();
            String plate = plateText.getText();
            String model = modelText.getText();
            String color = colorText.getText();
            boolean isAutomatic = Boolean.parseBoolean((String) automaticOptions.getSelectedItem());
            String category = (String) categoryOptions.getSelectedItem();
            long availableInLong = (long) availableText.getValue();
            int availableIn = (int) availableInLong;

            String store = (String) storeOptions.getSelectedItem();

            CarRental.registerCar(brand, plate, model, color, isAutomatic, category, availableIn, store);
            gManager.showMain();
        });
        back.addActionListener(e ->
        {
            gManager.showMain();
        });

        add(brandLabel);
        add(brandText);
        add(plateLabel);
        add(plateText);
        add(modelLabel);
        add(modelText);
        add(colorLabel);
        add(colorText);
        add(automaticLabel);
        add(automaticOptions);
        add(categoryLabel);
        add(categoryOptions);
        add(availableLabel);
        add(availableText);
        add(storeLabel);
        add(storeOptions);
        add(accept);
        add(back);
    }
}
