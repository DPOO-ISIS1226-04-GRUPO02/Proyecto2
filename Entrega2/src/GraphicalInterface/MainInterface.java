package GraphicalInterface;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;

import javax.imageio.ImageIO;
import javax.swing.*;

import Processing.CarRental;

import java.awt.*;
import java.awt.image.BufferedImage;

public class MainInterface extends JFrame {

    private ClientView clientView;
    private EmployeeView employeeView;
    private LManagerView lManagerView;
    private GManagerView gManagerView;

    final private String imgPath = "/Entrega2/data/logo.jpeg";
    private BufferedImage logo;

    MainInterface()
    {
        setTitle("Galetto Cars");
        setSize(1000, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setResizable(false);
		setLocationRelativeTo(null);
        try
        {
            logo = ImageIO.read(new File(new File("").getAbsolutePath() + imgPath));
        } 
        catch (IOException ex)
        {
            new ErrorDialog("La imagen del logo no fue encontrada!", this);
            logo = null;
        }
        JPanel logoPanel = new JPanel() {
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
        
                if (logo != null) {
                    int x = (getWidth() - logo.getWidth()) / 2;
                    int y = (getHeight() - logo.getHeight()) / 7;
        
                    g.drawImage(logo, x, y, this);
                }
            }
        };
        
        add(logoPanel, BorderLayout.CENTER);
    }

    void loadView(int access, String username, String password)
    {
        try
        {
            CarRental.loadCarRental();
            switch (access)
        {
            case 0:
                clientView = new ClientView(username, this);
                add(clientView, BorderLayout.NORTH);
                setVisible(true);
                break;
            case 1:
                employeeView = new EmployeeView(username, password, this);
                add(employeeView, BorderLayout.NORTH);
                setVisible(true);
                break;
            case 2:
                lManagerView = new LManagerView(username, password, this);
                add(lManagerView, BorderLayout.NORTH);
                setVisible(true);
                break;
            case 3:
                gManagerView = new GManagerView(username, password, this);
                add(gManagerView, BorderLayout.NORTH);
                setVisible(true);
                break;
            default:
                new ErrorDialog("No se encontró el nivel de acceso del usuario", this);
                setVisible(true);
                break;
        }
        }
        catch (IOException | ParseException ex)
        {
            new ErrorDialog("Hubo un error cargando los datos!\n" + ex.getLocalizedMessage(), this);
        }
        
    }

}