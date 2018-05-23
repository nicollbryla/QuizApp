package sample.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import sample.Main;
import sample.model.Player;

import java.io.IOException;
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
