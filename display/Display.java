package com.harddrillstudio.wat.concurrent.display;

import com.harddrillstudio.wat.concurrent.Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Display implements Runnable{
    private JFrame frame;
    private Canvas canvas;

    public static JLabel countLabel = new JLabel("SFDSF");
    public static JTextArea history = new JTextArea(30, 30);
    public static JScrollPane jp = new JScrollPane(history);
    public static JButton stopButton = new JButton("IT'S TIME TO STOP");
    public static JButton startButton = new JButton("START");

    Game game;

    public Display(int width, int height, Game game) {
        createDisplay(width, height);
        this.game = game;
    }

    private void createDisplay(int width, int height) {
        frame = new JFrame();
        frame.setSize(width, height);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setAlwaysOnTop(true);
        frame.setFocusable(true);
        frame.setLayout(new FlowLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        /*frame.setCursor(Toolkit.getDefaultToolkit().createCustomCursor(
                new ImageIcon("res/textures/cursor.png").getImage(),
                new Point(0,0),"custom cursor"));*/

        canvas = new Canvas();
        canvas.setPreferredSize(new Dimension(width, height));
        canvas.setMaximumSize(new Dimension(width, height));
        canvas.setMinimumSize(new Dimension(width, height));
        canvas.setFocusable(false);

        frame.add(canvas);
        //frame.pack();
    }

    public JFrame getFrame() {
        return frame;
    }

    public Canvas getCanvas(){
        return canvas;
    }

    @Override
    public void run() {

        setSwingElements();

        this.frame.add(stopButton);
        this.frame.add(startButton);
        this.frame.add(jp);
        this.frame.pack();
        this.frame.setVisible(true);
    }

    public static void logHistory(String msg) {
        String latestHistory = history.getText();
        history.setText(null);
        history.append(latestHistory + "\n" + msg);
    }


    private void setSwingElements() {
        countLabel.setSize(40, 20);
        this.frame.add(countLabel);

        history.setBounds(200, 200, 100, 200);
        history.append("Starting...");

        jp.setBounds(200, 200, 300, 200);

        stopButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                logHistory("STOP");
                //Tell the players to stop
                game.stopPlayers();
            }
        });

        startButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                logHistory("START!");
                //Tell the players to stop
                game.startPlayers();
            }
        });
    }


}
