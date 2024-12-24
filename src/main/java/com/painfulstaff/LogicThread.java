package com.painfulstaff;

import java.awt.AWTException;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.PointerInfo;
import java.awt.Robot;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicBoolean;

public class LogicThread extends Thread {

    private final AtomicBoolean running = new AtomicBoolean(false);

    public void pauseLogic() {
        running.set(false);
    }

    public void resumeLogic() {
        running.set(true);
    }




    @Override
    public void run() {
        if (!running.get()) {
            running.set(true);
            System.out.println("One: start.");
            logic();
            System.out.println("One: end.");
        }
    }

    void logic() {
        int move_steps1 = 0;
        int move_steps2 = 0;
        int oldx = 0;
        int oldy = 0;
        int counter = 0;
        int counterP = 10;
        PointerInfo a;

        Robot hal;

        while (true) {

            if (running.get()) {

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

                        counter = 0;
                    }

                    oldx = x;
                    oldy = y;

                    // System.out.print("*");
                } catch (AWTException e) {
                    e.printStackTrace();
                }
            }

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.err.println("InterruptedException");
            }
        }
    }

    public AtomicBoolean getRunning() {
        return running;
    }
}
