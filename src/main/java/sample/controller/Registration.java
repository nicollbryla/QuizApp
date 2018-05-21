package main.java.sample.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import main.java.sample.Main;
import main.java.sample.model.Database;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.util.ResourceBundle;

public class Registration extends QuizController {

    public PasswordField pass1;
    public PasswordField pass2;
    public TextField name;
    public TextField surname;
    public TextField login;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    private void emptyField(TextField reset, String name, boolean multyply){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Rejestracja");
        alert.setHeaderText("Błąd podczas dokonywania rejestracji.");
        if(multyply){
            alert.setContentText("Błędne wypełnienie pól " + name);
        } else {
            alert.setContentText("Błędne wypełnienie pola " + name);
        }
        reset.setText("");
        alert.showAndWait();
    }

    public void signUp(ActionEvent actionEvent) throws IOException{
        boolean multiply = false;
        if(pass1.getText().equals("") && pass2.getText().equals("")){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Rejestracja");
            alert.setHeaderText("Błąd podczas dokonywania rejestracji.");
            alert.setContentText("Pola \"Hasło\" i \"Powtórz hasło\" nie mogą być puste");
            alert.showAndWait();
        } else if(pass1.getText().equals("")){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Rejestracja");
            alert.setHeaderText("Błąd podczas dokonywania rejestracji.");
            alert.setContentText("Pole \"Hasło\" nie może być puste");
            alert.showAndWait();
        } else if(pass2.getText().equals("")){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Rejestracja");
            alert.setHeaderText("Błąd podczas dokonywania rejestracji.");
            alert.setContentText("Pole \"Powtórz hasło\" nie może być puste");
            alert.showAndWait();
        } else if (!pass1.getText().equals(pass2.getText())) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Rejestracja");
            alert.setHeaderText("Błąd podczas dokonywania rejestracji.");
            alert.setContentText("Podane hasła nie zgadzają sie!");
            alert.showAndWait();
        } else if(name.getText().length() < 2){
            if(surname.getText().length() < 2){
                multiply = true;
                if(login.getText().length() < 2){
                    emptyField(name, "\"Imie\", \"Nazwisko\" i \"Login\"", multiply);
                } else{
                    emptyField(name, "\"Imie\" i \"Nazwisko\"",multiply);
                }
            } else {
                emptyField(name, "\"Imie\"",multiply);
            }
        } else if(surname.getText().length() < 2){
            if(login.getText().length() < 2){
                multiply = true;
                emptyField(surname, "\"Nazwisko\" i \"Login\"",multiply);
            } else {
                emptyField(surname, "\"Nazwisko\"",multiply);
            }
        } else if(login.getText().length() < 2){
            emptyField(login, "\"Login\"",multiply);
        } else {
            Database db = null;
            ResultSet dbres;
            try {
                db = new Database();
                dbres = db.dbselect("Select * from player where login = '" + login.getText() + "'");
                if (dbres.next()){
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Rejestracja");
                    alert.setHeaderText("Błąd podczas dokonywania rejestracji.");
                    alert.setContentText("Podany login jest już zajęty!");
                    login.setText("");
                    alert.showAndWait();
                    return;
                }
                db.close();
            } catch (Exception e) {
                try {
                    assert db != null;
                    db.close();
                } catch (Exception ignored) {
                }
            }
            int maxIdx = 0;
            try {
                db = new Database();
                dbres = db.dbselect("select max(id) from player");
                if (dbres.next()){
                    maxIdx = dbres.getInt("max") + 1;
                }
                db.close();
            } catch (Exception e) {
                try {
                    db.close();
                } catch (Exception ignored) {
                }
            }
            System.out.println(maxIdx);
            try {
                db = new Database();
                db.insert("Insert into player(id, login, password, name, surname, score) values ('" + maxIdx + "', '" + login.getText() + "', '" + pass1.getText() + "', '" + name.getText() + "', '"+ surname.getText() +"', '0'"+");");
                db.close();
            } catch (Exception e) {
                try {
                    db.close();
                } catch (Exception ignored) {
                }
            }
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Rejestracja");
            alert.setHeaderText("Rejestracja ukończona pomyślnie.");
            alert.setContentText("Można sie zalogować.");
            login.setText("");
            alert.showAndWait();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/Login.fxml"));
            Main.changeWindow(actionEvent, null, loader, "logowanie", null);
        }
    }

    public void backToLogin(ActionEvent actionEvent) throws  IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/Login.fxml"));
        Main.changeWindow(actionEvent, player, loader, "Login", null);
    }

    public void goToMenu(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/Menu.fxml"));
        Main.changeWindow(actionEvent, player, loader, "Menu", null);
    }

    public void exitFromTheGame(){
        System.exit(0);
    }
}