package sample.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import sample.Main;
import sample.controller.QuizController;
import sample.model.Database;
import sample.model.Player;
import sample.model.Question;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class LoginSecondPlayer extends QuizController {

    @FXML
    private TextField login;

    @FXML
    private PasswordField haslo;

    Player secondPlayer;

    @Override
    public void setTwoPlayers(Player firstPlayer, Player secondPlayer) {
        super.setTwoPlayers(firstPlayer,secondPlayer);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void signIn(ActionEvent actionEvent) throws Database.db_exception, SQLException, IOException {
        Database db = new Database();
        ResultSet dbres;
        dbres = db.dbselect("SELECT * from player p where login = '" + login.getText() + "' AND password = '" + haslo.getText() + "'");
        int cntr = 0;

        while(dbres.next()){
            cntr++;
            secondPlayer = new Player(dbres);
        }
        if(cntr == 0){
            alert();
        }
        if(secondPlayer != null) {
            setTwoPlayers(player, secondPlayer);
            player.howManyQuestions = Question.askForQuestions();
            secondPlayer.howManyQuestions = player.howManyQuestions;
            if(player.howManyQuestions != 0) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/TwoPlayers.fxml"));
                Main.changeWindow(actionEvent, player, secondPlayer, loader, admin);
            }
        }
        db.close();
    }

    private void alert() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText("Niepoprawne dane");
        alert.showAndWait();
    }

}
