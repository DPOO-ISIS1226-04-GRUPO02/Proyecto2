package GraphicalInterface;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Set;

import javax.swing.*;

import Processing.CarRental;
import Processing.Users;

public class PickUp extends JPanel {
    PickUp(EmployeeView employee, String loginEmployee, String passwordEmployee, JFrame main) {
        String workplace = Users.loadUser(loginEmployee, passwordEmployee).getWorkplace();

        JLabel clientUserLabel = new JLabel("Ingrese el nombre de usuario del cliente: ");
        JTextField clientUser = new JTextField(20);
        JButton accept = new JButton("Aceptar");
        JButton back = new JButton("Atrás");
        JButton insuranceButton = new JButton("Seguros");

        setLayout(new FlowLayout());
        setSize(getPreferredSize());

        accept.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                boolean correct = CarRental.confirmPickUp(clientUser.getText(), workplace);
                if (!correct)
                    new ErrorDialog(
                            "No se ha podido confirmar la recogida. Revise que el cliente tenga una renta activa y sea este el orígen de dicha renta.",
                            main);
                else
                    employee.showMain();
            }
        });

        back.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                employee.showMain();
            }
        });

        insuranceButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Abre un nuevo JFrame para la selección de seguros
                JFrame insuranceFrame = new JFrame("Selección de Seguros");
                insuranceFrame.setSize(300, 200);
                insuranceFrame.setLayout(new FlowLayout());

                // Agrega JCheckBox para los seguros
                Set<String> seguros = CarRental.getInsurances();
                ArrayList<JCheckBox> arreglo = new ArrayList<>();
                for (String seguro : seguros){
                    JCheckBox insurance = new JCheckBox(seguro);
                    arreglo.add(insurance);
                    insuranceFrame.add(insurance);
                }

                JButton confirmInsuranceButton = new JButton("Confirmar");
                confirmInsuranceButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        
                        for (JCheckBox ins : arreglo){
                            if (ins.isSelected()){
                                
                                CarRental.getClient(clientUser.getText()).getActiveRental().getInsurances().add(CarRental.getInsurance(ins.getText()));
                            }
                        }

                        // Puedes hacer algo con la selección de seguros, como almacenarla en la base de datos
                        // o realizar otras acciones según tus necesidades.

                        // Cierra la ventana de selección de seguros
                        insuranceFrame.dispose();
                    }
                });

                
                insuranceFrame.add(confirmInsuranceButton);

                insuranceFrame.setVisible(true);
            }
        });

        add(clientUserLabel);
        add(clientUser);
        add(accept);
        add(back);
        add(insuranceButton);
        setVisible(true);
    }
}

