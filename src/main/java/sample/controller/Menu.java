package sample.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import sample.Main;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Menu extends QuizController {
    @Override
    public void initialize(URL location, ResourceBundle resources) {}


    public void goToLogin(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/Login.fxml"));
        Main.changeWindow(actionEvent, null, null, loader, "login", null);
    }

    public void goToRanking(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/Ranking.fxml"));
        Main.changeWindow(actionEvent,null ,null, loader,"ranking",null);
    }
}
