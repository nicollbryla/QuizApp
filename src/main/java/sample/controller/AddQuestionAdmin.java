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

    private void alertToEmptyField(StringBuilder name, boolean multyply){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Błąd podczas dodawania pytania.");
        alert.setHeaderText("Nie można dodać pustego pola do bazy.");
        if(multyply){
            alert.setContentText("Błędne wypełnienie pól :" + name);
        }
        else {
            alert.setContentText("Błędne wypełnienie pola " + name);
        }
        alert.showAndWait();
    }

    public void addQuestion(ActionEvent actionEvent){
        boolean multiply = false;
        StringBuilder emptyFields = new StringBuilder();
        if (question.getText().isEmpty()) {
            emptyFields.append("pytanie");
        }
        if (correctAnswer.getText().isEmpty()){
            if (!emptyFields.toString().equals("")) {
                multiply = true;
                emptyFields.append(", ");
            }
            emptyFields.append("poprawna odpowiedź");
        }
        if (incorrectAnswer1.getText().isEmpty()
                || incorrectAnswer2.getText().isEmpty()
                || incorrectAnswer3.getText().isEmpty()){
            if (!emptyFields.toString().equals("")) {
                multiply = true;
                emptyFields.append(", ");
            }
            emptyFields.append("niepoprawna odpowiedź");
        }
        if ((!emptyFields.toString().equals(""))) {
            emptyFields.append(".");
            alertToEmptyField(emptyFields, multiply);
        }
        else {
            Database db = null;
            ResultSet dbres;
            int maxIdx = -1;
            try {
                db = new Database();
                dbres = db.dbselect("select max(id) from questions");
                if (dbres.next()){
                    maxIdx = dbres.getInt("max") + 1;
                }
                db.close();
            } catch (Exception e) {
                try {
                    assert db != null;
                    db.close();
                } catch (Exception ignored) {
                }
            }
            System.out.println(maxIdx);
            try {
                db = new Database();
                db.insert("Insert into questions(id, cat, content, ans0, ans1, ans2, ans3) values ('" + maxIdx + "','jakakolwiek', '" + question.getText() + "', '" + correctAnswer.getText() + "', '" + incorrectAnswer1.getText() + "', '"+ incorrectAnswer2.getText() +"', '"+incorrectAnswer3.getText() + "'"+");");
                db.close();
            } catch (java.sql.SQLException e) {
                e.printStackTrace();
                try {
                    db.close();
                } catch (Exception ignored) {
                }
            }
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Dodawanie pytań");
            alert.setHeaderText("Pytanie zostało dodane.");
            alert.showAndWait();
        }
    }

    public void backToMainWindowAdmin(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/mainWindowAdmin.fxml"));
        Main.changeWindow(actionEvent, null, null, loader, "mainWindowAdmin", null);
    }
}
