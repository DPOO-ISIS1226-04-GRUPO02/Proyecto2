package GraphicalInterface;
import javax.swing.*;
    import java.awt.*;
    import java.awt.event.ActionEvent;
    import java.awt.event.ActionListener;

public class ChangePersonalInformation extends JPanel {

    

    public ChangePersonalInformation()
    {
        JPanel changePersonalInfoPanel = new JPanel(new GridLayout(3,1));

        JButton cambiarInfoPersonalButton = new JButton("Cambiar Información Personal");
        cambiarInfoPersonalButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                //TODO definir que hacer, si hacer paneles aca dentro para cada uno
            JLabel labelOpciones = new JLabel("Información a cambiar:");

            String[] opciones = {"Nombre", "Celular", "E-mail", "Id Photo Path"}

             JComboBox opcionesComboBox = new JComboBox<>();
             
            }
            
        });



        JButton cambiarMetodoDePagoButton = new JButton("Cambiar Metodo de Pago");
        JButton cambiarLicenciaButton= new JButton("Cambiar Información Licencia");


    



        
        

    }
        
}
