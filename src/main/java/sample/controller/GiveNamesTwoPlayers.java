package sample.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import sample.Main;
import sample.model.Player;
import sample.model.Question;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class GiveNamesTwoPlayers extends QuizController{
    @FXML
    public Button goNext;
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
            Player p1 = new Player(first.getText());
            Player p2 = new Player(second.getText());
            p1.howManyQuestions = Question.askForQuestions();
            p2.howManyQuestions = p1.howManyQuestions;
            if(p1.howManyQuestions != 0) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/TwoPlayers.fxml"));
                Main.changeWindow(actionEvent, p1, p2, loader, null);
            }
        }
    }

    public void goToMenu(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/Menu.fxml"));
        Main.changeWindow(actionEvent, player, null, loader, null);
    }

    public void backToLogin(ActionEvent actionEvent) throws  IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/Login.fxml"));
        Main.changeWindow(actionEvent, player, null, loader,  null);
    }
}
