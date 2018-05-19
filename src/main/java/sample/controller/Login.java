package main.java.sample.controller;

import main.java.sample.model.Admin;
import main.java.sample.model.Database;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import main.java.sample.model.Player;
import main.java.sample.Main;

public class Login extends QuizController {
    @FXML
    public ToggleGroup logowanie;
    @FXML
    private Label alert;
    @FXML
    private ToggleButton playerButton;
    @FXML
    private ToggleButton adminButton;
    @FXML
    private PasswordField haslo;
    @FXML
    private TextField login;
    @FXML
    private Button guest;
    @FXML
    private Button register;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        login.setText("ResultSet");
        haslo.setText("halko");
        guest.setWrapText(true);
        register.setWrapText(true);
    }

    public void signIn(ActionEvent actionEvent) throws IOException, SQLException {
        Database db = null;
        String name = null;
        Player player = null;
        Admin admin = null;
        ResultSet dbres;
        if (logowanie.getSelectedToggle() == null) {
            alert.setText("Nie wybrano zadnej opcji!");
            return;
        }
        if (logowanie.getSelectedToggle() == adminButton){
            try {
                login.setText("jerzyna");
                haslo.setText("zeszczecina");
                db = new Database();
                dbres = db.dbselect("SELECT * from admin p where login = '" + login.getText() + "' AND password = '" + haslo.getText() + "'");
                int cntr = 0;
                while (dbres.next()) {
                    cntr++;
                    admin = new Admin(dbres);
                    if (cntr == 2) {
                        alert.setText("Co najmniej dwa te same rekordy w bazie danych");
                        break;
                    }
                }
                if (cntr == 0) {
                    alert();
                }
                if (admin != null) {
                    db.close();
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/mainWindowAdmin.fxml"));
                    try {
                        Main.zmiana_strony_css(actionEvent, null, loader, "mainWindowAdmin",  admin);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                db.close();
            }
            catch (Exception e){
                try {
                    assert db != null;
                    db.close();
                } catch (Exception ignored){
                }
                alert.setText("Wystąpił problem z bazą danych.");
                e.printStackTrace();
            }
        }
        else if (logowanie.getSelectedToggle() == playerButton){
            try {
                db = new Database();
                dbres = db.dbselect("SELECT * from player p where login = '" + login.getText() + "' AND password = '" + haslo.getText() + "'");
                int cntr = 0;
                while (dbres.next()) {
                    cntr++;
                    if (cntr == 2) {
                        alert.setText("Więcej tych samych rekordów w bazie danych");
                        break;
                    }
                    player = new Player(dbres);
                }
                if (cntr == 0){
                    alert();
                }
                if (player != null) {
                    db.close();
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/mainWindow.fxml"));
                    try {
                        Main.zmiana_strony_css(actionEvent, player, loader, "mainWindow",  null);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                db.close();
            }
            catch (Exception e){
                try {
                    assert db != null;
                    db.close();
                } catch (Exception ignored){
                }
                alert.setText("Wystąpił problem z bazą danych.");
                e.printStackTrace();
            }
        }
    }

    public void alert() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText("Niepoprawne dane");
        alert.showAndWait();
    }

    public void asGuest(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/mainWindow.fxml"));
        Main.zmiana_strony_css(actionEvent, new Player(), loader, "guest", null);
    }

    public void signUp(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/Registration.fxml"));
        Main.zmiana_strony_css(actionEvent, null, loader, "rejestracja", null);
    }

    public void exitFromTheGame(){
        System.exit(0);
    }

    public void goToMenu(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/Menu.fxml"));
        Main.zmiana_strony_css(actionEvent, player, loader, "Menu", null);
    }
}
