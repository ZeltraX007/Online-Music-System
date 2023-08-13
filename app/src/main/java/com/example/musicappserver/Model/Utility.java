package com.example.musicappserver.Model;

public class Utility {
    public static String convertDuration(long duration){
        long minutes = (duration / 1000) / 60;
        long seconds = (duration / 1000) % 60;
        return String.format("%d:%02d",minutes,seconds);
    }
}
