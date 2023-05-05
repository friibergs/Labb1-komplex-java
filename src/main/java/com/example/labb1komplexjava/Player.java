package com.example.labb1komplexjava;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table
public class Player {

    private String name;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToMany(fetch=FetchType.EAGER, cascade = {CascadeType.ALL})
    private List<Result> resultList = new ArrayList<>();

    public Player() {
    }

    public Player(String name) {
        this.name = name;
    }


    public double getAverageScore() {
        if (resultList.isEmpty()){
            return 0;
        }
        int totalScore = resultList.stream().mapToInt(Result::getScore).sum();
        return (double) totalScore/resultList.size();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Result> getResultList() {
        return resultList;
    }

    public void setResultList(List<Result> resultList) {
        this.resultList = resultList;
    }

    public void addResultToList (int guess){
        resultList.add(new Result(guess));
    }
}
