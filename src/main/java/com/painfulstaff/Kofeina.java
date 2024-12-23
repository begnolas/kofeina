package com.painfulstaff;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;

import javax.swing.UnsupportedLookAndFeelException;

public class Kofeina {

    static Thread supervisor;
    static Logic one;

    public static void main(String[] args) throws AWTException, ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {

        Gui gui = new Gui();
        

        one = new Logic();
        one.start();




        supervisor = new Thread() {
            @Override
            public void run() {
                try {
                    System.out.println("Supervisor: Start.");

                    Thread.State oldState = one.getState();

                    while (true) {
                        Thread.State newState = one.getState();

                        if (oldState != newState) {
                            System.out.println("Supervisor: one status changed from " + oldState + " to: " + newState);
                        }

                        oldState = newState;
                        Thread.sleep(1000);

                    }

                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

            }
        };
        supervisor.start();


    }

 

    static void LockScreen() throws AWTException {
        Robot robot = new Robot();

        robot.keyPress(KeyEvent.VK_WINDOWS + KeyEvent.VK_L);
        robot.delay(200);
        //robot.keyPress(KeyEvent.VK_L);

        robot.keyRelease(KeyEvent.VK_L);
        robot.keyRelease(KeyEvent.VK_WINDOWS);
    }
}
