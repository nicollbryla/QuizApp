package sample.controller;

import javafx.fxml.FXML;

import javafx.scene.control.Label;
import sample.model.Question;

import java.awt.*;
import java.net.URL;
import java.util.List;
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
    private Label pointsLabel;


    private List<Question> questionList;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
