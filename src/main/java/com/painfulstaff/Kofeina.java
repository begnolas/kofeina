package com.painfulstaff;

import java.awt.AWTException;
import java.awt.SystemTray;


public class Kofeina {

    static SupervisorThread supervisor;
    static LogicThread logicThread;
    static GuiThread gui;

    public static void main(String[] args) throws AWTException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        if (!SystemTray.isSupported()) {
            System.out.println("SystemTray is not supported");
            System.exit(0);
        }

        logicThread = new LogicThread();
        logicThread.start();

        supervisor = new SupervisorThread(logicThread);
        supervisor.start();

        gui = new GuiThread(logicThread);
        gui.start();
    }
}
