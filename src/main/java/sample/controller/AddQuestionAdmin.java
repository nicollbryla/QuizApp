package sample.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import sample.Main;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AddQuestionAdmin extends QuizController {

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

    public void addQuestion(ActionEvent actionEvent) throws IOException {
        AddQuestion x = new AddQuestion();
        if (x.validateQuestion(question.getText(), correctAnswer.getText(), incorrectAnswer1.getText(), incorrectAnswer2.getText(), incorrectAnswer3.getText())){
            x.addQuestion("questions",question.getText(), correctAnswer.getText(), incorrectAnswer1.getText(), incorrectAnswer2.getText(), incorrectAnswer3.getText());

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Dodawanie pytań");
            alert.setHeaderText("Pytanie zostało dodane.");
            alert.showAndWait();
        }
    }


    public void backToMainWindowAdmin(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/MainWindowAdmin.fxml"));
        Main.changeWindow(actionEvent, null, null, loader, null);
    }
}
