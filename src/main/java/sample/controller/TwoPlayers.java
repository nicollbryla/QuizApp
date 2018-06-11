package sample.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import sample.Main;

import sample.model.Database;
import sample.model.Player;
import sample.model.Question;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.*;

public class TwoPlayers extends QuizController {
    @FXML
    public Button goToNextQuestionButton;
    @FXML
    public Button answerButton;

    @FXML
    private Label namePlayer;

    @FXML
    private RadioButton answer0;

    @FXML
    private RadioButton answer2;

    @FXML
    private RadioButton answer1;

    @FXML
    private RadioButton answer3;

    @FXML
    private Label questionLabel;

    @FXML
    private Label pointsLabel;

    @FXML
    private Label categoryLabel;

    private boolean first = true;
    private Random random;
    private List<Question> questionList;
    private int questionIndex;
    private Question currentQuestion;
    private int amountOfQuestions;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ToggleGroup group = new ToggleGroup();
        answer0.setToggleGroup(group);
        answer1.setToggleGroup(group);
        answer2.setToggleGroup(group);
        answer3.setToggleGroup(group);
        goToNextQuestionButton.setVisible(false);
        answerButton.setVisible(true);
        random = new Random();
        questionIndex = -1;
        questionList = Question.loadQuestions("questions");
        Collections.shuffle(questionList, random);
        currentQuestion = questionList.get(++questionIndex);
        currentQuestion.shuffle(random);
    }

    @Override
    public void setTwoPlayers(Player p1, Player p2) {
        firstPlayer = p1;
        secondPlayer = p2;
        amountOfQuestions = p1.howManyQuestions;
        displayQuestion();
    }

    private void displayQuestion() {
        if(first) {
            namePlayer.setText("Gracz " + firstPlayer.name);
            pointsLabel.setText("Twoje punkty: " + Integer.toString(firstPlayer.scoreDuringGame));
        }
        else {
            namePlayer.setText("Gracz " + secondPlayer.name);
            pointsLabel.setText("Twoje punkty: " + Integer.toString(secondPlayer.scoreDuringGame));
        }

        questionLabel.setWrapText(true);
        answer0.setWrapText(true);
        answer1.setWrapText(true);
        answer2.setWrapText(true);
        answer3.setWrapText(true);
        questionLabel.setText(currentQuestion.content);
        categoryLabel.setText("Kategoria: " + currentQuestion.category);
        answer0.setText(currentQuestion.ans[0]);
        answer1.setText(currentQuestion.ans[1]);
        answer2.setText(currentQuestion.ans[2]);
        answer3.setText(currentQuestion.ans[3]);
    }

    private boolean nextQuestion(){
        if(questionIndex + 1 == amountOfQuestions )
            return false;
        currentQuestion = questionList.get(++questionIndex);
        currentQuestion.shuffle(random);
        return true;
    }

    private boolean NoAnswerIsSelected(){
        return !answer0.isSelected() && !answer1.isSelected() && !answer2.isSelected() && !answer3.isSelected();
    }

    private void alertNoAnswerIsSelected() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText("Błąd");
        alert.setContentText("Trzeba zaznaczyć jedną odpowiedź!");
        alert.showAndWait();
    }

    private void checkAnswer(RadioButton button){
        String correctAnswer = currentQuestion.ans[currentQuestion.correctAnswer];
        if(button.getText().equals(correctAnswer)) {
            button.setStyle("-fx-text-fill: #50ff00");
            if(first) {
                firstPlayer.scoreDuringGame++;
            }
            else {
                secondPlayer.scoreDuringGame++;
            }
        } else {
            button.setStyle("-fx-text-fill: red");
        }
    }

    private void changePlayer(){
        first = !first;
    }

    public void answer(){
        boolean selected = false;

        if (answer0.isSelected()) {
            checkAnswer(answer0);
            selected = true;
        } else if (answer1.isSelected()) {
            checkAnswer(answer1);
            selected = true;
        } else if (answer2.isSelected()) {
            checkAnswer(answer2);
            selected = true;
        } else if (answer3.isSelected()) {
            checkAnswer(answer3);
            selected = true;
        }

        if(NoAnswerIsSelected()){
            alertNoAnswerIsSelected();
        }

        if(selected) {
            answerButton.setVisible(false);
            goToNextQuestionButton.setVisible(true);
        }
    }

    public void goToNextQuestion(ActionEvent actionEvent) throws SQLException, IOException {
        goToNextQuestionButton.setVisible(false);
        answerButton.setVisible(true);

        answer0.setStyle("-fx-text-fill:white");
        answer1.setStyle("-fx-text-fill:white");
        answer2.setStyle("-fx-text-fill:white");
        answer3.setStyle("-fx-text-fill:white");

        answer0.setSelected(false);
        answer1.setSelected(false);
        answer2.setSelected(false);
        answer3.setSelected(false);

        if(nextQuestion()) {
            changePlayer();
            displayQuestion();
        }
        else if(!nextQuestion()){
            updateTheScore();
            endOfGame(actionEvent);
        }
    }

    private void updateTheScore() throws SQLException {
        Database db = new Database();
        db.updateScore(firstPlayer.scoreDuringGame, firstPlayer.login);
        db.updateScore(secondPlayer.scoreDuringGame, secondPlayer.login);
        db.close();
    }

    private void endOfGame(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/EndOfTwoPlayersGame.fxml"));
        Main.changeWindow(actionEvent, firstPlayer, secondPlayer, loader, null);
    }

    public void goToMenu(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/Menu.fxml"));
        Main.changeWindow(actionEvent, firstPlayer, secondPlayer, loader, null);
    }

    public void exitFromTheGame(){
        System.exit(0);
    }
}
