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

    public void onePlayer(ActionEvent actionEvent) throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/OnePlayer.fxml"));
        Main.zmiana_strony_css(actionEvent, player, loader, "onePlayer", null);
    }

}
