package main.java.sample.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import main.java.sample.model.Player;

import java.net.URL;
import java.util.ResourceBundle;

public class EndOfGame extends QuizController {

    @FXML
    private Label pointsLabel;

    public void setPlayer(Player p){
        player = p;
        pointsLabel.setText(Integer.toString(player.score));
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }


}