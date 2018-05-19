package main.java.sample.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import main.java.sample.model.Database;
import main.java.sample.model.Player;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class Ranking extends QuizController{

    @FXML
    private TableView<Player> tableView;
    @FXML
    private TableColumn<?, ?> idColumn;
    @FXML
    private TableColumn<?, ?> loginColumn;
    @FXML
    private TableColumn<?, ?> nameColumn;
    @FXML
    private TableColumn<?, ?> surnameColumn;
    @FXML
    private TableColumn<?, ?> pointsColumn;


    private List<Player> players;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        players = loadPlayers();
        
    }

    public void makeTable(){
        tableView.getItems().addAll(players);
    }

    public List<Player> loadPlayers(){
        List<Player> players = new ArrayList<>();
        Database db = new Database();
        Player newPlayer;
        try {
            ResultSet rs = db.dbselect("SELECT * FROM player;");
            while (rs.next()){
                newPlayer = new Player(rs);
                players.add(newPlayer);
            }

        } catch (Database.db_exception db_exception) {
            db_exception.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return players;
    }
}
