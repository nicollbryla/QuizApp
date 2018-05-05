package sample;

import sample.comp.Admin;
import sample.comp.Player;
import sample.controllers.MainWindow;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
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

    public static void zmiana_strony(ActionEvent actionEvent, Player player, FXMLLoader loader, String fxmlfile, Admin worker) throws IOException{
        Parent home_page_parent = loader.load();
        Scene home_page_scene = new Scene(home_page_parent);

        Stage app_stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        app_stage.setScene(home_page_scene);
        app_stage.show();
    }

    public static void zmiana_strony_css(ActionEvent actionEvent, Player player, FXMLLoader loader, String fxmlfile, Admin admin) throws IOException {
        Parent home_page_parent = loader.load();
        Scene home_page_scene = new Scene(home_page_parent);
        if (fxmlfile.equals("mainWindow")) {
            MainWindow controller = loader.getController();
            controller.add_client(player);
            home_page_parent.setId("pane");
           // home_page_parent.getStylesheets().addAll(elements);
        }
        // coo
        else if (fxmlfile.equals("rejestracja")){
            home_page_parent.setId("rejestracja");
            //home_page_parent.getStylesheets().addAll(elements);
        } else if (fxmlfile.equals("logowanie")) {
            home_page_parent.setId("pane");
            //home_page_parent.getStylesheets().addAll(elements);
        }
        Stage app_stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        app_stage.setScene(home_page_scene);
        app_stage.show();
    }
}
