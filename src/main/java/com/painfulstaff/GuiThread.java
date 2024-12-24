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
import javax.swing.JFrame;
import javax.swing.JTextField;

public class GuiThread extends Thread {

    private String os;

    private SystemTray tray;
    private TrayIcon trayIcon;
    private PopupMenu popup;
    private MenuItem exitItem, startLogicItem, installItem, pauseLogicItem;

    private LogicThread logicThread;

    public GuiThread(LogicThread one) {
        logicThread = one;
    }

    @Override
    public void run() {
        String os = System.getProperty("os.name");
        System.out.println(os);

        this.tray = SystemTray.getSystemTray();
        this.trayIcon = new TrayIcon(createImage("/bulb.gif", "Kofeina"));

        this.popup = new PopupMenu();

        trayIcon.setPopupMenu(popup);
        try {
            tray.add(trayIcon);
        } catch (AWTException ex) {
        }

        popup.setLabel("__ Kofeina __");

        // Create a popup menu components
        startLogicItem = new MenuItem("Start logic");
        pauseLogicItem = new MenuItem("Pause logic");
        exitItem = new MenuItem("Exit");
        popup.add(pauseLogicItem);
        popup.add(startLogicItem);
        popup.addSeparator();
        popup.add(exitItem);

        UpdateMenu();

        // ActionListener
        startLogicItem.addActionListener((ActionEvent e) -> {
            logicThread.resumeLogic();
            UpdateMenu();
        });

        pauseLogicItem.addActionListener((ActionEvent e) -> {
            logicThread.pauseLogic();
            UpdateMenu();

        });

        exitItem.addActionListener((ActionEvent e) -> {
            System.exit(0);
        });
    }

    static void OpenJFrame() {

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

    public void UpdateMenu() {

        startLogicItem.setEnabled(!logicThread.getRunning().get());
        pauseLogicItem.setEnabled(logicThread.getRunning().get());
    }

}
