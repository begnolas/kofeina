package com.painfulstaff;

public class SupervisorThread extends Thread 
{
    private LogicThread logicTh;

    public SupervisorThread(LogicThread lTh) {
        logicTh = lTh;
    }     

    @Override
    public void run() {
        try {
            System.out.println("Supervisor: Start.");

            Thread.State oldState = logicTh.getState();

            while (true) {
                Thread.State newState = logicTh.getState();

                if (oldState != newState) {
                    System.out.println("Supervisor: logicTh status changed from " + oldState + " to: " + newState);
                }

                oldState = newState;
                Thread.sleep(1000);
            }

        } catch (InterruptedException e) {
            System.out.println("Supervisor InterruptedException");
        }
    }
}
