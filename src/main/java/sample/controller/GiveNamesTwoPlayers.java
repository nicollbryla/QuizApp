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
        } else if(first.getText().equals(second.getText())){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Imiona muszą się różnić!");
            alert.showAndWait();
        } else {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/TwoPlayers.fxml"));
            Main.changeWindow(actionEvent, new Player(first.getText()), new Player(second.getText()), loader, null);
        }
    }

    public void goToMenu(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/Menu.fxml"));
        Main.changeWindow(actionEvent, player, null, loader, null);
    }

    /*public void goToMainWindow(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/MainWindow.fxml"));
        Main.changeWindow(actionEvent, player, null, loader, "mainWindow", null);
    }*/

    public void backToLogin(ActionEvent actionEvent) throws  IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/Login.fxml"));
        Main.changeWindow(actionEvent, player, null, loader,  null);
    }


}
