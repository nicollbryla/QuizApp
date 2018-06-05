package sample.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.TextInputDialog;
import sample.Main;
import sample.model.Question;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class MainWindow extends QuizController {

    @Override
    public void initialize(URL location, ResourceBundle resources) {}

    public void backToLogin(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/Login.fxml"));
        Main.changeWindow(actionEvent, player, null, loader, "Login", null);
    }

    public void onePlayer(ActionEvent actionEvent) throws IOException{
        int amount = Question.askForQuestions();
        player.howManyQuestions = amount;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/OnePlayer.fxml"));
        Main.changeWindow(actionEvent, player, null, loader, "onePlayer", null);
    }

    public void twoPlayers(ActionEvent actionEvent) throws IOException {
        if(player.asGuest){
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/GiveNamesTwoPlayers.fxml"));
            Main.changeWindow(actionEvent,null, null, loader,"twoPlayersAsGuests",null);
        }
    }

    public void goToMenu(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/Menu.fxml"));
        Main.changeWindow(actionEvent, player, null, loader, "Menu", null);
    }

    public void addQuestion(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/addQuestionPlayer.fxml"));
        Main.changeWindow(actionEvent, player, null, loader, "addQuestionPlayer", null);
    }
}
