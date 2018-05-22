package sample.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import org.omg.Messaging.SYNC_WITH_TRANSPORT;
import sample.Main;
import sample.controller.QuizController;
import sample.model.Database;
import sample.model.Player;
import sample.model.Question;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.*;

public class TwoPlayers extends QuizController {

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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ToggleGroup group = new ToggleGroup();
        answer0.setToggleGroup(group);
        answer1.setToggleGroup(group);
        answer2.setToggleGroup(group);
        answer3.setToggleGroup(group);
        random = new Random();
        questionIndex = -1;
        questionList = Question.loadQuestions();
        Collections.shuffle(questionList, random);
        nextQuestion();
    }

    @Override
    public void setTwoPlayers(Player p1, Player p2) {
        firstPlayer = p1;
        secondPlayer = p2;
        displayQuestion();
    }

    public void changePlayer(){
        first = !first;
    }

    private void displayQuestion() {
        if(first) {
            namePlayer.setText(firstPlayer.name);
            pointsLabel.setText("Twoje punkty: " + Integer.toString(firstPlayer.score));
        }
        else {
            namePlayer.setText(secondPlayer.name);
            pointsLabel.setText("Twoje punkty: " + Integer.toString(secondPlayer.score));
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
        if(questionIndex + 1 == questionList.size())
            return false;
        currentQuestion = questionList.get(++questionIndex);
        currentQuestion.shuffle(random);
        return true;
    }

    public boolean NoAnswerIsSelected(){
        if(!answer0.isSelected() && !answer1.isSelected() && !answer2.isSelected() && !answer3.isSelected())
            return true;
        return false;
    }

    public void alertNoAnswerIsSelected() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText("Błąd");
        alert.setContentText("Trzeba zaznaczyć jedną odpowiedź!");
        alert.showAndWait();

    }

    public boolean checkAnswer(RadioButton button){
        String correctAnswer = currentQuestion.ans[currentQuestion.correctAnswer];
        if(button.isSelected() && button.getText().equals(correctAnswer)) {
            if(first) {
                firstPlayer.score++;
                System.out.println("punkt dla 1");
            }
            else {
                secondPlayer.score++;
                System.out.println("punkt dla 2");
            }
            return true;
        } else if(button.isSelected()) {
        }
        return false;
    }

    public void answer(ActionEvent actionEvent) throws IOException {
        if(checkAnswer(answer0) || checkAnswer(answer1) || checkAnswer(answer1) || checkAnswer(answer1) || checkAnswer(answer1)) {
        } else if(NoAnswerIsSelected()){
            alertNoAnswerIsSelected();
        }
        answer0.setSelected(false);
        answer1.setSelected(false);
        answer2.setSelected(false);
        answer3.setSelected(false);

        if(nextQuestion()) {
            changePlayer();
            displayQuestion();
        }
        else if(!nextQuestion()){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText("Gratulacje! Gra skończona");
            alert.setContentText("Kliknij ok, żeby zobaczyć swój wynik.");
            Optional<ButtonType> result = alert.showAndWait();
            ButtonType button = result.orElse(ButtonType.CANCEL);

           // updateTheScore();

            if(button == ButtonType.OK){
                endOfGame(actionEvent);
            }
        }

    }

    /*public void updateTheScore() throws SQLException {
        Database db = new Database();
        db.update(player.score, player.login);
        db.close();
    }*/

    public void endOfGame(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/EndOfTwoPlayersGame.fxml"));
        Main.changeWindow(actionEvent, firstPlayer, secondPlayer, loader, "endOfTwoPlayersGame", null);
    }

    public void backToMainWindow(ActionEvent actionEvent) throws  IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/mainWindow.fxml"));
        Main.changeWindow(actionEvent, firstPlayer, secondPlayer, loader, "mainWindow", null);
    }

    public void goToMenu(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/Menu.fxml"));
        Main.changeWindow(actionEvent, firstPlayer, secondPlayer, loader, "Menu", null);
    }

    public void exitFromTheGame(){
        System.exit(0);
    }
}
