package sample;

import sample.comp.Admin;
import sample.comp.Config;
import sample.comp.Player;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.controllers.QuizController;

import java.io.IOException;

public class Main extends Application {

    private Config config;

    @Override
    public void start(Stage primaryStage) throws Exception{
        config = new Config("config.cfg");
        Parent root = FXMLLoader.load(getClass().getResource("fxml/Login.fxml"));
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

    //jak zrobie css to wprowadze jeszcze jedna zmienna do tej metody. narazie niech sie tak nazywa
    public static void zmiana_strony_css(ActionEvent actionEvent, Player player, FXMLLoader loader, String fxmlfile, Admin admin) throws IOException {
        Parent homePageParent = loader.load();
        QuizController controller = loader.getController();
        controller.setPlayer(player);
        Scene homePageScene = new Scene(homePageParent);
        Stage appStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        if (fxmlfile.equals("mainWindow")) {
            homePageParent.setId("pane");
        }
        else if (fxmlfile.equals("rejestracja")){
            homePageParent.setId("rejestracja");
        }
        else if (fxmlfile.equals("logowanie")){
            homePageParent.setId("pane");
        }
        else if (fxmlfile.equals("onePlayer")){
            homePageParent.setId("onePlayer");
            appStage.hide();
            appStage = new Stage();
        } else if (fxmlfile.equals("guest")) {
            homePageParent.setId("pane");
        }
        appStage.setScene(homePageScene);
        appStage.show();
    }
}
