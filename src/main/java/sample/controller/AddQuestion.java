package sample.controller;

import javafx.scene.control.Alert;
import sample.model.Database;

import java.sql.ResultSet;

public class AddQuestion  {

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

    public boolean validateQuestion(String question, String correctAnswer, String incorrectAnswer1, String incorrectAnswer2, String incorrectAnswer3){
        boolean multiply = false;
        StringBuilder emptyFields = new StringBuilder();
        if (question.isEmpty()) {
            emptyFields.append("pytanie");
        }
        if (correctAnswer.isEmpty()){
            if (!emptyFields.toString().equals("")) {
                multiply = true;
                emptyFields.append(", ");
            }
            emptyFields.append("poprawna odpowiedź");
        }
        if (incorrectAnswer1.isEmpty()
                || incorrectAnswer2.isEmpty()
                || incorrectAnswer3.isEmpty()){
            if (!emptyFields.toString().equals("")) {
                multiply = true;
                emptyFields.append(", ");
            }
            emptyFields.append("niepoprawna odpowiedź");
        }
        if ((!emptyFields.toString().equals(""))) {
            emptyFields.append(".");
            alertToEmptyField(emptyFields, multiply);
            return false;
        }
        return true;
    }

    public void addQuestion(String tableName, String question, String correctAnswer, String incorrectAnswer1, String incorrectAnswer2, String incorrectAnswer3){
            Database db = null;
            ResultSet dbres;
            int maxIdx = -1;
            try {
                db = new Database();
                dbres = db.dbselect("select max(id) from "+tableName);
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
                db.insert("Insert into "+tableName+"(id, cat, content, ans0, ans1, ans2, ans3) values ('"
                        + maxIdx + "','jakakolwiek', '" + question + "', '" + correctAnswer
                        + "', '" + incorrectAnswer1 + "', '"+ incorrectAnswer2 +"', '"
                        +incorrectAnswer3 + "'"+");");
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
