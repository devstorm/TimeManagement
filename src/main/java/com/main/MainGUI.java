package com.main;

/**
 * Created by Igor on 07.01.2015.
 */

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

public class MainGUI extends JFrame {
    private JLabel promptLabel, timerLabel;
    private Time time = Time.getInstance();
    private JFormattedTextField textField;
    private JButton button;
    private JButton buttonPause;
    private Timer timer = new Timer(1000,new TimeTickListener(time.getCountCurrent()));

    private void Initialize(){
        setLayout(new GridLayout(3,2,6,5));

        promptLabel = new JLabel("Enter seconds:", SwingConstants.CENTER);
        add(promptLabel);

        MaskFormatter format = null;
        try {
            format = new MaskFormatter("##:##:##");
            format.setPlaceholderCharacter('_');
        } catch (ParseException e) {
            e.printStackTrace();
        }
        textField=new JFormattedTextField(format);
        add(textField);

        button=new JButton("Start timing");
        add(button);

        timerLabel=new JLabel("Waiting ...", SwingConstants.CENTER);
        add(timerLabel);

        buttonPause=new JButton("Pause timing");
        add(buttonPause);


        button.addActionListener(new StartListener());
        buttonPause.addActionListener(new StopListener());


        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setAlwaysOnTop(true);
        this.setSize(320, 120);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setTitle("Task Timer");
        this.setVisible(true);
    }

    public MainGUI(){
        Initialize();
    }

    public class StopListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            timer.stop();
        }
    }

    public class StartListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {

            time.setTime(textField.getText());
            //timerLabel.setText("Time left: "+String.format("%02d:%02d:%02d", time.getHours(), time.getMinutes(), time.getSeconds()));
            timer.start();
        }
    }

    class TimeTickListener implements ActionListener {
        int counter;

        public TimeTickListener(int counter){
            this.counter=counter;
        }

        @Override
        public void actionPerformed(ActionEvent e) {

            time.decrementCount();
            if(time.getCountCurrent()>=1){
                timerLabel.setText("Time left: "+String.format("%02d:%02d:%02d", time.getHours(), time.getMinutes(), time.getSeconds()));
            }else {
                timer.stop();
                timerLabel.setText("Done!");
                Toolkit.getDefaultToolkit().beep();
            }

        }
    }



}