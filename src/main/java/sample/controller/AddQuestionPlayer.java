package sample.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import sample.Main;
import sample.model.Database;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.util.ResourceBundle;

public class AddQuestionPlayer extends QuizController {

    @FXML
    private TextField question;

    @FXML
    private TextField correctAnswer;

    @FXML
    private TextField incorrectAnswer1;

    @FXML
    private TextField incorrectAnswer2;

    @FXML
    private TextField incorrectAnswer3;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void addQuestion(ActionEvent actionEvent){
        AddQuestion x = new AddQuestion();
        if (x.validateQuestion(question.getText(), correctAnswer.getText(), incorrectAnswer1.getText(), incorrectAnswer2.getText(), incorrectAnswer3.getText())){
            x.addQuestion("proposedQuestions",question.getText(), correctAnswer.getText(), incorrectAnswer1.getText(), incorrectAnswer2.getText(), incorrectAnswer3.getText());
        }
    }

    public void backToMenu(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/Menu.fxml"));
        Main.changeWindow(actionEvent, null, null, loader, "menu", null);
    }
}
