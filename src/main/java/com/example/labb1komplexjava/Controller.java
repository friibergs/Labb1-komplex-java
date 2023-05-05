package com.example.labb1komplexjava;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@org.springframework.stereotype.Controller
public class Controller {

    @Autowired
    private GameService gameService;

    @PostMapping ("/start")
    public String register(@RequestParam String name, Model m){
        gameService.registered(name);
        m.addAttribute("name", gameService.getPlayer().getName());
        return "gamesite";
    }

    @PostMapping ("/game")
    public String answers(@RequestParam int guess, Model m){
        boolean gameOver = gameService.makeGuess(guess);
        m.addAttribute("player", gameService.getPlayer().getName());
        m.addAttribute("gameover", gameOver?"Du gissade rätt!": "FEEEEL, försök igen!");
        m.addAttribute("answers", gameService.getPreviousAnswerList());
        return "gamesite";
    }

    @GetMapping("/result")
    public String result(Model m){
        m.addAttribute("toplist", gameService.getTopList());
        return "results";
    }



}
