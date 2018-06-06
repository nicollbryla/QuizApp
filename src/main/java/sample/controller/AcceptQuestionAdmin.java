package sample.controller;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import sample.Main;
import sample.model.Admin;
import sample.model.Question;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class AcceptQuestionAdmin  extends QuizController{
    @FXML
    private Label questionLabel;
    @FXML
    private Label categoryLabel;
    @FXML
    private Label answer0;
    @FXML
    private Label answer1;
    @FXML
    private Label answer2;
    @FXML
    private Label answer3;
    @FXML
    private Label whichQuestion;


    private List<Question> questionList;
    private int questionIndex;
    private Question currentQuestion;
    private Admin admin;

    public void setAdmin(Admin a) {
        admin = a;
        displayQuestion();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        questionList = Question.loadQuestions("proposedquestions");
        questionIndex = 0;
        currentQuestion = questionList.get(questionIndex);
    }


    public void nextQuestion(ActionEvent actionEvent)throws IOException{
        if(questionIndex + 1 == questionList.size())
            questionIndex = -1;
        currentQuestion = questionList.get(++questionIndex);
        displayQuestion();
    }

    public void prevQuestion(ActionEvent actionEvent)throws IOException{
        if(questionIndex - 1 == -1)
            questionIndex = questionList.size();
        currentQuestion = questionList.get(--questionIndex);
        displayQuestion();
    }

    private void displayQuestion() {
        questionLabel.setWrapText(true);
        answer0.setWrapText(true);
        answer1.setWrapText(true);
        answer2.setWrapText(true);
        answer3.setWrapText(true);
        questionLabel.setText(currentQuestion.content);
        categoryLabel.setText("Kategoria: " + currentQuestion.category);
        whichQuestion.setText(questionIndex + 1 + "/" + questionList.size());
        answer0.setText(currentQuestion.ans[0]);
        answer1.setText(currentQuestion.ans[1]);
        answer2.setText(currentQuestion.ans[2]);
        answer3.setText(currentQuestion.ans[3]);
    }

    public void goToMenu(ActionEvent actionEvent) throws IOException {
        saveChannges(actionEvent);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/Menu.fxml"));
        Main.changeWindow(actionEvent, player, null, loader, "Menu", null);
    }

    public void saveChannges(ActionEvent actionEvent) throws IOException {

    }

    public void goToMainWindowAdmin(ActionEvent actionEvent) throws IOException {
        saveChannges(actionEvent);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/MainWindowAdmin.fxml"));
        Main.changeWindow(actionEvent, player, null, loader, "mainWindowAdmin", null);
    }
}
