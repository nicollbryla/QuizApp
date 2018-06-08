package sample;

import javafx.scene.layout.AnchorPane;
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

    @Override
    public void start(Stage primaryStage) throws Exception{
        new Config("config.cfg");
        AnchorPane root = FXMLLoader.load(getClass().getResource("view/Login.fxml"));
        primaryStage.setTitle("QuizApp");
        Scene loginScene = new Scene(root);
        primaryStage.setScene(loginScene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }


    public static void changeWindow(ActionEvent actionEvent, Player player1, Player player2, FXMLLoader loader, Admin admin) throws IOException {
        Parent homePageParent = loader.load();
        QuizController controller = loader.getController();
        if(player1 != null && player2 != null){
            controller.setTwoPlayers(player1, player2);
        }
        else if (admin!=null) {
            controller.setAdmin(admin);
        }
        else {
            controller.setPlayer(player1);
        }
        Scene homePageScene = new Scene(homePageParent);
        Stage appStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        appStage.setScene(homePageScene);
        appStage.show();
    }
}
