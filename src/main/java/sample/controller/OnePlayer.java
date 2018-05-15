package main.java.sample.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import main.java.sample.Main;
import main.java.sample.model.Player;
import main.java.sample.model.Question;

import java.io.IOException;
import java.net.URL;
import java.util.*;

public class OnePlayer extends QuizController {
    @FXML
    private Label questionLabel;
    @FXML
    private Label categoryLabel;
    @FXML
    private RadioButton answer0;
    @FXML
    private RadioButton answer1;
    @FXML
    private RadioButton answer2;
    @FXML
    private RadioButton answer3;
    @FXML
    private Label pointsLabel;

    public void setPlayer(Player p){
        player = p;
        displayQuestion();
    }

    private Random random;
    private List<Question> questionList;
    private int questionIndex;
    private Question currentQuestion;

    private void displayQuestion(){
        questionLabel.setWrapText(true);
        answer0.setWrapText(true);
        answer1.setWrapText(true);
        answer2.setWrapText(true);
        answer3.setWrapText(true);
        questionLabel.setText(currentQuestion.content);
        categoryLabel.setText("Kategoria: " + currentQuestion.category);
        pointsLabel.setText("Twoje punkty: " + Integer.toString(player.score));
        answer0.setText(currentQuestion.ans[0]);
        answer1.setText(currentQuestion.ans[1]);
        answer2.setText(currentQuestion.ans[2]);
        answer3.setText(currentQuestion.ans[3]);
    }

    private boolean nextQuestion(){
        if(questionIndex + 1 == questionList.size())
            return false;
        currentQuestion = questionList.get(++questionIndex);
        currentQuestion.shuffle(random);
        return true;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ToggleGroup group = new ToggleGroup();
        answer0.setToggleGroup(group);
        answer1.setToggleGroup(group);
        answer2.setToggleGroup(group);
        answer3.setToggleGroup(group);
        random = new Random();
        questionList = Question.loadQuestions();
        questionIndex = -1;
        Collections.shuffle(questionList, random);
        nextQuestion();
    }

    public void alertCorrectAnswer(){
        player.score++;
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("Gratulacje!");
        alert.setContentText("Ta odpowiedź jest poprawna. Dostajesz jeden punkt.");
        alert.showAndWait();
    }

    public void alertIncorrectAnswer(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("Niestety...");
        alert.setContentText("Ta odpowiedź jest niepoprawna.");
        alert.showAndWait();
    }

    public boolean NoAnswerIsSelected(){
        if(!answer0.isSelected() && !answer1.isSelected() && !answer2.isSelected() && !answer3.isSelected())
            return true;
        return false;
    }

    public void alertNoAnswerIsSelected(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("Błąd");
        alert.setContentText("Trzeba zaznaczyć jedną odpowiedź!");
        alert.showAndWait();
    }

    public boolean checkAnswer(RadioButton button){
        String correctAnswer = currentQuestion.ans[currentQuestion.correctAnswer];

        if(button.isSelected() && button.getText().equals(correctAnswer))
            return true;
        return false;
    }

    public void answer(ActionEvent actionEvent) throws IOException {
        if(checkAnswer(answer0) || checkAnswer(answer1) || checkAnswer(answer2) || checkAnswer(answer3)){
            alertCorrectAnswer();
        } else if(NoAnswerIsSelected()){
            alertNoAnswerIsSelected();
        } else {
            alertIncorrectAnswer();
        }
        answer0.setSelected(false);
        answer1.setSelected(false);
        answer2.setSelected(false);
        answer3.setSelected(false);
        if(nextQuestion())
            displayQuestion();
    }

    public void backToMainWindow(ActionEvent actionEvent) throws  IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/mainWindow.fxml"));
        Main.zmiana_strony_css(actionEvent, player, loader, "mainWindow", null);
    }
}
