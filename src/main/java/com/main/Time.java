package com.main;

/**
 * Created by Igor on 07.01.2015.
 */
public class Time {

    private String[] time;
    private int countStart;
    private int countCurrent;
    private int hours;
    private int minutes;
    private int seconds;
    private boolean isStarted=false;


    private Time(){}
    private static Time factory = new Time();
    public static Time getInstance(){return factory;}

    public void setTime(String time){
        this.time = time.split(":");

        if(!isStarted){
            countCurrent = Integer.parseInt(this.time[0])*3600+Integer.parseInt(this.time[1])*60+Integer.parseInt(this.time[2]);
            countStart = countCurrent;
            hours = countCurrent/3600;
            minutes = (countCurrent%3600)/60;
            seconds = countCurrent%60;
        }
    }
    public int getHours(){return hours;}
    public int getMinutes(){return minutes;}
    public int getSeconds(){return seconds;}
    public int getCountStart(){return countStart;}
    public int getCountCurrent(){return countCurrent;}

    public void decrementCount(){
        countCurrent--;
        if(countCurrent!=0)
            isStarted=true;

        if(isStarted){
            hours = countCurrent/3600;
            minutes = (countCurrent%3600)/60;
            seconds = countCurrent%60;
        }
    }

    public boolean isStarted(){return isStarted;}

}
