package com.painfulstaff;

import java.awt.AWTException;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JTextField;

public class Gui {

    private SystemTray tray;
    private TrayIcon trayIcon;
    private PopupMenu popup;



    private JFrame frame;
	private JMenuBar menuBar;
	private JPopupMenu fileMenu;
	private JMenuItem newMenuItem;
	private MenuItem saveMenuItem;
	private JMenuItem exitMenuItem;



    public Gui() throws AWTException {

        String os = System.getProperty("os.name");
        System.out.println(os);

        if (!SystemTray.isSupported()) {
            System.out.println("SystemTray is not supported");
            //return;
        }

        this.tray = SystemTray.getSystemTray();
        this.trayIcon = new TrayIcon(createImage("/bulb.gif", "Kofeina"));
        
        
        this.popup = new PopupMenu();


        trayIcon.setPopupMenu(popup);
        tray.add(trayIcon);



        // Create a popup menu components
        MenuItem startItem = new MenuItem("Start");
        popup.add(startItem);

        MenuItem stopItem = new MenuItem("Stop");
        popup.add(stopItem);

        MenuItem exitItem = new MenuItem("Exit");
        popup.add(exitItem);

        MenuItem killLogicItem = new MenuItem("Pause");
        popup.add(killLogicItem);






        
        // ActionListener
        exitItem.addActionListener((ActionEvent e) -> {
            tray.remove(trayIcon);
            System.exit(0);
        });



        killLogicItem.addActionListener((ActionEvent e) -> {
            
           

        });



        startItem.addActionListener((ActionEvent e) -> {
            OpenJFrame();
        });


        stopItem.addActionListener((ActionEvent e) -> {
            
            System.out.println("Stop");
        });










 
        fileMenu = new JPopupMenu();


		
		newMenuItem = new JMenuItem("New...");
		
        ImageIcon icon = new ImageIcon("images/new.gif");
		newMenuItem.setIcon(icon);
		newMenuItem.setIconTextGap(10);

        
		saveMenuItem = new JMenuItem("Save");
		saveMenuItem.setIconTextGap(10);

		
		exitMenuItem = new JMenuItem("Exit");
		exitMenuItem.setIconTextGap(10);

		
		popup.add(newMenuItem);
		popup.add(saveMenuItem);
		
		fileMenu.add(new JRadioButtonMenuItem("RadioButton"));
		fileMenu.add(new JCheckBoxMenuItem("CheckBox"));
		fileMenu.add(new JMenu("Sub Menu"));
		
		fileMenu.addSeparator();
		
		fileMenu.add(exitMenuItem);
		
		menuBar.add(fileMenu);
		
		frame.setJMenuBar(menuBar);


















    }









    static void OpenJFrame () {

        JFrame a = new JFrame("Kofeina 🐢");
        JTextField b = new JTextField("suka");

        b.setBounds(50, 100, 200, 30);
        a.add(b);
        a.setSize(300, 300);
        a.setLayout(null);
        a.setVisible(true);

    }






    // Obtain the image URL
    protected static Image createImage(String path, String description) {

        URL imageURL = Kofeina.class.getResource(path);

        if (imageURL == null) {
            System.err.println("Resource not found: " + path);
            return null;
        } else {
            return (new ImageIcon(imageURL, description)).getImage();
        }

    }

}
