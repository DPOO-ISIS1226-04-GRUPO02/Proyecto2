package GraphicalInterface;

import java.util.ArrayList;

import javax.swing.*;

public class PastRental extends JOptionPane
{
    PastRental(ArrayList<String> info, JPanel parent)
    {
        String rentalNumber = info.get(0);
        String startDate = info.get(1);
        String endDate = info.get(2);
        String totalCost = info.get(3);

        String message = "";
        message += "Renta #" + rentalNumber + "\n";
        message += "Fecha de inicio: " + startDate + "\n";
        message += "Fecha de finalización: " + endDate + "\n";
        message += "Costo total de la renta: " + totalCost + "\n";

        showMessageDialog(parent, message, startDate, JOptionPane.PLAIN_MESSAGE);
    }
}
