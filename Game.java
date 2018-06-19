package com.harddrillstudio.wat.concurrent;

import com.harddrillstudio.wat.concurrent.display.Display;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Game {

    public static Display display;

    int width = 180, height = 480;

    public static Player player1, player2;

    Thread thread1, thread2, threadDisplay;
    Lock lock;


    public Game() {
        init();

        lock = new ReentrantLock();

        player1 = new Player("P1: ping", lock);
        player2 = new Player("P2: pong", lock);

        player1.setNextPlayer(player2);
        player2.setNextPlayer(player1);

        System.out.println("Game starting...!");

        player1.setPlay(true);

        thread2 = new Thread(player2);
        thread2.start();
        thread1 = new Thread(player1);
        thread1.start();



        //Let the players play!
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //Tell the players to stop
        thread1.interrupt();
        thread2.interrupt();

        //Wait until players finish
        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Game finished!");
    }

    public void stopPlayers() {
        lock.lock();
    }

    public void startPlayers() {
        lock.unlock();
    }

    private void init() {
        display = new Display(width, height, this);

        threadDisplay = new Thread(display);
        threadDisplay.start();
    }

}
