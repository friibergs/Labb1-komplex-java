package com.example.labb1komplexjava;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
@SessionScope
public class GameService {

    @Autowired
    PlayerRepository playerRepository;
    private Player player;
    private int randomNumber;

    int numberOfGuesses;

    List<String> previousAnswerList = new ArrayList<>();

    boolean isRegistered;

    public GameService(){
        Random random = new Random();
        randomNumber = random.nextInt(1,21);
        numberOfGuesses = 0;
    }

    public Player registered(String name){
        Player p;
        List<Player> playerList = playerRepository.findByName(name);

        if (playerList.size() == 0) {
            Player temp = new Player(name);
            p = playerRepository.save(temp);
        } else {
            p = playerList.get(0);
        }
        player = p;
        isRegistered = true;
        return p;
    }

    public void saveResult (int numberOfGuesses){
        if (!isRegistered) return;
        player.addResultToList(numberOfGuesses);
        playerRepository.save(player);
    }

    public boolean makeGuess (int guess) {
        numberOfGuesses++;
        if (guess < randomNumber){
            previousAnswerList.add(guess + ": För lågt");
            return false;
        } else if (guess > randomNumber){
            previousAnswerList.add(guess + ": För högt");
            return false;
        } else {
            saveResult(numberOfGuesses);
            previousAnswerList.clear();
            return true;
        }
    }

    public List<PlayerAverage> getTopList() {
        List<PlayerAverage> averageScores = new ArrayList<>();
        List<Player> playerList = playerRepository.findAll();
        for (Player p : playerList){
            averageScores.add(new PlayerAverage(p.getName(),p.getAverageScore()));
        }
        averageScores.sort((p1,p2)->Double.compare(p1.getAverageScore(), p2.getAverageScore()));
    return averageScores;
    }

    public PlayerRepository getPlayerRepository() {
        return playerRepository;
    }

    public void setPlayerRepository(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public int getRandomNumber() {
        return randomNumber;
    }

    public void setRandomNumber(int randomNumber) {
        this.randomNumber = randomNumber;
    }

    public int getNumberOfGuesses() {
        return numberOfGuesses;
    }

    public void setNumberOfGuesses(int numberOfGuesses) {
        this.numberOfGuesses = numberOfGuesses;
    }

    public List<String> getPreviousAnswerList() {
        return previousAnswerList;
    }

    public void setPreviousAnswerList(List<String> previousAnswerList) {
        this.previousAnswerList = previousAnswerList;
    }

    public boolean isRegistered() {
        return isRegistered;
    }

    public void setRegistered(boolean registered) {
        isRegistered = registered;
    }
}
