package sample.controllers;

import sample.comp.Admin;
import sample.comp.Database;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import sample.comp.Player;
import sample.Main;

public class Login implements Initializable {
    @FXML
    private ToggleGroup logowanie;
    @FXML
    private Label alert;
    @FXML
    private ToggleButton klientButton;
    @FXML
    private ToggleButton adminButton;
    @FXML
    private PasswordField haslo;
    @FXML
    private TextField login;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        login.setText("PieterJan");
        haslo.setText("8hrhUaUUGhhglBF2822F3Yf");
    }

    public void zaloguj(ActionEvent actionEvent) throws IOException, SQLException {
        Database db = null;
        String name = null;
        Player player = null;
        Admin admin = null;
        ResultSet dbres = null;
        if (logowanie.getSelectedToggle() == null) {
            alert.setText("Nie wybrano zadnej opcji!");
            return;
        }
        if (logowanie.getSelectedToggle() == adminButton){
            try {
                login.setText("Andrzej.Lewandowski@rp2.pl");
                haslo.setText("46022013318");
                db = new Database();
                //TO DO query
                dbres = db.dbselect("");
                int ile = 0;
                while (dbres.next()) {
                    ile++;
                    admin = new Admin(dbres);
                    if (ile == 2) {
                        alert.setText("Więcej tych samych rekordów w bazie danych");
                        break;
                    }
                }
                if (ile == 0)
                    alert.setText("Niepoprawne dane");
                if (admin != null) {
                    db.close();
                    //tu miejsce na przelaczenie na amina jak juz powstanie
                }
                db.close();
            }
            catch (Exception e){
                try {
                    db.close();
                } catch (Exception e2){}
                alert.setText("Wystąpił problem z bazą danych.");
                System.out.println(e);
            }
        }
        else if (logowanie.getSelectedToggle() == klientButton){
            try {
                db = new Database();
                dbres = db.dbselect("SELECT * from player p where login = '" + login.getText() + "' AND password = '" + haslo.getText() + "'");
                int ile = 0;
                while (dbres.next()) {
                    ile++;
                    player = new Player(dbres);
                    if (ile == 2) {
                        alert.setText("Więcej tych samych rekordów w bazie danych");
                        break;
                    }
                }
                if (ile == 0)
                    alert.setText("Niepoprawne dane");
                if (player != null) {
                    db.close();
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("../fxml/mainWindow.fxml"));
                    Main.zmiana_strony_css(actionEvent, player, loader, "mainWindow",  null);
                }
                db.close();
            }
            catch (Exception e){
                try {
                    db.close();
                } catch (Exception e2){}
                alert.setText("Wystąpił problem z bazą danych.");
                System.out.println(e);
            }
        }
    }

    public void zarejestruj(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../fxml/rejestracja.fxml"));
        Main.zmiana_strony_css(actionEvent, null, loader, "rejestracja", null);
    }
}
