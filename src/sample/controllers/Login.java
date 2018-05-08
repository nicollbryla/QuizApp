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
    public ToggleGroup logowanie;
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
    public void initialize(URL location, ResourceBundle resources) {}

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
                login.setText("Andrzej.Lewandowski@rp2.pl");
                haslo.setText("46022013318");
                db = new Database();
                //TODO query
                dbres = db.dbselect("");
                int cntr = 0;
                while (dbres.next()) {
                    cntr++;
                    admin = new Admin(dbres);
                    if (cntr == 2) {
                        alert.setText("Więcej tych samych rekordów w bazie danych");
                        break;
                    }
                }
                if (cntr == 0)
                    alert.setText("Niepoprawne dane");
                if (admin != null) {
                    db.close();
                    //TODO
                    //tu miejsce na przelaczenie na admina jak juz powstanie
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
        else if (logowanie.getSelectedToggle() == klientButton){
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
                if (cntr == 0)
                    alert.setText("Niepoprawne dane");
                if (player != null) {
                    db.close();
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("../fxml/mainWindow.fxml"));
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

    public void signUp(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../fxml/rejestracja.fxml"));
        Main.zmiana_strony_css(actionEvent, null, loader, "rejestracja", null);
    }
}
