package com.example.labb1komplexjava;

public class PlayerAverage {
    private String name;
    private double averageScore;

    public PlayerAverage(String name, double averageScore){
        this.name = name;
        this.averageScore = averageScore;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getAverageScore() {
        return averageScore;
    }

    public void setAverageScore(double averageScore) {
        this.averageScore = averageScore;
    }
}
