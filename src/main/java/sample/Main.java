package sample;

import sample.model.Admin;
import sample.model.Config;
import sample.model.Player;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.controller.QuizController;

import java.io.IOException;

public class Main extends Application {

    private Config config;

    @Override
    public void start(Stage primaryStage) throws Exception{
        config = new Config("config.cfg");
        Parent root = FXMLLoader.load(getClass().getResource("view/Login.fxml"));
        root.setId("pane");
        primaryStage.setTitle("QuizApp");
        Scene loginScene = new Scene(root);
        primaryStage.setScene(loginScene);
        primaryStage.setHeight(450);
        primaryStage.setWidth(600);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    public static void changeWindow(ActionEvent actionEvent, Player player, FXMLLoader loader, String fxmlfile, Admin admin) throws IOException {
        Parent homePageParent = loader.load();
        QuizController controller = loader.getController();
        controller.setPlayer(player);
        Scene homePageScene = new Scene(homePageParent);
        Stage appStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        switch (fxmlfile) {
            case "mainWindow":
                homePageParent.setId("pane");
                break;
            case "rejestracja":
                homePageParent.setId("rejestracja");
                break;
            case "logowanie":
                homePageParent.setId("pane");
                break;
            case "onePlayer":
                homePageParent.setId("onePlayer");
                appStage.hide();
                appStage = new Stage();
                break;
            case "guest":
                homePageParent.setId("pane");
                break;
            case "endOfGame":
                homePageParent.setId("endOfGame");
                break;
            case "ranking":
                homePageParent.setId("ranking");
                break;
            case "addQuestionAdmin":
                homePageParent.setId("addQuestionAdmin");
                break;
            case "Menu":
                homePageParent.setId("menu");
        }
        appStage.setScene(homePageScene);
        appStage.show();
    }
}
