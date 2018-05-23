package sample.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import sample.Main;
import sample.model.Admin;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainWindowAdmin extends QuizController {
    private Admin admin;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    public void setAdmin(Admin a){
        admin = a;
        System.out.println("Administrator: " + admin.name + " " + admin.surname);
    }

    public void addQuestion(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/addQuestionAdmin.fxml"));
        Main.changeWindow(actionEvent, null, null, loader, "addQuestionAdmin", admin);
    }

    public void backToMainWindow(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/login.fxml"));
        Main.changeWindow(actionEvent, null, null, loader, "login", admin);
    }
}
