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

public class OnePlayer extends QuizController {
    @FXML
    public Button answerButton;
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

    public void setPlayer(Player p) {
        player = p;
        displayQuestion();
    }

    private Random random;
    private List<Question> questionList;
    private int questionIndex;
    private Question currentQuestion;


    private void displayQuestion() {
        questionLabel.setWrapText(true);
        answer0.setWrapText(true);
        answer1.setWrapText(true);
        answer2.setWrapText(true);
        answer3.setWrapText(true);
        questionLabel.setText(currentQuestion.content);
        categoryLabel.setText("Kategoria: " + currentQuestion.category);
        pointsLabel.setText("Twoje punkty: " + Integer.toString(player.scoreDuringGame));
        answer0.setText(currentQuestion.ans[0]);
        answer1.setText(currentQuestion.ans[1]);
        answer2.setText(currentQuestion.ans[2]);
        answer3.setText(currentQuestion.ans[3]);
    }

    private boolean nextQuestion(){
        if(questionIndex + 1 == player.howManyQuestions)
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
        questionList = Question.loadQuestions("questions");
        questionIndex = -1;
        Collections.shuffle(questionList, random);
        currentQuestion = questionList.get(++questionIndex);
        currentQuestion.shuffle(random);
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

        if(button.isSelected() && button.getText().equals(correctAnswer)) {
            player.scoreDuringGame++;
        }
    }

    public void answer(ActionEvent actionEvent) throws IOException, SQLException {
        checkAnswer(answer0);
        checkAnswer(answer1);
        checkAnswer(answer2);
        checkAnswer(answer3);

        if(NoAnswerIsSelected()){
            alertNoAnswerIsSelected();
        }
        answer0.setSelected(false);
        answer1.setSelected(false);
        answer2.setSelected(false);
        answer3.setSelected(false);

        if(nextQuestion()) {
            displayQuestion();
        }
        else if(!nextQuestion()){

            updateTheScore();
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText("Gratulacje! Gra skończona");
            alert.setContentText("Kliknij ok, żeby zobaczyć swój wynik.");
            Optional<ButtonType> result = alert.showAndWait();
            ButtonType button = result.orElse(ButtonType.CANCEL);

            if(button == ButtonType.OK){
                endOfGame(actionEvent);
            }
            else if(button == ButtonType.CANCEL){
                goToMenu(actionEvent);
            }
        }
    }

    private void updateTheScore() throws SQLException {
        Database db = new Database();
        db.update(player.scoreDuringGame, player.login);
        db.close();
    }

    public void exitFromTheGame(){
        System.exit(0);
    }

    private void endOfGame(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/EndOfGame.fxml"));
        Main.changeWindow(actionEvent, player, null, loader, null);
    }

    public void backToMainWindow(ActionEvent actionEvent) throws  IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/MainWindow.fxml"));
        Main.changeWindow(actionEvent, player, null, loader, null);
    }

    public void goToMenu(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/Menu.fxml"));
        Main.changeWindow(actionEvent, player, null, loader, null);
    }

}
