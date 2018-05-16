package main.java.sample.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.effect.Light;
import javafx.scene.effect.Lighting;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import main.java.sample.Main;
import main.java.sample.model.Player;
import main.java.sample.model.Question;
import sun.plugin2.message.Message;

import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.concurrent.TimeUnit;

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
    @FXML
    private Button answerButton;

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

    /*public void alertCorrectAnswer(){
        player.score++;
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("Gratulacje!");
        alert.setContentText("Ta odpowiedź jest poprawna. Dostajesz jeden punkt.");
        alert.showAndWait();
    }*/

   /* public void alertIncorrectAnswer(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("Niestety...");
        alert.setContentText("Ta odpowiedź jest niepoprawna.");
        alert.showAndWait();
    }*/

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

        if(button.isSelected() && button.getText().equals(correctAnswer)) {
            player.score++;
            setColor(button,true);
            return true;
        } else if(button.isSelected()) {
            setColor(button, false);
        }
        return false;
    }

    public void setColor(RadioButton button, boolean correct){
        Light.Distant light = new Light.Distant();
        light.setAzimuth(-135.0);

        Lighting lighting = new Lighting();
        lighting.setLight(light);
        lighting.setSurfaceScale(5.0);

        if(correct)
            button.setTextFill(Color.GREEN);
        else
            button.setTextFill(Color.RED);
        button.setEffect(lighting);
    }

    public void answer(ActionEvent actionEvent) throws IOException, InterruptedException {
        if(checkAnswer(answer0)) {
            System.out.println("ok");
            //setColor(answer0,true);
        } else if(checkAnswer(answer1)) {
            System.out.println("ok");
            //setColor(answer1,true);
        } else if(checkAnswer(answer2)) {
            System.out.println("ok");
            //setColor(answer2,true);
        }else if(checkAnswer(answer3)) {
            System.out.println("ok");
            //setColor(answer3,true);
        } else if(NoAnswerIsSelected()){
            alertNoAnswerIsSelected();
        }
        answer0.setSelected(false);
        answer1.setSelected(false);
        answer2.setSelected(false);
        answer3.setSelected(false);

        if(nextQuestion()) {
            TimeUnit.SECONDS.sleep(2);
            displayQuestion();
        }
        else if(!nextQuestion()){
            TimeUnit.SECONDS.sleep(2);
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText("Gratulacje! Gra skończona");
            alert.setContentText("Kliknij ok, żeby zobaczyć swój wynik.");
            Optional<ButtonType> result = alert.showAndWait();
            ButtonType button = result.orElse(ButtonType.CANCEL);

            if(button == ButtonType.OK){
                endOfGame(actionEvent);
            }
        }
    }

    public void endOfGame(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/EndOfGame.fxml"));
        Main.zmiana_strony_css(actionEvent, player, loader, "endOfGame", null);
    }


    public void backToMainWindow(ActionEvent actionEvent) throws  IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/mainWindow.fxml"));
        Main.zmiana_strony_css(actionEvent, player, loader, "mainWindow", null);
    }
}
