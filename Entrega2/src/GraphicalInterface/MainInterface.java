package GraphicalInterface;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class MainInterface extends JFrame {

    private ClientView clientView;
    private EmployeeView employeeView;
    private LManagerView lManagerView;
    private GManagerView gManagerView;

    final private String separator = File.separator;
    final private String imgPath = separator + "data" + separator + "logo.jpeg";
    private BufferedImage logo;

    MainInterface() {

        setTitle("Galetto Cars");
        setSize(1000, 1200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setResizable(false);
		setLocationRelativeTo(null);
        
        try {
            logo = ImageIO.read(new File(new File("").getAbsolutePath() + imgPath));
        } catch (IOException ex) {
            new ErrorDialog("La imagen del logo no fue encontrada!", this);
            logo = null;
        }

        JPanel logoPanel = new JPanel() 
        {
            public void paint(Graphics g)
            {
                Graphics2D g2 = (Graphics2D) g;
                int w = getWidth() - 270;
                int h = getHeight() - 480;
                g2.drawImage(logo, w/2, h/2, 270, 480, new Color(0xFF, 0xC3, 0x00), null);
            }
        };

        add(logoPanel, BorderLayout.WEST);

    }

    void loadView(int access, String username, String password) {

        switch (access) {
            case 0:
                clientView = new ClientView(username, this);
                add(clientView, BorderLayout.CENTER);
                setVisible(true);
                break;
            case 1:
                employeeView = new EmployeeView(username, password, this);
                add(employeeView, BorderLayout.CENTER);
                setVisible(true);
                break;
            case 2:
                lManagerView = new LManagerView(username, password, this);
                add(lManagerView, BorderLayout.CENTER);
                setVisible(true);
                break;
            case 3:
                gManagerView = new GManagerView(username, password, this);
                add(gManagerView, BorderLayout.CENTER);
                setVisible(true);
                break;
            default:
                new ErrorDialog("No se encontró este acceso", this);
                setVisible(true);
                break;
        }

    }

}