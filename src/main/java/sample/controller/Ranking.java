package main.java.sample.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import main.java.sample.model.Database;
import main.java.sample.model.ModelTable;


import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class Ranking extends QuizController{

    @FXML
    private TableView<ModelTable> tableView;
    @FXML
    private TableColumn<ModelTable, String> loginColumn;
    @FXML
    private TableColumn<ModelTable, String> nameColumn;
    @FXML
    private TableColumn<ModelTable, String> surnameColumn;
    @FXML
    private TableColumn<ModelTable, Integer> pointsColumn;

    private ObservableList<ModelTable> observableList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        Database db = new Database();
        try {
            ResultSet rs = db.dbselect("SELECT * FROM player;");

            while (rs.next()){
                System.out.println(rs.getString("login"));
                observableList.add(new ModelTable(rs.getString("login"),rs.getString("name"),rs.getString("surname"),rs.getInt("score")));
                System.out.println(observableList.toString());
            }

        } catch (Database.db_exception db_exception) {
            db_exception.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        tableView.refresh();
        loginColumn.setCellValueFactory(new PropertyValueFactory<>("log"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("imie"));
        surnameColumn.setCellValueFactory(new PropertyValueFactory<>("naz"));
        pointsColumn.setCellValueFactory(new PropertyValueFactory<>("pkt"));

        tableView.setItems(observableList);
    }
}
