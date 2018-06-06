package sample.controller;

import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import sample.model.Admin;
import sample.model.Player;
import sample.model.Question;

public abstract class QuizController implements Initializable {
    Player player;
    Player firstPlayer;
    Player secondPlayer;
    Admin admin;

    public void setPlayer(Player player){
        this.player = player;
    }

    public void setTwoPlayers(Player firstPlayer, Player secondPlayer){
        this.firstPlayer = firstPlayer;
        this.secondPlayer = secondPlayer;
    }

    public void setAdmin(Admin a){
        admin = a;
        System.out.println("Administrator: " + admin.name + " " + admin.surname);
    }

    public Player getPlayer(){
        return this.player;
    }

}
