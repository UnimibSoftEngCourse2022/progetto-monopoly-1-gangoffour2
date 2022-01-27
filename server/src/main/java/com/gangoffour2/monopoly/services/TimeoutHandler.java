package com.gangoffour2.monopoly.services;

import lombok.Builder;

import java.util.Timer;
import java.util.TimerTask;


public class TimeoutHandler {
    @Builder.Default
    private Timer timer = new Timer();


    public void setTimeout(Runnable runnable, int millisecondi) {
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run(){
                runnable.run();
            }
        }, millisecondi);
    }


    public void stopTimeout() {
        if (timer != null){
            timer.cancel();
            timer = null;
        }
    }
}
