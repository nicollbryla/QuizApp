package sample.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
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
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/AddQuestionAdmin.fxml"));
        Main.changeWindow(actionEvent, null, null, loader, admin);
    }

    public void backToMainWindow(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/Login.fxml"));
        Main.changeWindow(actionEvent, null, null, loader, admin);
    }

    public void acceptQuestion(ActionEvent actionEvent) throws IOException {
        if(AcceptQuestionAdmin.IsProposedQestionEmpty())
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Dodawanie zaproponowanych pytań");
            alert.setHeaderText("Baza z oczekującymi pytaniami jest pusta.");
            alert.showAndWait();
            return;
        }
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/AcceptQuestionAdmin.fxml"));
        Main.changeWindow(actionEvent, null, null, loader, admin);
    }

    public void editQuestion(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/EditQuestionAdmin.fxml"));
        Main.changeWindow(actionEvent, null, null, loader, admin);
    }
}
