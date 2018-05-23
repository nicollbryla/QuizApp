package sample.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import sample.model.Player;

import java.net.URL;
import java.util.ResourceBundle;

public class EndOfGame extends QuizController {

    @FXML
    private Label pointsLabel;

    public void setPlayer(Player p){
        player = p;
        pointsLabel.setText(Integer.toString(player.scoreDuringGame));
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }


}
