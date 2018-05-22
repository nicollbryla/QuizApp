package sample.controller;

import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import sample.model.Player;
import sample.model.Question;

public abstract class QuizController implements Initializable {
    Player player;
    Player firstPlayer;
    Player secondPlayer;

    public void setPlayer(Player player){
        this.player = player;
    }

    public void setTwoPlayers(Player firstPlayer, Player secondPlayer){
        this.firstPlayer = firstPlayer;
        this.secondPlayer = secondPlayer;
    }

    public Player getPlayer(){
        return this.player;
    }

}
