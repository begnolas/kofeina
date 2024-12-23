package com.painfulstaff;

import java.awt.AWTException;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.PointerInfo;
import java.awt.Robot;
import java.util.concurrent.ThreadLocalRandom;

public class Logic extends Thread {

    @Override
    public void run() {
        System.out.println("One: start.");
        logic();
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
            } catch (AWTException e) {
                e.printStackTrace();
            }

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {

                System.err.println("InterruptedException");

                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

}
