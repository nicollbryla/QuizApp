package sample.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import sample.model.Player;

import java.net.URL;
import java.util.ResourceBundle;

public class EndOfTwoPlayersGame extends QuizController{

    @FXML
    private Label pointsPlayer1;

    @FXML
    private Label pointsPlayer2;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void setTwoPlayers(Player p1, Player p2){
        firstPlayer = p1;
        secondPlayer = p2;

        pointsPlayer1.setText("Wynik gracza " + firstPlayer.name + " : " + Integer.toString(firstPlayer.score));
        pointsPlayer2.setText("Wynik gracza " + secondPlayer.name + " : " + Integer.toString(secondPlayer.score));
    }

}
