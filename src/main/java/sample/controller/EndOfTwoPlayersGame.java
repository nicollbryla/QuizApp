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

    public void goToLogin(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/Login.fxml"));
        Main.changeWindow(actionEvent, firstPlayer, secondPlayer, loader,null);
    }

    public void exitFromTheGame(){
        System.exit(0);
    }

}
