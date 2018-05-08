package sample.controllers;

import javafx.fxml.Initializable;
import sample.comp.Player;

public abstract class QuizController implements Initializable {
    Player player;

    public void setPlayer(Player player){
        this.player = player;
    }
    public Player getPlayer(){
        return this.player;
    }
}
