package sample.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import sample.Main;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainWindow extends QuizController {

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void backToLogin(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/Login.fxml"));
        Main.changeWindow(actionEvent, player, loader, "Login", null);
    }

    public void onePlayer(ActionEvent actionEvent) throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/OnePlayer.fxml"));
        Main.changeWindow(actionEvent, player, loader, "onePlayer", null);
    }

    public void goToMenu(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/Menu.fxml"));
        Main.changeWindow(actionEvent, player, loader, "Menu", null);
    }
}
