package GraphicalInterface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MainInterface extends JFrame {

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

    }

    void loadView(int access, String username, String password) {

        switch (access) {
            case 0:


        }

    }

}