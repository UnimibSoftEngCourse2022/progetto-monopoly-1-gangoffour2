package com.gangoffour2.monopoly.services;

public class TimeoutHandler {
    private Thread handlerThread;


    public void setTimeout(Runnable runnable, int millisecondi) {
        handlerThread = new Thread(() -> {
            try {
                Thread.sleep(millisecondi);
                runnable.run();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }

        });
        handlerThread.start();
    }


    public void stopTimeout() {
        handlerThread.interrupt();
    }
}
