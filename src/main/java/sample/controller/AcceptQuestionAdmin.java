package sample.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import sample.Main;
import sample.model.Admin;
import sample.model.Database;
import sample.model.Question;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Vector;

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
    @FXML
    private ToggleButton skipButton;
    @FXML
    private ToggleButton addButton;
    @FXML
    private ToggleButton deleteButton;
    @FXML
    public ToggleGroup tgroup;

    private List<Question> questionList;
    private int questionIndex;
    private Question currentQuestion;
    private Vector<Integer> ifAddList;


    public void setAdmin(Admin a) {
        admin = a;
        displayQuestion();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        questionList = Question.loadQuestions("proposedquestions");
        questionIndex = 0;
        currentQuestion = questionList.get(questionIndex);
        System.out.println(questionList.size());
        ifAddList = new Vector<>();
        ifAddList.setSize(questionList.size());
        System.out.println(ifAddList.size());
        for (int i = 0; i < ifAddList.size(); ++i)
        {
            ifAddList.set(i,0);
        }
        tgroup.selectedToggleProperty().addListener((skipButton,addButton,deleteButton)-> {
            saveToggle();
            //change colour
        });
    }

    public void nextQuestion() {

        if(questionIndex + 1 == questionList.size())
            questionIndex = -1;
        currentQuestion = questionList.get(++questionIndex);
        displayQuestion();
    }

    public void prevQuestion(){
        if(questionIndex - 1 == -1)
            questionIndex = questionList.size();
        currentQuestion = questionList.get(--questionIndex);
        displayQuestion();
    }

    private void saveToggle() {
        if (tgroup.getSelectedToggle() == addButton) {
            ifAddList.setElementAt(1,questionIndex);
        }
        else if (tgroup.getSelectedToggle() == deleteButton) {
            ifAddList.setElementAt(2, questionIndex);
        }
        else if (tgroup.getSelectedToggle() == skipButton || tgroup.getSelectedToggle() == null)
        {
            ifAddList.setElementAt(0,questionIndex);
        }
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
        if (ifAddList.elementAt(questionIndex) == 1) {
            tgroup.selectToggle(addButton);
        }
        else if (ifAddList.elementAt(questionIndex) == 2) {
            tgroup.selectToggle(deleteButton);
        }
        else {
            tgroup.selectToggle(skipButton);
        }
    }

    public void goToMenu(ActionEvent actionEvent) throws IOException {
        saveChanges();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/Menu.fxml"));
        Main.changeWindow(actionEvent, null, null, loader, admin);
    }

    public void saveAndDisplay(ActionEvent actionEvent) throws IOException {
        saveChanges();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/AcceptQuestionAdmin.fxml"));
        Main.changeWindow(actionEvent, null, null, loader, admin);
    }

    private void saveChanges(){
        Database db = new Database();
        for (int i = 0; i < ifAddList.size(); ++i) {
            Integer iff = ifAddList.elementAt(i);
            if (iff == 1) //add
            {
                Question q = questionList.get(i);
                AddQuestion addQuestion = new AddQuestion();
                addQuestion.addQuestion("questions", q.content, q.ans[q.correctAnswer], q.ans[1], q.ans[2], q.ans[3]);
                db.executeUpdate("executeUpdate from proposedquestions where id = "+q.id.toString()+";");
            }
            else if (iff == 2) //executeUpdate
            {
                db.executeUpdate("executeUpdate from proposedquestions where id = "+questionList.get(i).id.toString()+";");
            }
        }
        try {
            db.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void goToMainWindowAdmin(ActionEvent actionEvent) throws IOException {
        saveChanges();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/MainWindowAdmin.fxml"));
        Main.changeWindow(actionEvent, player, null, loader, null);
    }
}
