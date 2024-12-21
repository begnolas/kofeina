package com.painfulstaff;


import java.awt.AWTException;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.PointerInfo;
import java.awt.PopupMenu;
import java.awt.Robot;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.net.URL;
import java.util.concurrent.ThreadLocalRandom;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPopupMenu;
import javax.swing.JTextField;



public class Kofeina {
    static Thread one, supervisor;

    public static void Main(String[] args) throws AWTException {

        System.out.println("Started.");
        createAndShowGUI();

        //createAndShowJGUI(tray);

        System.out.println("End.");
    }



    private static void createAndShowJGUI(SystemTray tray) throws AWTException {  
        
        final TrayIcon trayIcon = new TrayIcon(createImage("bulb.gif", "tray icon"));
        final JPopupMenu jPopupMenu = new JPopupMenu("Prova");
        //trayIcon.setPopupMenu(jPopupMenu);
        tray.add(trayIcon);
    }






    private static void createAndShowGUI() throws AWTException {
        if (!SystemTray.isSupported()) {
            System.out.println("SystemTray is not supported");
            return;
        }

        final SystemTray tray = SystemTray.getSystemTray();
        final TrayIcon trayIcon = new TrayIcon(createImage("/bulb.gif","Kofeina" ));





        final PopupMenu popup = new PopupMenu();


        // Create a popup menu components
        MenuItem startItem = new MenuItem("Start");
        popup.add(startItem);


        // 




        MenuItem stopItem = new MenuItem("Stop");
        popup.add(stopItem);

        MenuItem exitItem = new MenuItem("Exit");
        popup.add(exitItem);

        trayIcon.setPopupMenu(popup);
        tray.add(trayIcon);



        
        // ActionListener
        exitItem.addActionListener((ActionEvent e) -> {
            tray.remove(trayIcon);
            System.exit(0);
        });



        startItem.addActionListener((ActionEvent e) -> {
            OpenJFrame();
        });




        stopItem.addActionListener((ActionEvent e) -> {
            
            try {
                LockScreen();
            } catch (AWTException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        });
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


    static void OpenJFrame () {

        JFrame a = new JFrame("Kofeina 🐢");
        JTextField b = new JTextField("suka");

        b.setBounds(50, 100, 200, 30);
        a.add(b);
        a.setSize(300, 300);
        a.setLayout(null);
        a.setVisible(true);

    }


    static void LockScreen() throws AWTException {
        Robot robot=new Robot();

        robot.keyPress(KeyEvent.VK_WINDOWS);
        robot.keyPress(KeyEvent.VK_L);
        robot.delay(200);

        robot.keyRelease(KeyEvent.VK_L);
        robot.keyRelease(KeyEvent.VK_WINDOWS);
    }







    static void logic() {

		int move_steps1 = 0;
		int move_steps2 = 0;
		int oldx = 0;
		int oldy = 0;
		int counter = 0;
		int counterP = 30;
		PointerInfo a;
        
		Robot hal;

		while (true) {

			try {
				hal = new Robot();

				a = MouseInfo.getPointerInfo();

				Point b = a.getLocation();

				int x = (int) b.getX();
				int y = (int) b.getY();

				if ((Math.abs(x - oldx) < 10) && (Math.abs(y - oldy) < 10)) {
					counter++;
				} else {
					counter = 0;
				}

				if (counter > counterP) {
					move_steps1 = ThreadLocalRandom.current().nextInt(-9, 9 + 1);
					move_steps2 = ThreadLocalRandom.current().nextInt(-9, 9 + 1);

					hal.mouseMove((x + move_steps1), (y + move_steps2));
				}

				oldx = x;
				oldy = y;

				// System.out.print("*");

			} catch (AWTException | HeadlessException | NullPointerException e) {
                e.printStackTrace();
			}



			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
