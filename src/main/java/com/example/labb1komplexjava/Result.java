package com.example.labb1komplexjava;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table
public class Result {

    @Id
    @GeneratedValue
    private Long id;
    private int score;

    public Result() {
    }

    public Result(int score) {
        this.score = score;
    }

    public int getScore() {
        return score;
    }

}

