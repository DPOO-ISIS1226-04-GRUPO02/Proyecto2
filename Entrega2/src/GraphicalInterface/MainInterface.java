package GraphicalInterface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MainInterface extends JFrame {

    UserSelection userSelection;
    ClientView clientView;
    EmployeeView employeeView;
    LManagerView lManagerView;
    GManagerView gManagerView;

    MainInterface() {

        setTitle("Galetto Cars");
        setSize(1000, 1200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setResizable(false);
		setLocationRelativeTo(null);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                // TODO: Add operation for closing if needed
            }
        });

        this.userSelection = new UserSelection();
        this.clientView = new ClientView();
        this.employeeView = new EmployeeView();
        this.lManagerView = new LManagerView();
        this.gManagerView = new GManagerView();

        



    }

}