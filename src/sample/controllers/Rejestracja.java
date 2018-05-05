package sample.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import sample.comp.Database;
import sample.Main;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.util.ResourceBundle;

public class Rejestracja implements Initializable{

    public PasswordField pass1;
    public PasswordField pass2;
    public TextField imie;
    public TextField nazwisko;
    public TextField login;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    public void pustepole(TextField wyzeruj, String name){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Rejestracja");
        alert.setHeaderText("Błąd podczas dokonywania rejestracji.");
        alert.setContentText("Błędne wypełnienie pola " + name);
        wyzeruj.setText("");
        alert.showAndWait();

    }

    public void zarejestruj(ActionEvent actionEvent) throws IOException{
        if (!pass1.getText().equals(pass2.getText())) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Rejestracja");
            alert.setHeaderText("Błąd podczas dokonywania rejestracji.");
            alert.setContentText("Podane hasła nie zgadzają sie!");
            alert.showAndWait();
        } else if(imie.getText().length() < 2){
            pustepole(imie, "Imie");
        } else if(nazwisko.getText().length() < 2){
            pustepole(nazwisko, "Nazwisko");
        }else if(login.getText().length() < 5){
            pustepole(login, "login");
        }else {
                Database db = null;
                ResultSet dbres = null;
                try {
                    db = new Database();
                    System.out.println("Select * from clients where login = '" + login.getText() + "'");

                    dbres = db.dbselect("Select * from clients where login = '" + login.getText() + "'");
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
                int d = 0;
                try {
                    db = new Database();
                    dbres = db.dbselect("select max(id_client) from clients");
                    if (dbres.next()){
                        d = dbres.getInt("1") + 1;
                    }
                    db.close();
                } catch (Exception e) {
                    try {
                        db.close();
                    } catch (Exception e2) {
                    }
                }
                System.out.println(d);
                try {
                    db = new Database();
                    db.insert("Insert into player(id, login, password, name, surname, score) values ('" + d + "', " + login.getText() + "', '" + pass1.getText() + "', '" + imie.getText() + "', '"+ nazwisko.getText() +"', '0'"+");");
                    db.close();
                } catch (Exception e) {
                    try {
                        db.close();
                    } catch (Exception e2) {
                    }
                }
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Rejestracja");
                alert.setHeaderText("Rejestracja ukończona pomyślnie.");
                alert.setContentText("Można sie zalogować.");
                login.setText("");
                alert.showAndWait();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("../fxml/Login.fxml"));
                Main.zmiana_strony_css(actionEvent, null, loader, "logowanie", null);
            }
    }
}