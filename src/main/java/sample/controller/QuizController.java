package main.java.sample.controller;

import javafx.fxml.Initializable;
import main.java.sample.model.Player;

public abstract class QuizController implements Initializable {
    Player player;

    public void setPlayer(Player player){
        this.player = player;
    }

    public Player getPlayer(){
        return this.player;
    }
}
