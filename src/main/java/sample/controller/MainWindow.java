package sample.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import sample.Main;
import sample.model.Question;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainWindow extends QuizController {

    @Override
    public void initialize(URL location, ResourceBundle resources) {}

    public void backToLogin(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/Login.fxml"));
        Main.changeWindow(actionEvent, player, null, loader, null);
    }

    public void onePlayer(ActionEvent actionEvent) throws IOException{
        player.howManyQuestions = Question.askForQuestions();
        if(player.howManyQuestions != 0) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/OnePlayer.fxml"));
            Main.changeWindow(actionEvent, player, null, loader, null);
        }
    }

    public void twoPlayers(ActionEvent actionEvent) throws IOException {
        if(player.asGuest){
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/GiveNamesTwoPlayers.fxml"));
            Main.changeWindow(actionEvent,null, null, loader, null);
        }
        else{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/LoginSecondPlayer.fxml"));
            Main.changeWindow(actionEvent, player, null, loader, null);
        }
    }

    public void goToMenu(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/Menu.fxml"));
        Main.changeWindow(actionEvent, player, null, loader, null);
    }

    public void addQuestion(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/AddQuestionPlayer.fxml"));
        Main.changeWindow(actionEvent, player, null, loader, null);
    }
}
