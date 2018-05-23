package sample.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import sample.Main;
import sample.model.Player;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class GiveNamesTwoPlayers extends QuizController{

    @FXML
    private Button goNext;

    @FXML
    private TextField first;

    @FXML
    private TextField second;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void goNextWindow(ActionEvent actionEvent) throws IOException {
        if(first.getText().equals("") || second.getText().equals("")){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Trzeba podać imiona!");
            alert.showAndWait();
        }
        else {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/TwoPlayers.fxml"));
            Main.changeWindow(actionEvent, new Player(first.getText()), new Player(second.getText()), loader, "twoPlayers", null);
        }
    }

}
