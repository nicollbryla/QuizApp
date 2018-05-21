package main.java.sample.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import main.java.sample.Main;
import main.java.sample.model.Database;
import main.java.sample.model.ModelTable;

import java.io.IOException;
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
        setData();
        columnsSettings();
    }

    public void setData(){
        Database db = new Database();
        try {
            ResultSet rs = db.dbselect("SELECT * FROM player ORDER BY SCORE DESC;");

            while (rs.next()){
                observableList.add(new ModelTable(rs.getString("login"),rs.getString("name"),rs.getString("surname"),rs.getInt("score")));
            }

        } catch (Database.db_exception db_exception) {
            db_exception.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        tableView.setItems(observableList);
    }

    public void columnsSettings(){
        loginColumn.setCellValueFactory(new PropertyValueFactory<>("login"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        surnameColumn.setCellValueFactory(new PropertyValueFactory<>("surname"));
        pointsColumn.setCellValueFactory(new PropertyValueFactory<>("score"));

        loginColumn.setStyle("-fx-alignment: CENTER;");
        nameColumn.setStyle("-fx-alignment: CENTER;");
        surnameColumn.setStyle("-fx-alignment: CENTER;");
        pointsColumn.setStyle("-fx-alignment: CENTER;");
    }

    public void goToMenu(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/Menu.fxml"));
        Main.zmiana_strony_css(actionEvent, player, loader, "Menu", null);
    }
}
